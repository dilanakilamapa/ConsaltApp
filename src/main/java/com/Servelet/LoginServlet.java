package com.Servelet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Model.ConsultantAvailability;
import com.Model.Role;
import com.Model.User;
import com.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	loginService = new LoginService();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("Username");
        String password = request.getParameter("password");

        int empId = loginService.checkLogin(userName, password);
        System.out.println("in serv"+ empId);

        if (empId != -1) {
            // Successful login
        	User users = loginService.getUserbyID(empId);
        	System.out.println("in serv"+ users.getRole_id());
            Role role = loginService.getRoleById(users.getRole_id());
            System.out.println("in serv"+ role.getRole_name());
            
            request.setAttribute("empId", empId);
            request.setAttribute("users", users);
            
            Cookie loginCookie = new Cookie("role",role.getRole_name());
			loginCookie.setMaxAge(10*60);
			response.addCookie(loginCookie);
            
			if(role.getRole_name().equals("Consultant")) {
				System.out.println(role.getRole_name()+"if");
				List<ConsultantAvailability> availabilities = loginService.getAllConsultantAvailabilities(empId);
			    request.setAttribute("availabilities", availabilities);
			    request.setAttribute("user", users);
			    request.getRequestDispatcher("Admin/ConsultantAvailabilities/consultant_availabilities_for_consultant.jsp").forward(request, response);
			}
			else if(role.getRole_name().equals("User")) {
				response.sendRedirect("Admin/Dashboard/DashboardForUser.jsp");
			}
			else {
				response.sendRedirect("Admin/Dashboard/Dashboard.jsp");
			}
            
        } else {
            // Failed login
            request.setAttribute("errorMessage", "Invalid username or password !");
            request.getRequestDispatcher("/Admin/Dashboard/login.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
