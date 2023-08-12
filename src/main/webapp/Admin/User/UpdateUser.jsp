<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/UserServlet?parameter=update" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>Update User</h2>
              </div>
              <div class="row">
                <div class="col-12">
                  <span class="">User ID</span>
                  <input class="form-control form-control-sm" type="text" name="ID" id="ID" value="${user.getId()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6 ">
                  <span class="">First name</span>
                  <input class="form-control form-control-sm" type="text" name="F_name" id="F_name"
                    value="${user.getF_name()}">
                </div>
                <div class="col-6">
                  <span class="">Last name</span>
                  <input class="form-control form-control-sm" type="text" name="L_name" id="L_name"
                    value="${user.getL_name()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-12">
                  <span class="">Address</span>
                  <input class="form-control form-control-sm" type="text" name="Address" id="Address"
                    value="${user.getAddress()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Phone Number 01</span>
                  <input class="form-control form-control-sm" type="number" name="phone_number01" id="phone_number01"
                    value="${user.getContact_01()}">
                </div>
                <div class="col-6">
                  <span class="">Phone Number 02</span>
                  <input class="form-control form-control-sm" type="number" name="phone_number02" id="phone_number02"
                    value="${user.getContact_02()}">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Date of Birth</span>
                  <input class="form-control form-control-sm" type="date" name="DOB" id="DOB" value="${user.getDOB()}">
                </div>
                <div class="col-6">
                  <span class="">Select Role</span>
                  <select class="form-select" name="role_id" id="role_id">
                    <c:forEach var="role" items="${roles}">
                      <option value="${role.getId()}">${role.getRole_name()}</option>
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
            </div>
          </div>
        </div>
      </form>
    </div>

    <jsp:include page="../Common/DashboardBottom.jsp" />
    <script>
      const selectElement = document.getElementById('role_id');
      const valueToSelect = '${user.getRole_id()}';

      for (let i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === valueToSelect) {
          selectElement.options[i].selected = true;
          break;
        }
      }
    </script>