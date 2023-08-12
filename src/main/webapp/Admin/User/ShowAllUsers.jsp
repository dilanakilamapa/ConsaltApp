<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row mt-4">
      <div class="col-6">
        <h1>List of Users</h1>
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
              <th scope="col">Address</th>
              <th scope="col">Contact 01</th>
              <th scope="col">Contact 02</th>
              <th scope="col">Date of Birth</th>
              <th scope="col">Role</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="User" items="${users}">
              <tr>
                <td>
                  <c:out value="${User.getId()}" />
                </td>
                <td>
                  <c:out value="${User.getF_name()}" />
                </td>
                <td>
                  <c:out value="${User.getL_name()}" />
                </td>
                <td>
                  <c:out value="${User.getAddress()}" />
                </td>
                <td>
                  <c:out value="${User.getContact_01()}" />
                </td>
                <td>
                  <c:out value="${User.getContact_02()}" />
                </td>
                <td>
                  <c:out value="${User.getDOB()}" />
                </td>
                <td>
                  <c:out value="${User.getRole_id()}" />
                </td>
                <td><a class="btn btn-success"
                    href="UserServlet?parameter=showUpdateForm&id=<c:out value='${User.getId()}' />">Edit</a>
                   <a class="btn btn-warning"
                    href="UserServlet?parameter=delete&id=<c:out value='${User.getId()}' />">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>


    <jsp:include page="../Common/DashboardBottom.jsp" />