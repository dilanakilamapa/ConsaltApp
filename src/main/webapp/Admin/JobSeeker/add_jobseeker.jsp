<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/JobseekerServlet?parameter=add" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>Create Job Seeker</h2>
              </div>
              <div class="row">
                <div class="col-6">
                  <span class="">First name</span>
                  <input class="form-control form-control-sm" type="text" name="firstName" id="firstName">
                </div>
                <div class="col-6">
                  <span class="">Last name</span>
                  <input class="form-control form-control-sm" type="text" name="lastName" id="lastName">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-12">
                  <span class="">Email</span>
                  <input class="form-control form-control-sm" type="email" name="email" id="email">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Phone Number</span>
                  <input class="form-control form-control-sm" type="number" name="phoneNumber" id="phoneNumber">
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