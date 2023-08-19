<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/ConsultantAvailabilityServlet?parameter=update" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>Update Consultant Available Date/Time</h2>
              </div>
              <div class="row mt-3">
              <div class="col-6">
                  <span class="">Available ID</span>
                  <input class="form-control form-control-sm" type="text" name="id" id="id" value="${availability.getID()}">
                </div>
                <div class="col-6">
                  <span class="">Available Date</span>
                  <input class="form-control form-control-sm" type="DATE" name="DATE" id="DATE" value="${availability.getDATE()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Available Start Time</span>
                  <input class="form-control form-control-sm" type="time" name="start_Time" id="start_Time" value="${availability.getStart_Time()}">
                </div>
                <div class="col-6">
                  <span class="">Available End Time</span>
                  <input class="form-control form-control-sm" type="time" name="end_Time" id="end_Time" value="${availability.getEnd_Time()}">
                </div>
              </div>
              <div class="row mt-3 justify-content-center">
                <div class="col-3">
                  <input class="btn btn-dark" type="submit" value="Update">
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