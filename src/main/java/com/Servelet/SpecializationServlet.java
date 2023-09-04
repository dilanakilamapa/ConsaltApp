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

import com.Model.Country;
import com.Model.Job;
import com.Model.User;
import com.Model.specialization;
import com.google.gson.Gson;
import com.service.SpecializationService;
import com.validator.EntityValidator;

/**
 * Servlet implementation class SpecializationServlet
 */
@WebServlet("/SpecializationServlet")
public class SpecializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private SpecializationService specializationService;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecializationServlet() {
    	specializationService = new SpecializationService();
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
            case "GetCountryByUser":
            	GetCountryByUser(request, response);
            	break;
            case "GetJobNameByCountry":
            	GetJobNameByCountry(request, response);
            	break;
            case "GetUserByCountryANDjob":
            	GetUserByCountryANDjob(request, response);
            	break;
            default:
                listSpecializations(request, response);
        }
	}

	private void GetUserByCountryANDjob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int Coun_id = Integer.parseInt(request.getParameter("Coun_id"));
		int Job_ID = Integer.parseInt(request.getParameter("Job_id"));
        List<specialization> specializations = specializationService.getSpecializationsByUserAndCountry(Coun_id,Job_ID);
       
        String json = new Gson().toJson(specializations);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    return;
		
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
	        List<specialization> specList = specializationService.listSpecializations();
	        request.setAttribute("specList", specList);
	        request.getRequestDispatcher("Admin/Specialization/list-specializations.jsp").forward(request, response);
	    }

	    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	List<User> users = specializationService.getAllConsultants();
	    	List<Country> countries =specializationService.getAllCountries();
	    	List<Job> jobs = specializationService.getAllJobs();
	    	request.setAttribute("users", users);
	    	request.setAttribute("countries", countries);
	    	request.setAttribute("jobs", jobs);
	        request.getRequestDispatcher("Admin/Specialization/add-specialization-form.jsp").forward(request, response);
	    }

	    private void addSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, SQLException {
	        
	    	List<String> errors1 = new ArrayList<>();
	    	
	        int user_ID = Integer.parseInt(request.getParameter("user"));
	        int country_id = Integer.parseInt(request.getParameter("Country"));
	        int job_Title_id = Integer.parseInt(request.getParameter("Job"));
	        
	        if (user_ID == 0) {
	        	errors1.add("Please select Consultant.");
	        }

	        if (country_id == 0) {
	        	errors1.add("Please select Country.");
	        }

	        if (job_Title_id == 0) {
	        	errors1.add("Please select Job.");
	        }

	        specialization spec = new specialization(user_ID, country_id, job_Title_id);
	        
	        EntityValidator<specialization> validator = new EntityValidator();
	        List<String> errors = validator.validate(spec);
	        
	        if(!errors.isEmpty()) {
	        	
	        	List<User> users = specializationService.getAllConsultants();
		    	List<Country> countries =specializationService.getAllCountries();
		    	List<Job> jobs = specializationService.getAllJobs();
		    	request.setAttribute("users", users);
		    	request.setAttribute("countries", countries);
		    	request.setAttribute("jobs", jobs);
		    	request.setAttribute("errors", errors);
		    	request.setAttribute("errors1", errors1);
		    	request.setAttribute("spec", spec);
		        request.getRequestDispatcher("Admin/Specialization/add-specialization-form.jsp").forward(request, response);
	        }
	        else {
	        	
	        	if(!errors1.isEmpty()) {
	        		specializationService.addSpecialization(spec);

			        response.sendRedirect("SpecializationServlet");
	        	}
	        	else {
	        		List<User> users = specializationService.getAllConsultants();
			    	List<Country> countries =specializationService.getAllCountries();
			    	List<Job> jobs = specializationService.getAllJobs();
			    	request.setAttribute("users", users);
			    	request.setAttribute("countries", countries);
			    	request.setAttribute("jobs", jobs);
			    	request.setAttribute("errors1", errors1);
			    	request.setAttribute("spec", spec);
			        request.getRequestDispatcher("Admin/Specialization/add-specialization-form.jsp").forward(request, response);
	        	}
	        	
	        }
	    }

	    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int specId = Integer.parseInt(request.getParameter("id"));
	        specialization spec = specializationService.getSpecializationById(specId);
	        List<User> users = specializationService.getAllConsultants();
	    	List<Country> countries =specializationService.getAllCountries();
	    	List<Job> jobs = specializationService.getAllJobs();
	    	request.setAttribute("users", users);
	    	request.setAttribute("countries", countries);
	    	request.setAttribute("jobs", jobs);
	        request.setAttribute("spec", spec);
	        request.getRequestDispatcher("Admin/Specialization/update-specialization-form.jsp").forward(request, response);
	    }

	    private void updateSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	List<String> errors1 = new ArrayList<>();
	        int specId = Integer.parseInt(request.getParameter("ID"));
	        int user_ID = Integer.parseInt(request.getParameter("user"));
	        int country_id = Integer.parseInt(request.getParameter("Country"));
	        int job_Title_id = Integer.parseInt(request.getParameter("Job"));
	        
	        if (user_ID == 0) {
	        	errors1.add("Please select Consultant.");
	        }

	        if (country_id == 0) {
	        	errors1.add("Please select Country.");
	        }

	        if (job_Title_id == 0) {
	        	errors1.add("Please select Job.");
	        }

	        specialization spec = new specialization(specId, user_ID, country_id, job_Title_id);
	        
	        EntityValidator<specialization> validator = new EntityValidator();
	        List<String> errors = validator.validate(spec);
	        
	        if(!errors.isEmpty()) {
	        	
	        	 List<User> users = specializationService.getAllConsultants();
	 	    	List<Country> countries =specializationService.getAllCountries();
	 	    	List<Job> jobs = specializationService.getAllJobs();
		    	request.setAttribute("users", users);
		    	request.setAttribute("countries", countries);
		    	request.setAttribute("jobs", jobs);
		        request.setAttribute("spec", spec);
		        request.setAttribute("errors", errors);
		        request.setAttribute("errors1", errors1);
		        request.getRequestDispatcher("Admin/Specialization/update-specialization-form.jsp").forward(request, response);
	        }
	        else {
	        	
	        	if(!errors1.isEmpty()) {
	        		try {
	        			specializationService.updateSpecialization(spec);
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
	        		response.sendRedirect("SpecializationServlet");
	        	}
	        	else {
	        		List<User> users = specializationService.getAllConsultants();
		 	    	List<Country> countries =specializationService.getAllCountries();
		 	    	List<Job> jobs = specializationService.getAllJobs();
			    	request.setAttribute("users", users);
			    	request.setAttribute("countries", countries);
			    	request.setAttribute("jobs", jobs);
			        request.setAttribute("spec", spec);
			        request.setAttribute("errors1", errors1);
			        request.getRequestDispatcher("Admin/Specialization/update-specialization-form.jsp").forward(request, response);
	        	}
	        	
	        }
	    }

	    private void deleteSpecialization(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int specId = Integer.parseInt(request.getParameter("id"));
	        try {
	        	specializationService.deleteSpecialization(specId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        response.sendRedirect("SpecializationServlet");
	    }
	    
	    private void GetCountryByUser(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int USerID = Integer.parseInt(request.getParameter("User_id"));
	        List<specialization> specializations = specializationService.getSpecializationsByUser(USerID);
	       
	        String json = new Gson().toJson(specializations);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    return;
	    }
	    
	    private void GetJobNameByCountry(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int USerID = Integer.parseInt(request.getParameter("Coun_id"));
	        List<specialization> specializations = specializationService.getSpecializationsByCountry(USerID);
	       
	        String json = new Gson().toJson(specializations);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    return;
	    }

}
