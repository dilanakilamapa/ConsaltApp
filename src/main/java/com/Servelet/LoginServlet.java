package com.Servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.LoginDAO;
import com.DAO.RoleDAO;
import com.DAO.UserDAO;
import com.Model.Role;
import com.Model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	loginDAO = new LoginDAO();
    	userDAO =new UserDAO();
    	roleDAO = new RoleDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("Username");
        String password = request.getParameter("password");

        int empId = loginDAO.checkLogin(userName, password);
        System.out.println("in serv"+ empId);

        if (empId != -1) {
            // Successful login
        	User users = userDAO.getUserById(empId);
        	System.out.println("in serv"+ users.getRole_id());
            Role role = roleDAO.getRoleById(users.getRole_id());
            System.out.println("in serv"+ role.getRole_name());
            
            request.setAttribute("empId", empId);
            request.setAttribute("users", users);
            
            Cookie loginCookie = new Cookie("role",role.getRole_name());
			loginCookie.setMaxAge(10*60);
			response.addCookie(loginCookie);
            
            response.sendRedirect("Admin/Dashboard/Dashboard.jsp");
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
