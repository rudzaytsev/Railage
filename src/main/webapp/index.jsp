<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Railage</title>
</head>
    <body>
        <h2>Welcome to Railage Information System</h2>
        <h2>Please Login</h2>
        <table>
            <form id="loginform" action="LoginServlet" method="POST">
            <tr>
                <td> Login:  </td>
                <td> <input type="text" name="login" /> </td>
            </tr>
            <tr>
                <td> Password:  </td>
                <td> <input type="password" name="password" /> </td>
            </tr>
            <tr>
                <td  cols="2"> <input type="submit" value="Login" > </td>
            </tr>
            </form>
            <form id="registerform" action="LoginServlet" method="get">
                <tr>
                    <td  cols="2"> <input type="submit" value="Register" > </td>
                </tr>
            </form>
        </table>
    </body>
</html>

