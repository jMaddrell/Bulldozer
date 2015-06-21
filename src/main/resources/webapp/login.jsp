<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Please Login</title>
</head>
<body>

<table>
    <form name="loginForm" action="" method="post">
        <table cellspacing="0" cellpadding="3">
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" maxlength="30"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" maxlength="30"></td>
            </tr>
            <tr>
                <td colspan="2" align="left"><input type="checkbox" name="rememberMe" value="true"><font size="2">Remember Me</font></td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit" name="submit" value="Login"></td>
            </tr>
        </table>
    </form>
</table>

<hr />
<ul>
    <li>
        <a href="/home">Home</a>
    </li>
    <li>
        <a href="/login">Login</a>
    </li>
    <li>
        <a href="/register">Register</a>
    </li>
</ul>

</body>
</html>