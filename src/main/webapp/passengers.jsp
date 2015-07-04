<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Passengers</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/dashboard.css">
</head>
<body>


    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Railage</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/trains/all">Trains</a></li>
                    <li><a href="/stations/all">Stations</a></li>
                    <li><a href="/routes/all">Routes</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </div>
        </div>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <!-- -->
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">Passengers</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td> Id </td>
                            <td> Name </td>
                            <td> Lastname </td>
                            <td> Birth date </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="passenger" items="${passengers}">
                            <tr>
                                <td>${passenger.id}</td>
                                <td>${passenger.name}</td>
                                <td>${passenger.lastName}</td>
                                <td>${passenger.getFormattedBirthDate()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
