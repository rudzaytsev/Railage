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
</head>
<body>
<h1>Train Rides Page</h1>
<a href="/rides/all/?trainId=${currentTrain.id}">View Passengers of All Rides </a>
<table>
    <thead align="center">
    <tr>
        <td> Id </td>
        <td> Train Number </td>
        <td> Route </td>
        <td> RideDate </td>
        <td> Passengers</td>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach var="ride" items="${trainRides}">
        <tr>
            <td>${ride.id}</td>
            <td>${ride.train.number}</td>
            <td>
                ${ride.route.getStartStation().name}
                - ${ride.route.getEndStation().name}
            </td>
            <td>${ride.date}</td>
            <td><a href="/rides/${ride.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
