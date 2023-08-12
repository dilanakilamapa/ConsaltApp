<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
                                            role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            User
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                            <li><a class="dropdown-item"
                                                    href="<%= request.getContextPath() %>/UserServlet?parameter=list">List
                                                    Users</a></li>
                                            <li><a class="dropdown-item"
                                                    href="<%= request.getContextPath() %>/UserServlet?parameter=showAddForm">Add
                                                    User</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Appoinment</a>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
                                            role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            Specialization
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                            <li><a class="dropdown-item"
                                                    href="<%= request.getContextPath() %>/SpecializationServlet?parameter=list">List
                                                    Specialization</a></li>
                                            <li><a class="dropdown-item"
                                                    href="<%= request.getContextPath() %>/SpecializationServlet?parameter=showAddForm">Add
                                                    Specialization</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <span class="navbar-text">
                                    <%=roleName %>
                                </span>
                            </div>
                        </div>
                    </nav>
                </header>
                <div class="container">