package com.Servelet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.ConsultantAvailabilityDAO;
import com.DAO.UserDAO;
import com.Model.ConsultantAvailability;
import com.Model.User;
import com.google.gson.Gson;
import com.service.ConsultantAvailabilityService;
import com.validator.EntityValidator;




/**
 * Servlet implementation class ConsultantAvailabilityServlet
 */
@WebServlet("/ConsultantAvailabilityServlet")
public class ConsultantAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsultantAvailabilityService consultantAvailabilityService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultantAvailabilityServlet() {
        super();
        consultantAvailabilityService = new ConsultantAvailabilityService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = request.getParameter("parameter");
        if (parameter == null) {
        	parameter = "list";
        }

        switch (parameter) {
        	case "getTime":
        	getTime(request, response);
            break;
            case "getDates":
            	getDates(request, response);
                break;
            case "list":
                listConsultantAvailabilities(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
			try {
				addConsultantAvailability(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "update":
			try {
				updateConsultantAvailability(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "delete":
			try {
				deleteConsultantAvailability(request, response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            default:
                listConsultantAvailabilities(request, response);
        }
	}
	
	private void getDates(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		int user_ID = Integer.parseInt(request.getParameter("id"));
	    List<ConsultantAvailability> consultantAvailabilityList = consultantAvailabilityService.getAllConsultantAvailabilities(user_ID);
	    
	    String json = new Gson().toJson(consultantAvailabilityList);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    return;
	    
	}
	
	private void getTime(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		Date user_ID = java.sql.Date.valueOf(request.getParameter("date"));
		System.out.println(user_ID);
	    List<ConsultantAvailability> consultantAvailabilityList = consultantAvailabilityService.getAvailabilityByDate(user_ID);
	    System.out.println(consultantAvailabilityList);
	    String json = new Gson().toJson(consultantAvailabilityList);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    return;
	    
	}

	private void deleteConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int availabilityId = Integer.parseInt(request.getParameter("id"));
		consultantAvailabilityService.deleteConsultantAvailability(availabilityId);
        response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+0);
	}

	private void updateConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<String> errors1 = new ArrayList<>();
		
		int availabilityId = Integer.parseInt(request.getParameter("id"));
        java.sql.Date DATE = java.sql.Date.valueOf(request.getParameter("DATE"));
        java.sql.Time start_Time = java.sql.Time.valueOf(request.getParameter("start_Time"));
        java.sql.Time end_Time = java.sql.Time.valueOf(request.getParameter("end_Time"));
        

        if (DATE == null) {
            errors1.add("Date should not be null.");
        } else {
            
            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            System.out.println("Today" + today);
            if (DATE.equals(today) || DATE.before(today)) {
                errors1.add("Date should not be today or befor day.");
            }
        }

        if (start_Time != null && end_Time != null && start_Time.after(end_Time)) {
            errors1.add("Start time should be after end time.");
        }

        ConsultantAvailability updatedAvailability = new ConsultantAvailability(DATE, start_Time, end_Time, availabilityId);
        
        EntityValidator<ConsultantAvailability> validator = new EntityValidator();
        List<String> errors = validator.validate(updatedAvailability);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	request.setAttribute("availability", updatedAvailability);
    	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/update_consultant_availability.jsp").forward(request, response);
        }
        else {
        	
        	if (!errors1.isEmpty()) {
        		request.setAttribute("errors", errors1);
        		request.setAttribute("availability", updatedAvailability);
        	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/update_consultant_availability.jsp").forward(request, response);
        	} else {
        		Boolean abc = consultantAvailabilityService.updateConsultantAvailability(updatedAvailability);
                System.out.println("Update "+availabilityId + " " + DATE + " " + start_Time + " " + end_Time + " " +abc);
                response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+0);
        	}
        	
        }
	}

	private void addConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<String> errors1 = new ArrayList<>();
		
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
        java.sql.Date DATE = java.sql.Date.valueOf(request.getParameter("DATE"));
        java.sql.Time start_Time = java.sql.Time.valueOf(request.getParameter("start_Time")+":00");
        java.sql.Time end_Time = java.sql.Time.valueOf(request.getParameter("end_Time")+":00");
        
        System.out.println(user_ID + " " + DATE + " " + start_Time + " " + end_Time );
        
        if (user_ID == 0) {
            errors1.add("User ID should not be 0.");
        }

        if (DATE == null) {
            errors1.add("Date should not be null.");
        } else {
            
            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            System.out.println("Today" + today);
            if (DATE.equals(today) || DATE.before(today)) {
                errors1.add("Date should not be today or befor day.");
            }
        }

        if (start_Time != null && end_Time != null && start_Time.after(end_Time)) {
            errors1.add("Start time should be after end time.");
        }

        ConsultantAvailability newAvailability = new ConsultantAvailability(user_ID, DATE, start_Time, end_Time);
        
        EntityValidator<ConsultantAvailability> validator = new EntityValidator();
        List<String> errors = validator.validate(newAvailability);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	List<User> users = consultantAvailabilityService.getAllConsultants();
    		request.setAttribute("users", users);
    	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/add_consultant_availability.jsp").forward(request, response);
        }
        else {
        	
        	if (!errors1.isEmpty()) {
        		request.setAttribute("errors", errors1);
            	List<User> users = consultantAvailabilityService.getAllConsultants();
        		request.setAttribute("users", users);
        	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/add_consultant_availability.jsp").forward(request, response);
        	} else {
        		consultantAvailabilityService.addConsultantAvailability(newAvailability);

                response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+user_ID);
        	}

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listConsultantAvailabilities(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
	    List<ConsultantAvailability> availabilities = consultantAvailabilityService.getAllConsultantAvailabilities(user_ID);
	    List<User> users = consultantAvailabilityService.getAllConsultants();
	    request.setAttribute("availabilities", availabilities);
	    request.setAttribute("users", users);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/consultant_availabilities.jsp").forward(request, response);
	}
	
	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		List<User> users = consultantAvailabilityService.getAllConsultants();
		request.setAttribute("users", users);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/add_consultant_availability.jsp").forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int availabilityId = Integer.parseInt(request.getParameter("id"));
	    ConsultantAvailability availability = consultantAvailabilityService.getConsultantAvailabilityById(availabilityId);
	    request.setAttribute("availability", availability);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/update_consultant_availability.jsp").forward(request, response);
	}

}
