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
</head>
<body>
<h1>Passengers</h1>
    <table>
        <thead align="center">
        <tr>
            <td> Id </td>
            <td> Name </td>
            <td> Lastname </td>
            <td> Birth date </td>
        </tr>
        </thead>
        <tbody align="center">
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
</body>
</html>
