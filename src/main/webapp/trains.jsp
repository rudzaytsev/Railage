<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
    <h1>Trains Page</h1>
    <div>
        <a href="/stations/all">Stations</a>
        <a href="/routes/all">Routes</a>
    </div>
    <table>
        <thead align="center">
        <tr>
            <td> Id </td>
            <td> Train Number </td>
            <td> Number of seats </td>
            <td> Train Rides </td>
        </tr>
        </thead>
        <tbody align="center">
        <c:forEach var="train" items="${trains}">
            <tr>
                <td>${train.id}</td>
                <td>${train.number}</td>
                <td>${train.seats}</td>
                <td><a href="/trains/${train.id}">Watch</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</body>
</html>
