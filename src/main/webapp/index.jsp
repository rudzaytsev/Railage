<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Railage</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/login.css">
</head>
    <body>
        <div class="container">
            <div align="center">
                <h1 class="form-signin-heading">Welcome to Railage Information System</h1>
            </div>
            <div id="loginformdiv">
            <form id="loginform" class="form-signin" action="LoginServlet" method="POST">
                <h2 class="form-signin-heading">Please log in</h2>
                <label for="inputLogin" class="sr-only">Email address</label>
                <input type="text" id="inputLogin" class="form-control" name="login" placeholder="Login" required autofocus  />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required autofocus  />
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" >
            </form>
            </div>
            <div id="registerformdiv">
            <form id="registerform" class="form-signin" action="LoginServlet" method="get">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register" >
            </form>
            </div>
        </div>
    </body>
</html>

