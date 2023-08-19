<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/JobseekerServlet?parameter=update" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>Create Job Seeker</h2>
              </div>
              <div class="row">
                <div class="col-6">
                  <span class="">ID</span>
                  <input class="form-control form-control-sm" type="text" name="id" id="id" value="${jobseeker.getJobSeekers_ID()}" disabled>
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">First name</span>
                  <input class="form-control form-control-sm" type="text" name="firstName" id="firstName" value="${jobseeker.getFirst_Name()}">
                </div>
                <div class="col-6">
                  <span class="">Last name</span>
                  <input class="form-control form-control-sm" type="text" name="lastName" id="lastName" value="${jobseeker.getLast_Name()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-12">
                  <span class="">Email</span>
                  <input class="form-control form-control-sm" type="email" name="email" id="email" value="${jobseeker.getEmail()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Phone Number</span>
                  <input class="form-control form-control-sm" type="number" name="phoneNumber" id="phoneNumber" value="${jobseeker.getPhone_Number()}">
                </div>
              </div>
              <div class="row mt-3 justify-content-center">
                <div class="col-3">
                  <input class="btn btn-dark" type="submit" value="Save">
                  <input class="btn btn-warning" type="reset" value="Clear">
                </div>
              </div>
              <div class="row mt-3 justify-content-center">
                <div class="col-6">
	                <ul class="error text text-danger">
		                <c:forEach var="error" items="${errors}">
		                	<li>${error}</li>
		                </c:forEach>
	                </ul>
                </div>
                <div class="col-6">
	                <ul class="error text text-danger">
		                <c:forEach var="error" items="${errors1}">
		                	<li>${error}</li>
		                </c:forEach>
	                </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>

    <jsp:include page="../Common/DashboardBottom.jsp" />