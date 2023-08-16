<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
        if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
        response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>COLOMBO-ADMIN</title>
          <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
		  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
		  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
        </head>

        <body>
          <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
              <div class="container-fluid">
                <a class="navbar-brand" href="#">COLOMBO</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                  aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarText">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        User
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/UserServlet?parameter=list">List
                            Users</a></li>
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/UserServlet?parameter=showAddForm">Add
                            User</a></li>
                      </ul>
                    </li>

                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Appointment
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/AppointmentServlet?parameter=list&user_ID=0">List
                            Appointment</a></li>
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/AppointmentServlet?parameter=showAddForm&user_ID=0">Add
                            Appointment</a></li>
                      </ul>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Specialization
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/SpecializationServlet?parameter=list">List
                            Specialization</a></li>
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/SpecializationServlet?parameter=showAddForm">Add
                            Specialization</a></li>
                      </ul>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Job Seeker
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/JobseekerServlet?parameter=list">List
                            Job Seeker</a></li>
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/JobseekerServlet?parameter=showAddForm">Add
                            Job Seeker</a></li>
                      </ul>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Consultant Availability
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/ConsultantAvailabilityServlet?parameter=list&user_ID=0">List
                            Consultant Availability</a></li>
                        <li><a class="dropdown-item"
                            href="<%= request.getContextPath() %>/ConsultantAvailabilityServlet?parameter=showAddForm">Add
                            Consultant Availability</a></li>
                      </ul>
                    </li>
                  </ul>
                  <span class="navbar-text">
                    <%=roleName %>
                  </span>
                </div>
              </div>
            </nav>
          </header>
          <div class="container">

            <div class="row justify-content-center mt-5">
              <form action="<%=request.getContextPath()%>/AppointmentServlet?parameter=add" method="post">
                <div class="row justify-content-center">
                  <div class="col-9 mb-5 ">
                    <div class="card p-5">
                      <div class="card-hedding">
                        <h2>Make New Appointment</h2>
                      </div>
                      <div class="row mt-3">
                        <div class="col-6">
                          <span class="">Job Seeker</span>
                          <select class="form-select" name="Seeker" id="Seeker">
                            <c:forEach var="Jobseeker" items="${Jobseekers}">
                              <option value="${Jobseeker.getJobSeekers_ID()}">${Jobseeker.getFirst_Name()}
                                ${Jobseeker.getLast_Name()}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-6">
                          <span class="">Available Consultant</span>
                          <select class="form-select" name="Consultant" id="Consultant">
                           <option value="0">01. Place Select Consultant First </option>
                            <c:forEach var="user" items="${users}">
                              <option value="${user.getId()}">${user.getF_name()} ${user.getL_name()}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-6">
                          <span class="">Available Date</span>
                          <select class="form-select" name="DateAvailabilityId" id="DateAvailabilityId">
                            <option value="0">02. Place Select Date </option>
                          </select>
                        </div>
                        <div class="col-6">
                          <span class="">Available Time</span>
                          <select class="form-select" name="start_time" id="start_time">
                          <option value="0">03. Place Select Available Time </option>
                          </select>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-6">
                          <span class="">Select Country</span>
                          <select class="form-select" name="Country" id="Country">
                            <option value="0">04. Place Select Country </option>
                          </select>
                        </div>
                        <div class="col-6">
                          <span class="">Available Job</span>
                          <select class="form-select" name="Job" id="Job">
                          <option value="0">05. Place Select Job </option>
                          </select>
                        </div>
                      </div>
                      <div class="row mt-3">
                      	<div class="col-6">
                      		<label for="exampleFormControlTextarea1" class="form-label">Note</label>
  							<textarea class="form-control" id="Note" name="Note" rows="1"></textarea>
                      	</div>
                        <div class="col-6">
                          <span class="">Appointment Type</span>
                          <select class="form-select" name="Type" id="Type">
                            <option value="0"> Place Appointment Type </option>
                            <option value="PhoneCall"> Over the Phone </option>
                            <option value="Reception"> Reception </option>
                          </select>
                        </div>
                        
                      </div>
                      
                      <div class="row mt-3 justify-content-center">
                        <div class="col-3">
                          <input class="btn btn-dark" type="submit" value="Save">
                          <input class="btn btn-warning" type="reset" value="Clear">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
          crossorigin="anonymous"></script>
		<script>
		
		function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();

		    if (month.length < 2) 
		        month = '0' + month;
		    if (day.length < 2) 
		        day = '0' + day;

		    return [year, month, day].join('-');
		}
		
		$(document).ready(function () {
			  $("#Consultant").on("change", function () {
			    var selectedValue = $(this).val();
			    console.log(selectedValue);
			    $.ajax({
			      url: "<%=request.getContextPath()%>/ConsultantAvailabilityServlet",
			      type: "GET",
			      data: {
			        parameter: "getDates",
			        id: selectedValue
			      },
			      success: function (data) {
			        var dateAvailabilityId = $("#DateAvailabilityId");
			        dateAvailabilityId.empty();
			        $.each(data, function (index, item) {
			          dateAvailabilityId.append($("<option>").val(item.DATE).text(item.DATE));
			        });
			      },
			      error: function (xhr, status, error) {
			        console.error("AJAX request error:", error);
			      }
			    });

			    $.ajax({
			      url: "<%=request.getContextPath()%>/SpecializationServlet",
			      type: "GET",
			      data: {
			    	parameter: "GetCountryByUser",
			        User_id: selectedValue
			      },
			      success: function (data) {
			        var countryDropdown = $("#Country");
			        countryDropdown.empty();
			        $.each(data, function (index, item) {
			          countryDropdown.append($("<option>").val(item.Country_id).text(item.Country_Name));
			        });
			      },
			      error: function (xhr, status, error) {
			        console.error("AJAX request error:", error);
			      }
			    });
			  });
			});

			$("#DateAvailabilityId").on("change", function () {
		    	  var selectedValue = $(this).val();
		    	  console.log(selectedValue);
		    	  console.log(formatDate(selectedValue));
			        $.ajax({
			          url: "<%=request.getContextPath()%>/ConsultantAvailabilityServlet",
			          type: "GET",
			          data: {
			            parameter: "getTime",
			            date: formatDate(selectedValue)
			          },
			          success: function (data) {
			            var start_time = $("#start_time");
			            start_time.empty();
			            $.each(data, function (index, item) {
			            	start_time.append($("<option>").val(item.ID).text(item.Start_Time + " --> " +item.End_Time ));
			            });
			          },
			          error: function (xhr, status, error) {
			            console.error("AJAX request error:", error);
			          }
			        });
			      });
			
			$("#Country").on("change", function () {
		    	  var selectedValue = $(this).val();
		    	  console.log(selectedValue);
			        $.ajax({
			          url: "<%=request.getContextPath()%>/SpecializationServlet",
			          type: "GET",
			          data: {
			            parameter: "GetJobNameByCountry",
			            Coun_id: selectedValue
			          },
			          success: function (data) {
			            var job = $("#Job");
			            job.empty();
			            $.each(data, function (index, item) {
			            	job.append($("<option>").val(item.Job_Title_id).text(item.Job_Name));
			            });
			          },
			          error: function (xhr, status, error) {
			            console.error("AJAX request error:", error);
			          }
			        });
			      });
		      
		  </script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        </html>