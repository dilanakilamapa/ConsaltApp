<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row mt-4">
      <div class="col-6">
        <h1>List of Consultant Availability </h1>
      </div>
    </div>
    <div class="row mt-5">
      <form action="<%=request.getContextPath()%>/ConsultantAvailabilityServlet?parameter=list" method="POST">
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
              <th scope="col">Name</th>
              <th scope="col">Date</th>
              <th scope="col">Start Time</th>
              <th scope="col">End Time</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="availabilitie" items="${availabilities}">
              <tr>
                <td>
                  <c:out value="${availabilitie.getID()}" />
                </td>
                <td>
                  <c:out value="${availabilitie.getF_name()} ${availabilitie.getL_name()}" />
                </td>
                <td>
                  <c:out value="${availabilitie.getDATE()}" />
                </td>
                <td>
                  <c:out value="${availabilitie.getStart_Time()}" />
                </td>
                <td>
                  <c:out value="${availabilitie.getEnd_Time()}" />
                </td>
                <td><a class="btn btn-success"
                    href="ConsultantAvailabilityServlet?parameter=showUpdateForm&id=<c:out value='${availabilitie.getID()}' />">Edit</a>
                  <a class="btn btn-warning"
                    href="ConsultantAvailabilityServlet?parameter=delete&id=<c:out value='${availabilitie.getID()}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>


    <jsp:include page="../Common/DashboardBottom.jsp" />