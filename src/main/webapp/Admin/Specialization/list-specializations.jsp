<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row mt-4">
      <div class="col-6">
        <h1>List of Specialization</h1>
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
              <th scope="col">Country</th>
              <th scope="col">Job</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="spec" items="${specList}">
              <tr>
                <td>
                  <c:out value="${spec.getId()}" />
                </td>
                <td>
                  <c:out value="${spec.getUser_F_Name()}" />
                </td>
                <td>
                  <c:out value="${spec.getUser_L_Name()}" />
                </td>
                <td>
                  <c:out value="${spec.getCountry_Name()}" />
                </td>
                <td>
                  <c:out value="${spec.getJob_Name()}" />
                </td>
                <td><a class="btn btn-success"
                    href="SpecializationServlet?parameter=showUpdateForm&id=<c:out value='${spec.getId()}' />">Edit</a>
                   <a class="btn btn-warning"
                    href="SpecializationServlet?parameter=delete&id=<c:out value='${spec.getId()}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>


    <jsp:include page="../Common/DashboardBottom.jsp" />