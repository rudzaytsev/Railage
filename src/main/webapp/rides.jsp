<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 29.06.15
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train Rides</title>
</head>
<body>
<h1>Trains Page</h1>
<table>
    <thead align="center">
    <tr>
        <td> Id </td>
        <td> Train Number </td>
        <td> Route </td>
        <td> RideDate </td>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach var="ride" items="${trains.getRides()}">
        <tr>
            <td>${ride.id}</td>
            <td>${ride.train.number}</td>
            <td>ROUTE</td>
            <td>${ride.rideDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
