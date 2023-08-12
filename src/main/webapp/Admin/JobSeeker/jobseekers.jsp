<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row mt-4">
      <div class="col-6">
        <h1>List of Job Seekers</h1>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col">
        <table class="table table-striped text-center">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email</th>
              <th scope="col">Phone Number</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="jobseeker" items="${jobseekers}">
              <tr>
                <td>
                  <c:out value="${jobseeker.getJobSeekers_ID()}" />
                </td>
                <td>
                  <c:out value="${jobseeker.getFirst_Name()}" />
                </td>
                <td>
                  <c:out value="${jobseeker.getLast_Name()}" />
                </td>
                <td>
                  <c:out value="${jobseeker.getEmail()}" />
                </td>
                <td>
                  <c:out value="${jobseeker.getPhone_Number()}" />
                </td>
                <td><a class="btn btn-success"
                    href="JobseekerServlet?parameter=showUpdateForm&id=<c:out value='${jobseeker.getJobSeekers_ID()}' />">Edit</a>
                   <a class="btn btn-warning"
                    href="JobseekerServlet?parameter=delete&id=<c:out value='${jobseeker.getJobSeekers_ID()}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>


    <jsp:include page="../Common/DashboardBottom.jsp" />