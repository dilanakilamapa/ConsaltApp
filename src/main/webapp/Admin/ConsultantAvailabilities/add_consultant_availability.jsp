<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../Common/DashboardTop.jsp" />
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
    if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
    response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>

    <div class="row justify-content-center mt-5">
      <form action="<%=request.getContextPath()%>/ConsultantAvailabilityServlet?parameter=add" method="post">
        <div class="row justify-content-center">
          <div class="col-9 mb-5 ">
            <div class="card p-5">
              <div class="card-hedding">
                <h2>ADD Consultant Available Date/Time</h2>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Select Consultant</span>
                  <select class="form-select" name="user_ID" id="user_ID">
                    <c:forEach var="user" items="${users}">
                      <option value="${user.getId()}">${user.getF_name()} ${user.getL_name()}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="col-6">
                  <span class="">Available Date</span>
                  <input class="form-control form-control-sm" type="DATE" name="DATE" id="DATE">
                </div>
              </div>
              <div class="row mt-3">
                <div class="col-6">
                  <span class="">Available Start Time</span>
                  <input class="form-control form-control-sm" type="time" name="start_Time" id="start_Time">
                </div>
                <div class="col-6">
                  <span class="">Available End Time</span>
                  <input class="form-control form-control-sm" type="time" name="end_Time" id="end_Time">
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