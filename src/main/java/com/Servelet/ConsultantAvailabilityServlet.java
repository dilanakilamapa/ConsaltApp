package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
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

/**
 * Servlet implementation class ConsultantAvailabilityServlet
 */
@WebServlet("/ConsultantAvailabilityServlet")
public class ConsultantAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsultantAvailabilityDAO consultantAvailabilityDAO;
	private UserDAO userDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultantAvailabilityServlet() {
        super();
        consultantAvailabilityDAO = new ConsultantAvailabilityDAO();
        userDAO = new UserDAO();
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

	private void deleteConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int availabilityId = Integer.parseInt(request.getParameter("id"));
        consultantAvailabilityDAO.deleteConsultantAvailability(availabilityId);
        response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+0);
	}

	private void updateConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int availabilityId = Integer.parseInt(request.getParameter("id"));
        java.sql.Date DATE = java.sql.Date.valueOf(request.getParameter("DATE"));
        java.sql.Time start_Time = java.sql.Time.valueOf(request.getParameter("start_Time"));
        java.sql.Time end_Time = java.sql.Time.valueOf(request.getParameter("end_Time"));
        
        
        
        ConsultantAvailability updatedAvailability = new ConsultantAvailability(DATE, start_Time, end_Time, availabilityId);
        Boolean abc = consultantAvailabilityDAO.updateConsultantAvailability(updatedAvailability);
        System.out.println("Update "+availabilityId + " " + DATE + " " + start_Time + " " + end_Time + " " +abc);
        response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+0);
	}

	private void addConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
        java.sql.Date DATE = java.sql.Date.valueOf(request.getParameter("DATE"));
        java.sql.Time start_Time = java.sql.Time.valueOf(request.getParameter("start_Time")+":00");
        java.sql.Time end_Time = java.sql.Time.valueOf(request.getParameter("end_Time")+":00");
        
        System.out.println(user_ID + " " + DATE + " " + start_Time + " " + end_Time );

        ConsultantAvailability newAvailability = new ConsultantAvailability(user_ID, DATE, start_Time, end_Time);
        consultantAvailabilityDAO.addConsultantAvailability(newAvailability);

        response.sendRedirect("/ConsultAppoinmentWebApp/ConsultantAvailabilityServlet?parameter=list&user_ID="+user_ID);
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
	    List<ConsultantAvailability> availabilities = consultantAvailabilityDAO.selectAllConsultantAvailabilitiesWithName(user_ID);
	    List<User> users = userDAO.selectAllConsultant();
	    request.setAttribute("availabilities", availabilities);
	    request.setAttribute("users", users);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/consultant_availabilities.jsp").forward(request, response);
	}
	
	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		List<User> users = userDAO.selectAllConsultant();
		request.setAttribute("users", users);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/add_consultant_availability.jsp").forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int availabilityId = Integer.parseInt(request.getParameter("id"));
	    ConsultantAvailability availability = consultantAvailabilityDAO.getConsultantAvailabilityById(availabilityId);
	    request.setAttribute("availability", availability);
	    request.getRequestDispatcher("Admin/ConsultantAvailabilities/update_consultant_availability.jsp").forward(request, response);
	}

}
