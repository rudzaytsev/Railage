<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 22.07.15
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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
                <li><a href="/railage/trains/all">Trains</a></li>
                <li><a href="/railage/stations/all">Stations</a></li>
                <li><a href="/railage/rides/all">Rides</a></li>
                <li><a href="/railage/routes/all">Routes</a></li>
                <li><a href="/railage/logout">Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
