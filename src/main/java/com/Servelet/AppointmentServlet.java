package com.Servelet;


import com.Model.Appointment;
import com.Model.Jobseeker;
import com.Model.User;
import com.service.AppointmentService;
import com.validator.EntityValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentService appointmentService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
        super();
        appointmentService = new AppointmentService();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Parameter = request.getParameter("parameter");

        if (Parameter == null) {
        	Parameter = "list";
        }

        switch (Parameter) {
            case "list":
                listAppointments(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
			try {
				addAppointments(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "edit":
                getAppointment(request, response);
                break;
            case "update":
			try {
				updateAppointment(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "delete":
                deleteAppointment(request, response);
                break;
            default:
                //listAppointments(request, response);
        }
	}

	private void addAppointments(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		List<String> errors1 = new ArrayList<>();

		int consultantId = Integer.parseInt(request.getParameter("Consultant"));
        int jobSeekerId = Integer.parseInt(request.getParameter("Seeker"));
        int availableId = Integer.parseInt(request.getParameter("start_time"));
        int countryId = Integer.parseInt(request.getParameter("Country"));
        int jobId = Integer.parseInt(request.getParameter("Job"));
        String note = request.getParameter("Note");
        String type = request.getParameter("Type");
        
        if (consultantId == 0) {
            errors1.add("Place Select Consultant !");
        }

        if (jobSeekerId == 0) {
            errors1.add("Place Select Job Seeker !");
        }

        if (availableId == 0) {
            errors1.add("Place Select Available !");
        }

        if (countryId == 0) {
            errors1.add("Place Select Country !");
        }

        if (jobId == 0) {
            errors1.add("Place Select Job !");
        }
        
        if (type == null || type.isEmpty()) {
            errors1.add("Place Select Type !");
        }
        
        Appointment appointment = new Appointment( consultantId, jobSeekerId, availableId, countryId, jobId, note,type);
        
        EntityValidator<Appointment> validator = new EntityValidator();
        List<String> errors = validator.validate(appointment);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	List<User> users = appointmentService.getAllConsultants();
    		List<Jobseeker> Jobseekers = appointmentService.getAllJobseekers();
    		//List<ConsultantAvailability> ConsultantAvailabilityDates = consultantAvailabilityDAO .selectAllConsultantAvailabilitiesWithName(id);
    		request.setAttribute("users", users);
    		request.setAttribute("Jobseekers", Jobseekers);
            request.getRequestDispatcher("Admin/Appointment/add-appointment.jsp").forward(request, response);
        }
        else {
        	
        	if (!errors1.isEmpty()) {
                request.setAttribute("errors1", errors1);
            	List<User> users = appointmentService.getAllConsultants();
        		List<Jobseeker> Jobseekers = appointmentService.getAllJobseekers();
        		//List<ConsultantAvailability> ConsultantAvailabilityDates = consultantAvailabilityDAO .selectAllConsultantAvailabilitiesWithName(id);
        		request.setAttribute("users", users);
        		request.setAttribute("Jobseekers", Jobseekers);
                request.getRequestDispatcher("Admin/Appointment/add-appointment.jsp").forward(request, response);
        	} else {
        		appointmentService.addAppointment(appointment);
                response.sendRedirect("/ConsultAppoinmentWebApp/AppointmentServlet?parameter=list&user_ID="+ 0);
        	}
        	
        }
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = appointmentService.getAllConsultants();
		List<Jobseeker> Jobseekers = appointmentService.getAllJobseekers();
		//List<ConsultantAvailability> ConsultantAvailabilityDates = consultantAvailabilityDAO .selectAllConsultantAvailabilitiesWithName(id);
		request.setAttribute("users", users);
		request.setAttribute("Jobseekers", Jobseekers);
        request.getRequestDispatcher("Admin/Appointment/add-appointment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println(request.getParameter("user_ID"));
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
		
		List<User> users = appointmentService.getAllConsultants();
        List<Appointment> appointmentList = appointmentService.getAppointmentsByConsultant(user_ID);
        request.setAttribute("appointmentList", appointmentList);
        request.setAttribute("users", users);
        request.getRequestDispatcher("Admin/Appointment/list-appointments.jsp").forward(request, response);
    }

    private void getAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher("Admin/Appointment/edit-appointment.jsp").forward(request, response);
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int consultantId = Integer.parseInt(request.getParameter("consultantId"));
        int jobSeekerId = Integer.parseInt(request.getParameter("jobSeekerId"));
        int availableId = Integer.parseInt(request.getParameter("availableId"));
        int countryId = Integer.parseInt(request.getParameter("countryId"));
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String note = request.getParameter("note");
        String Appointment_Type = request.getParameter("Appointment_Type");

        Appointment appointment = new Appointment(id, consultantId, jobSeekerId, availableId, countryId, jobId, note,Appointment_Type);
        
        EntityValidator<Appointment> validator = new EntityValidator();
        List<String> errors = validator.validate(appointment);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("Admin/Appointment/edit-appointment.jsp").forward(request, response);
        }
        else {
        	boolean rowUpdated = appointmentService.updateAppointment(appointment);
            response.sendRedirect("/ConsultAppoinmentWebApp/AppointmentServlet?parameter=list&user_ID="+0);
        }  
    }

    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        try {
        	appointmentService.deleteAppointment(appointmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ConsultAppoinmentWebApp/AppointmentServlet?parameter=list&user_ID="+0);
    }

}
