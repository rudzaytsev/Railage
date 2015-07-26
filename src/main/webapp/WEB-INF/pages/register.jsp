<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/register.css"/>">
</head>
<body>
    <div class="container">
        <div align="center">
            <h1 class="form-signin-heading">Register New User</h1>
        </div>
        <form:form id="registerform" class="form-signin" modelAttribute="user"
                   action="/railage/register/user" method="post">
            <label for="inputLogin">Login:</label>
            <input id="inputLogin" class="form-control" type="text" name="login"/>

            <label for="inputPassword" >Password:</label>
            <input id="inputPassword" type="password"  class="form-control" name="password" />

            <label for="inputRoles" >Roles:</label>
            <select id="inputRoles" class="form-control" name="role">
                <option selected value="CLIENT">Client</option>
                <option value="EMPLOYEE">Employee</option>
            </select>
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register">
        </form:form>
    </div>
</body>
</html>
