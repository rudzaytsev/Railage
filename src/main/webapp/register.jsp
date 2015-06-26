<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register Page</h1>
    <table>
        <form id="registerform" action="/RegisterServlet" method="post">
        <tr>
            <td>Login:</td>
            <td> <input type="text" name="login" /> </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td> <input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td>Roles:</td>
            <td>
                <select name="role">
                    <option selected value="CLIENT">Client</option>
                    <option value="EMPLOYEE">Employee</option>
                </select>
            </td>
        </tr>
        <tr>
            <td  cols="2"> <input type="submit" value="Register" > </td>
        </tr>
        </form>
    </table>
</body>
</html>
