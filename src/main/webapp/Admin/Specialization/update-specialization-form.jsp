<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/SpecializationServlet?parameter=update" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>ADD New Specialization</h2>
              </div>
              <div class="row">
                <div class="col-6">
                  <span class="">ID</span>
                  <input class="form-control form-control-sm" type="text" name="ID" id="ID" value="${spec.getId()}" disabled>
                </div>
                <div class="col-6">
                  <span class="">Select Consultant</span>
                  <select class="form-select" name="user" id="user" disabled>
                    <c:forEach var="user" items="${users}">
                      <option value="${user.getId()}">${user.getF_name()} ${user.getL_name()}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Select Country</span>
                  <select class="form-select" name="Country" id="Country">
                    <c:forEach var="country" items="${countries}">
                      <option value="${country.getId()}">${country.getName()}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="col-6">
                  <span class="">Select Job</span>
                  <select class="form-select" name="Job" id="Job">
                    <c:forEach var="job" items="${jobs}">
                      <option value="${job.getId()}">${job.getName()}</option>
                    </c:forEach>
                  </select>
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
    
    <script>
      const selectElement = document.getElementById('user');
      const valueToSelect = '${spec.getUser_ID()}';

      for (let i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === valueToSelect) {
          selectElement.options[i].selected = true;
          break;
        }
      }
    </script>
    
    <script>
      const selectElementCountry = document.getElementById('Country');
      const valueToSelectCountry = '${spec.getCountry_id()}';

      for (let i = 0; i < selectElementCountry.options.length; i++) {
        if (selectElementCountry.options[i].value === valueToSelectCountry) {
          selectElementCountry.options[i].selected = true;
          break;
        }
      }
    </script>
    
    <script>
      const selectElementJob = document.getElementById('Job');
      const valueToSelectJob = '${spec.getJob_Title_id()}';

      for (let i = 0; i < selectElementJob.options.length; i++) {
        if (selectElementJob.options[i].value === valueToSelectJob) {
          selectElementJob.options[i].selected = true;
          break;
        }
      }
    </script>