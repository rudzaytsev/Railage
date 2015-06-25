<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mobile Inventory System</title>
</head>
<body>
<h1>Please Login</h1>
<form id="loginform" action="LoginServlet" method="POST">
    <table>
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
    </table>
</form>
</body>
</html>

