<%--
  Created by IntelliJ IDEA.
  User: makar
  Date: 22.04.2017
  Time: 5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>report</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/report?trips_pkey=${trips_pkey}" method="post">
    <textarea name="message" rows="4" cols="55" wrap="virtual">We are not so interested about your opinion, but you may leave it here.</textarea>
    <input type="submit" name="send" value="Share your opinion" />
</form>
<a href="<c:url value="/j_spring_security_logout"/>">Logout!</a>
</body>
</html>

