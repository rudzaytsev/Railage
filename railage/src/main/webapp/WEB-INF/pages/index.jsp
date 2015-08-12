<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Railage</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/login.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>
</head>
    <body>
        <div class="container">
            <div align="center">
                <h1 class="form-signin-heading">Welcome to Railage Information System</h1>
            </div>
            <c:if test="${isValidationError}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Error!</strong> ${errorMsg}
                </div>
            </c:if>
            <div id="login_form_div">
            <form id="loginform" class="form-signin"
                  action="<c:url value="/"/>" method="post">

                <h2 class="form-signin-heading">Please log in</h2>
                <label for="inputLogin" class="sr-only">Login</label>
                <input type="text" id="inputLogin" class="form-control"
                       name="login" placeholder="Login" required autofocus  />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control"
                       name="password" placeholder="Password" required autofocus  />
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" >
            </form>
            </div>
            <sec:authorize access="isAnonymous()">
                <div id="registerformdiv">
                <form:form id="registerform" class="form-signin" action="/railage/registration" method="get">
                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register" >
                </form:form>
                </div>
            </sec:authorize>
        </div>
    </body>
</html>

