package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.RoleDAO;
import com.DAO.UserDAO;
import com.Model.Role;
import com.Model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    	 userDAO = new UserDAO();
    	 roleDAO = new RoleDAO();
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
	                listUsers(request, response);
	                break;
	            case "showAddForm":
	                showAddForm(request, response);
	                break;
	            case "add":
				try {
					addUser(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	                break;
	            case "showUpdateForm":
	                showUpdateForm(request, response);
	                break;
	            case "update":
	                updateUser(request, response);
	                break;
	            case "delete":
	                deleteUser(request, response);
	                break;
	            default:
	                listUsers(request, response);
	        }
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDAO.selectAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("Admin/User/ShowAllUsers.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Role> roles = roleDAO.selectAllRoles();
    	 request.setAttribute("roles", roles);
        request.getRequestDispatcher("Admin/User/AddUser.jsp").forward(request, response);
        
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	String f_name = request.getParameter("F_name");
        String l_name = request.getParameter("L_name");
        String address = request.getParameter("Address");
        int contact_01 = Integer.parseInt(request.getParameter("phone_number01"));
        int contact_02 = Integer.parseInt(request.getParameter("phone_number02"));
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("DOB"));
        int role_id = Integer.parseInt(request.getParameter("role_id"));

        User newUser = new User(f_name, l_name, address, contact_01, contact_02, dob, role_id);
        userDAO.addUser(newUser);

        response.sendRedirect("UserServlet");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("update id " + userId);
        User user = userDAO.getUserById(userId);
        List<Role> roles = roleDAO.selectAllRoles();
   	 	request.setAttribute("roles", roles);
        request.setAttribute("user", user);
        request.getRequestDispatcher("Admin/User/UpdateUser.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int userId = Integer.parseInt(request.getParameter("ID"));
        String f_name = request.getParameter("F_name");
        String l_name = request.getParameter("L_name");
        String address = request.getParameter("Address");
        int contact_01 = Integer.parseInt(request.getParameter("phone_number01"));
        int contact_02 = Integer.parseInt(request.getParameter("phone_number02"));
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("DOB"));
        int role_id = Integer.parseInt(request.getParameter("role_id"));

        User user = new User(userId, f_name, l_name, address, contact_01, contact_02, dob, role_id);
        try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.sendRedirect("UserServlet");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("Delete id " +userId);
        try {
			userDAO.deleteUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("UserServlet");
    }

}
