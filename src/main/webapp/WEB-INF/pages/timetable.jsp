<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TimeTable</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/dashboard.css"/>">
</head>
<body>

    <%@ include file="navigation_panel.jsp" %>


    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                  <!-- -->
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">TimeTable for station ${currentStation.name}</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td> Id </td>
                            <td> Train </td>
                            <td> Departure Time </td>
                            <td> Time Period </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="timeTableLine" items="${timetable}">
                            <tr>
                                <td>${timeTableLine.id}</td>
                                <td>${timeTableLine.train.number}</td>
                                <td>${timeTableLine.timeInfo.departureTime}</td>
                                <td>${timeTableLine.timeInfo.period}</td>
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
