package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.JobseekerDAO;
import com.Model.Jobseeker;
import com.Model.User;
import com.service.JobseekerService;
import com.validator.EntityValidator;

/**
 * Servlet implementation class JobseekerServlet
 */
@WebServlet("/JobseekerServlet")
public class JobseekerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobseekerService jobseekerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobseekerServlet() {
    	jobseekerService = new JobseekerService();
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
        List<Jobseeker> jobseekers = jobseekerService.getAllJobseekers();
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
    	List<String> errors1 = new ArrayList<>();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = 0700000000;
        
        if(!request.getParameter("phoneNumber").isEmpty()) {
        	if(request.getParameter("phoneNumber").length() < 10) {
        		errors1.add("Please Enter your corect Contact Number.");
        	}
        	phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
           }else {
           	if(phoneNumber == 0700000000) {
           		errors1.add("Please Enter your Contact Number.");
           	}
           }
        Jobseeker newJobseeker = new Jobseeker(firstName, lastName, email, phoneNumber);
        
        EntityValidator<Jobseeker> validator = new EntityValidator();
        List<String> errors = validator.validate(newJobseeker);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors1", errors1);
        	request.setAttribute("errors", errors);
        	 request.getRequestDispatcher("Admin/JobSeeker/add_jobseeker.jsp").forward(request, response);
        }
        else {
        	
        	if(!errors1.isEmpty()) {
        		try {
        			jobseekerService.addJobseeker(newJobseeker);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("JobseekerServlet");
        	}
        	else {
        	 request.setAttribute("errors", errors1);
        	 request.setAttribute("errors", errors);
           	 request.getRequestDispatcher("Admin/JobSeeker/add_jobseeker.jsp").forward(request, response);
        	}
        	
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        Jobseeker jobseeker = jobseekerService.getJobseekerById(jobseekerId);
        request.setAttribute("jobseeker", jobseeker);
        request.getRequestDispatcher("Admin/JobSeeker/update_jobseeker.jsp").forward(request, response);
    }

    private void updateJobseeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<String> errors1 = new ArrayList<>();
    	
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        int phoneNumber = 0700000000;
        
        if(!request.getParameter("phoneNumber").isEmpty()) {
        	phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
           }else {
           	if(phoneNumber == 0700000000) {
           		errors1.add("Please Enter your Contact Number.");
           	}
           }

        Jobseeker updatedJobseeker = new Jobseeker(jobseekerId, firstName, lastName, email, phoneNumber);
        
        EntityValidator<Jobseeker> validator = new EntityValidator();
        List<String> errors = validator.validate(updatedJobseeker);
        
        if(!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	request.setAttribute("jobseeker", updatedJobseeker);
            request.getRequestDispatcher("Admin/JobSeeker/update_jobseeker.jsp").forward(request, response);
        }
        else {
        	
        	if(!errors1.isEmpty()) {
        		try {
        			jobseekerService.updateJobseeker(updatedJobseeker);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("JobseekerServlet");
        	}
        	else {
        	 request.setAttribute("errors", errors1);
        	 request.setAttribute("errors", errors);
        	 request.setAttribute("jobseeker", updatedJobseeker);
        	 request.getRequestDispatcher("Admin/JobSeeker/update_jobseeker.jsp").forward(request, response);
        	}
        }
    }

    private void deleteJobseeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobseekerId = Integer.parseInt(request.getParameter("id"));
        try {
        	jobseekerService.deleteJobseeker(jobseekerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("JobseekerServlet");
    }

}
