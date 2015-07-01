<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TimeTable</title>
</head>
<body>
<h1>Trains Page</h1>
<div>
    <a href="/stations/all">Stations</a>
</div>
    <table>
        <thead align="center">
        <tr>
            <td> Id </td>
            <td> Train </td>
            <td> Departure Time </td>
        </tr>
        </thead>
        <tbody align="center">
        <c:forEach var="timeTableLine" items="${timetable}">
            <tr>
                <td>${timeTableLine.id}</td>
                <td>${timeTableLine.train.number}</td>
                <td>${timeTableLine.timeInfo.departureTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
