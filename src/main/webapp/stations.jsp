<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
    <h1>Stations</h1>
    <table>
        <thead align="center">
        <tr>
            <td> Id </td>
            <td> Station Name </td>
            <td> Timetable </td>
        </tr>
        </thead>
        <tbody align="center">
        <c:forEach var="station" items="${stations}">
            <tr>
                <td>${station.id}</td>
                <td>${station.name}</td>
                <td><a href="/station/${station.id}">Watch</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
