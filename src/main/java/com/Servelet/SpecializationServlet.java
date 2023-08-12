package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.CountryDAO;
import com.DAO.JobDAO;
import com.DAO.SpecializationDAO;
import com.DAO.UserDAO;
import com.Model.Country;
import com.Model.Job;
import com.Model.User;
import com.Model.specialization;

/**
 * Servlet implementation class SpecializationServlet
 */
@WebServlet("/SpecializationServlet")
public class SpecializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private SpecializationDAO specializationDAO;
	 private UserDAO userDAO;
	 private CountryDAO countryDAO;
	 private JobDAO jobDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecializationServlet() {
    	specializationDAO = new SpecializationDAO();
    	userDAO = new UserDAO();
    	countryDAO = new CountryDAO();
    	jobDAO = new JobDAO();
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
                listSpecializations(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
                try {
                    addSpecialization(request, response);
                } catch (ServletException | IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "update":
                updateSpecialization(request, response);
                break;
            case "delete":
                deleteSpecialization(request, response);
                break;
            default:
                listSpecializations(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 private void listSpecializations(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        List<specialization> specList = specializationDAO.selectAllSpecializationswithname();
	        request.setAttribute("specList", specList);
	        request.getRequestDispatcher("Admin/Specialization/list-specializations.jsp").forward(request, response);
	    }

	    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	List<User> users = userDAO.selectAllConsultant();
	    	List<Country> countries =countryDAO.selectAllCountries();
	    	List<Job> jobs = jobDAO.selectAllJobs();
	    	request.setAttribute("users", users);
	    	request.setAttribute("countries", countries);
	    	request.setAttribute("jobs", jobs);
	        request.getRequestDispatcher("Admin/Specialization/add-specialization-form.jsp").forward(request, response);
	    }

	    private void addSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, SQLException {
	        
	        int user_ID = Integer.parseInt(request.getParameter("user"));
	        int country_id = Integer.parseInt(request.getParameter("Country"));
	        int job_Title_id = Integer.parseInt(request.getParameter("Job"));

	        specialization spec = new specialization(user_ID, country_id, job_Title_id);
	        specializationDAO.addSpecialization(spec);

	        response.sendRedirect("SpecializationServlet");
	    }

	    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int specId = Integer.parseInt(request.getParameter("id"));
	        specialization spec = specializationDAO.getSpecializationById(specId);
	        List<User> users = userDAO.selectAllConsultant();
	    	List<Country> countries =countryDAO.selectAllCountries();
	    	List<Job> jobs = jobDAO.selectAllJobs();
	    	request.setAttribute("users", users);
	    	request.setAttribute("countries", countries);
	    	request.setAttribute("jobs", jobs);
	        request.setAttribute("spec", spec);
	        request.getRequestDispatcher("Admin/Specialization/update-specialization-form.jsp").forward(request, response);
	    }

	    private void updateSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int specId = Integer.parseInt(request.getParameter("ID"));
	        int user_ID = Integer.parseInt(request.getParameter("user"));
	        int country_id = Integer.parseInt(request.getParameter("Country"));
	        int job_Title_id = Integer.parseInt(request.getParameter("Job"));

	        specialization spec = new specialization(specId, user_ID, country_id, job_Title_id);
	        try {
	            specializationDAO.updateSpecialization(spec);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        response.sendRedirect("SpecializationServlet");
	    }

	    private void deleteSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int specId = Integer.parseInt(request.getParameter("id"));
	        try {
	            specializationDAO.deleteSpecialization(specId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        response.sendRedirect("SpecializationServlet");
	    }

}
