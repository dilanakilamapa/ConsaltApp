<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie :
            cookies){ if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
            response.sendRedirect(request.getContextPath()+"/Admin/Dashboard/login.jsp"); %>
  <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>COLOMBO-ADMIN</title>
                <link rel="stylesheet" href="/css/style.css">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
                    crossorigin="anonymous">
                
            </head>
            <body>
                <header>
                    <nav class="navbar navbar-expand-lg bg-body-tertiary">
                        <div class="container-fluid">
                            <a class="navbar-brand" href="#">COLOMBO</a>
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false"
                                aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarText">
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                </ul>
                                <span class="navbar-text">
                                    <%=roleName %>
                                </span>
                            </div>
                        </div>
                    </nav>
                </header>
                <div class="container">
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
                      <option value="${user.getId()}">${user.getF_name()} ${user.getL_name()}</option>
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
    
	    const now = new Date();
	    const currentTime = now.toTimeString().slice(0, 5);
	    
	    now.setHours(now.getHours() + 5);
	    const updatedTime = now.toTimeString().slice(0, 5);
	
	    document.getElementById("start_Time").value = currentTime;
	    document.getElementById("end_Time").value = updatedTime;
	</script>