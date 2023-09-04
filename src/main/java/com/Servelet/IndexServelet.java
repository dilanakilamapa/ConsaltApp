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

import com.Model.Appointment;
import com.Model.Jobseeker;
import com.Model.specialization;
import com.service.IndexService;
import com.validator.EntityValidator;

/**
 * Servlet implementation class IndexServelet
 */
@WebServlet("/IndexServelet")
public class IndexServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexService indexService;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServelet() {
        super();
        indexService = new IndexService();
        
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
		List<String> errors1 = new ArrayList<>();
		
		String firstName = request.getParameter("F_name");
        String lastName = request.getParameter("L_name");
        String email = request.getParameter("email");
        
        int phoneNumber = 0700000000;
        
        if(!request.getParameter("phone_number").isEmpty()) {
        	if(request.getParameter("phone_number").length() < 10) {
        		errors1.add("Please Enter your corect Contact Number.");
        	}
        	
        	if(request.getParameter("phone_number").length() == 10) {
        		phoneNumber = Integer.parseInt(request.getParameter("phone_number"));
        	}
        	else {
        		errors1.add("You Entered a Contact Number of less than 10 ");
        	}
        	
        	
           }else {
           	if(phoneNumber == 0700000000) {
           		errors1.add("Please Enter your Contact Number.");
           	}
           }
        
        int JobSeekerInsertedID = -1;
        int CountryID = Integer.parseInt(request.getParameter("Country"));
        int JobID = Integer.parseInt(request.getParameter("Job"));
        int ConsultID = Integer.parseInt(request.getParameter("Consultant"));
        int Available_id = Integer.parseInt(request.getParameter("start_time"));
        String Note = request.getParameter("Note");
        String Type = "Online";
        
        if (CountryID == 0) {
        	errors1.add("Please Select Country.");
        }

        if (JobID == 0) {
        	errors1.add("Please Select Job.");
        }

        if (ConsultID == 0) {
        	errors1.add("Please Select Consultent.");
        }

        if (Available_id == 0) {
        	errors1.add("Please Select Time and Date.");
        }

        Jobseeker newJobseeker = new Jobseeker(firstName, lastName, email, phoneNumber);
        
        EntityValidator<Jobseeker> validator = new EntityValidator();
        List<String> errors = validator.validate(newJobseeker);
        
        if(!errors.isEmpty() || !errors1.isEmpty() ) {
        	request.setAttribute("errors", errors);
        	request.setAttribute("errors1", errors1);
        	request.setAttribute("newJobseeker", newJobseeker);
        	List<specialization> specializations = indexService.getAllSpecializations();
    		request.setAttribute("specializations", specializations);
    	    request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
        	JobSeekerInsertedID = indexService.addJobseeker(newJobseeker);
            System.out.println(JobSeekerInsertedID);
            
            Appointment appointment = new Appointment(ConsultID, JobSeekerInsertedID, Available_id, CountryID, JobID, Note, Type);
            
            EntityValidator<Appointment> validator1 = new EntityValidator();
            List<String> errors2 = validator1.validate(appointment);
            
            if(!errors2.isEmpty()|| !errors1.isEmpty()) {
            	System.out.println("!errors.isEmpty() part");
            	request.setAttribute("errors", errors2);
            	request.setAttribute("errors1", errors1);
            	List<specialization> specializations = indexService.getAllSpecializations();
        		request.setAttribute("specializations", specializations);
        	    request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else {
            	
            	indexService.addAppointment(appointment);
                request.getRequestDispatcher("IndexServelet?parameter=list").forward(request, response);
            }
        }
	}

	private void ShowIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<specialization> specializations = indexService.getAllSpecializations();
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
