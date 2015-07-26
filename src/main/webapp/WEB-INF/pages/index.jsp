<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Railage</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/login.css"/>">
</head>
    <body>
        <div class="container">
            <div align="center">
                <h1 class="form-signin-heading">Welcome to Railage Information System</h1>
            </div>
            <div id="login_form_div">
            <form:form id="loginform" class="form-signin" modelAttribute="user"
                  action="/railage/login" method="post">

                <h2 class="form-signin-heading">Please log in</h2>
                <label for="inputLogin" class="sr-only">Login</label>
                <input type="text" id="inputLogin" class="form-control"
                       name="login" path="login" placeholder="Login" required autofocus  />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control"
                       name="password" path="password" placeholder="Password" required autofocus  />
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" >
            </form>
            </div>
            <div id="registerformdiv">
            <form id="registerform" class="form-signin" action="/railage/registration" method="get">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register" >
            </form:form>
            </div>
        </div>
    </body>
</html>

