<jsp:include page="../Common/DashboardTop.jsp" />
<% String roleName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie : cookies){
        if(cookie.getName().equals("role")) roleName=cookie.getValue(); } } if(roleName==null)
        response.sendRedirect("login.jsp"); %>

<jsp:include page="../Common/DashboardBottom.jsp" />
