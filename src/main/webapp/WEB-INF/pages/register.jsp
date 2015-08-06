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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>
</head>
<body>
    <div class="container">
        <div align="center">
            <h1 class="form-signin-heading">Register New User</h1>
        </div>
        <c:if test="${isValidationError}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Error!</strong> ${errorMsg}
            </div>
        </c:if>
        <form:form id="registerform" class="form-signin" modelAttribute="user"
                   action="/railage/register/user" method="post">
            <label for="inputLogin">Login:</label>
            <input id="inputLogin" class="form-control" type="text" name="login"/>

            <label for="inputPassword" >Password:</label>
            <input id="inputPassword" type="password"  class="form-control" name="password" />

            <label for="inputRoles" >Roles:</label>
            <select id="inputRoles" class="form-control" name="role">
                <option selected value="ROLE_CLIENT">Client</option>
                <option value="ROLE_EMPLOYEE">Employee</option>
            </select>
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register">
        </form:form>
    </div>
</body>
</html>
