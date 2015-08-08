<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/error_pages.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>

    <title>Error 404</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Static navbar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
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
            <ul class="nav navbar-nav">

            </ul>
            <ul class="nav navbar-nav navbar-right">

            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>


<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>Error 404</h1>
        <p>Page not found</p>
        <p>Click on button for jump to login page</p>
        <p>
            <a class="btn btn-lg btn-primary" href="/railage/" role="button">Jump on login page &raquo;</a>
        </p>
    </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->

</body>
</html>

