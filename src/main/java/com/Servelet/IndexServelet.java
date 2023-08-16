package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AppointmentDAO;
import com.DAO.JobseekerDAO;
import com.DAO.SpecializationDAO;
import com.DAO.UserDAO;
import com.Model.Appointment;
import com.Model.Jobseeker;
import com.Model.specialization;

/**
 * Servlet implementation class IndexServelet
 */
@WebServlet("/IndexServelet")
public class IndexServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobseekerDAO jobseekerDAO;
	private SpecializationDAO specializationDAO;
	private AppointmentDAO appointmentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServelet() {
        super();
        jobseekerDAO = new JobseekerDAO();
        specializationDAO = new SpecializationDAO();
        appointmentDAO = new AppointmentDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("parameter");
        if (parameter == null) {
        	parameter = "list";
        }
        switch (parameter) {
        	
            case "ShowIndex":
            	ShowIndex(request, response);
                break;
            case "Add":
			try {
				Add(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            default:
            	ShowIndex(request, response);
        }
	}

	private void Add(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String firstName = request.getParameter("F_name");
        String lastName = request.getParameter("L_name");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phone_number"));
        
        int JobSeekerInsertedID = -1;
        int CountryID = Integer.parseInt(request.getParameter("Country"));
        int JobID = Integer.parseInt(request.getParameter("Job"));
        int ConsultID = Integer.parseInt(request.getParameter("Consultant"));
        int Available_id = Integer.parseInt(request.getParameter("start_time"));
        String Note = request.getParameter("Note");
        String Type = "Online";

        Jobseeker newJobseeker = new Jobseeker(firstName, lastName, email, phoneNumber);
        JobSeekerInsertedID = jobseekerDAO.addJobseekerReurnID(newJobseeker);
        System.out.println(JobSeekerInsertedID);

        Appointment appointment = new Appointment(ConsultID, JobSeekerInsertedID, Available_id, CountryID, JobID, Note, Type);
        appointmentDAO.addAppointment(appointment);
        
        request.getRequestDispatcher("IndexServelet?parameter=list").forward(request, response);
	}

	private void ShowIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<specialization> specializations = specializationDAO.SELECT_SPECIALIZATION_ALL_COUNTRY();
		request.setAttribute("specializations", specializations);
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
