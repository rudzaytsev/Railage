<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 29.06.15
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Train Rides</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
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
                <li><a href="/rides/all/?trainId=${currentTrain.id}">Passengers of All Train Rides </a>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <c:if test="${isValidationError}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Error!</strong> ${errorMsg}
                </div>
            </c:if>
            <c:if test="${success}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Well Done!</strong> ${infoMsg}
                </div>
            </c:if>
            <h1 class="page-header">Train Rides</h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td> Id </td>
                        <td> Train Number </td>
                        <td> Start Station </td>
                        <td> End Station </td>
                        <td> RideDate </td>
                        <td> Passengers</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ride" items="${trainRides}">
                        <tr>
                            <td>${ride.id}</td>
                            <td>${ride.train.number}</td>
                            <td>${ride.route.getStartStation().name}</td>
                            <td>${ride.route.getEndStation().name}</td>
                            <td>${ride.date}</td>
                            <td><a href="/rides/${ride.id}" class="btn btn-info">View</a></td>
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
