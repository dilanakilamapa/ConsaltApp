<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row mt-4">
      <div class="col-6">
        <h1>List of Appointment </h1>
      </div>
    </div>
    <div class="row mt-5">
      <form action="<%=request.getContextPath()%>/AppointmentServlet?parameter=list" method="POST">
        <div class="col-6">
          <span class="">Select Consultant</span>
          <select class="form-select" name="user_ID" id="user_ID">
            <c:forEach var="user" items="${users}">
              <option value="${user.getId()}">${user.getF_name()} ${user.getL_name()}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-6 mt-3">
          <input class="btn btn-success" type="submit" value="Search">
        </div>
      </form>
    </div>
    <div class="row mt-5">
      <div class="col">
        <table class="table table-striped text-center">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Consultant Name</th>
              <th scope="col">JobSeeker Name</th>
              <th scope="col">Country</th>
              <th scope="col">Job</th>
              <th scope="col">Available Date</th>
              <th scope="col">Start Time</th>
              <th scope="col">End Time</th>
              <th scope="col">Note</th>
              <th scope="col">Appointment Type</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="appointment" items="${appointmentList}">
              <tr>
                <td>
                  <c:out value="${appointment.getAppointment_ID()}" />
                </td>
                <td>
                  <c:out value="${appointment.getConsultant_first_name()} ${appointment.getConsultant_last_name()}" />
                </td>
                <td>
                  <c:out value="${appointment.getJobSeeker_name()}" />
                </td>
                <td>
                  <c:out value="${appointment.getCountry_name()}" />
                </td>
                <td>
                  <c:out value="${appointment.getJob_name()}" />
                </td>
                <td>
                  <c:out value="${appointment.getDATE()}" />
                </td>
                <td>
                  <c:out value="${appointment.getStart_Time()}" />
                </td>
                <td>
                  <c:out value="${appointment.getEnd_Time()}" />
                </td>
                <td>
                  <c:out value="${appointment.getNote()}" />
                </td>
                <td>
                  <c:out value="${appointment.getAppointment_Type()}" />
                </td>
                <td><a class="btn btn-success"
                    href="ConsultantAvailabilityServlet?parameter=showUpdateForm&id=<c:out value='${appointment.getAppointment_ID()}' />">Edit</a>
                  <a class="btn btn-warning"
                    href="ConsultantAvailabilityServlet?parameter=delete&id=<c:out value='${appointment.getAppointment_ID()}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>


    <jsp:include page="../Common/DashboardBottom.jsp" />