package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.JobseekerDAO;
import com.Model.Jobseeker;

/**
 * Servlet implementation class JobseekerServlet
 */
@WebServlet("/JobseekerServlet")
public class JobseekerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobseekerDAO jobseekerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobseekerServlet() {
    	 jobseekerDAO = new JobseekerDAO();
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
                listJobseekers(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
                addJobseeker(request, response);
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "update":
                updateJobseeker(request, response);
                break;
            case "delete":
                deleteJobseeker(request, response);
                break;
            default:
                listJobseekers(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listJobseekers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Jobseeker> jobseekers = jobseekerDAO.selectAllJobseekers();
        request.setAttribute("jobseekers", jobseekers);
        request.getRequestDispatcher("Admin/JobSeeker/jobseekers.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display add jobseeker form
        request.getRequestDispatcher("Admin/JobSeeker/add_jobseeker.jsp").forward(request, response);
    }

    private void addJobseeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));

        Jobseeker newJobseeker = new Jobseeker(firstName, lastName, email, phoneNumber);
        try {
            jobseekerDAO.addJobseeker(newJobseeker);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("JobseekerServlet");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        Jobseeker jobseeker = jobseekerDAO.getJobseekerById(jobseekerId);
        request.setAttribute("jobseeker", jobseeker);
        request.getRequestDispatcher("Admin/JobSeeker/update_jobseeker.jsp").forward(request, response);
    }

    private void updateJobseeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));

        Jobseeker updatedJobseeker = new Jobseeker(jobseekerId, firstName, lastName, email, phoneNumber);
        try {
            jobseekerDAO.updateJobseeker(updatedJobseeker);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("JobseekerServlet");
    }

    private void deleteJobseeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        try {
            jobseekerDAO.deleteJobseeker(jobseekerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("JobseekerServlet");
    }

}
