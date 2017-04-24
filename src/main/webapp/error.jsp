<%--
  Created by IntelliJ IDEA.
  User: makar
  Date: 23.04.2017
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>
    <% String message = (String) request.getAttribute("message");%>
    We are sorry, we screwed up. If you wanna know what happened, we can tell you: <br>
    <%=message%>
</body>
</html>
