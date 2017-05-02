<%--
  Created by IntelliJ IDEA.
  User: makar
  Date: 20.04.2017
  Time: 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login</title>
</head>
<c:if test="${success == true}"> Success! You can log in right now! </c:if>
<form method="post" action="/taxi/j_spring_security_check">
    Login:
        <input type="text" name="j_username">
    Password:
        <input type="text" name="j_password">
    <input type="submit" name="login_button" value="Log in">
</form>

<c:if test="${success != true}">
    <a href="${pageContext.request.contextPath}?toAuth=1">First time here, want to enjoy as a passenger</a><br>
    <a href="${pageContext.request.contextPath}?toAuth=2">First time here, want to enjoy as a driver</a><br><br><br>
</c:if>
<form method="get">
    <c:if test="${param.toAuth != null}">
        Login:
        <input type="text" name="loginNew" required="required">
        Password:
        <input type="text" name="passwordNew" pattern="[a-zA-Z0-9\d]{8,100}" title="Not less 8 letters, please" required="required"><br><br>
        Your name:
            <input type="text" name="fullName" required="required">
        Your birthday:
            <input type="date" name="birth" required="required"><br><br>
    </c:if>
    <c:if test="${param.toAuth == 2}">
        Your car number:
        <input type="text" name="carNumber" required="required">
        Car description:
        <input type="text" name="carDescription" required="required">
        Your passport number:
        <input type="text" name="passport" required="required">
    </c:if><br><br>

    <c:if test="${param.toAuth != null}">
    <input type="submit" name="createAnAccount" value="I promice I'll behave. Create an account.">
    </c:if>
</form>
</html>
