<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 02.07.15
  Time: 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Routes</title>
</head>
<body>
    <h1>Routes</h1>
    <table>
        <thead align="center">
        <tr>
            <td> Id </td>
            <td> Start Station </td>
            <td> End Station </td>
            <td> Plying train </td>
            <td> Station list </td>
        </tr>
        </thead>
        <tbody align="center">
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>${route.id}</td>
                <td>${route.getStartStation().name}</td>
                <td>${route.getEndStation().name}</td>
                <td>${route.train.number}</td>
                <td><a href="/routeparts/${route.id}">Watch</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
