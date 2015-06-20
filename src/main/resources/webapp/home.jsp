<%@ page contentType="text/html; charset=UTF-8" language="java" import="org.apache.shiro.SecurityUtils" %>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<body>
Hi
<shiro:guest>Guest</shiro:guest>
<shiro:user>
    <shiro:principal />
</shiro:user>
Value: [<c:out value="${obj}"/>]

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