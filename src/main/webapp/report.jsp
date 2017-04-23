<%--
  Created by IntelliJ IDEA.
  User: makar
  Date: 22.04.2017
  Time: 5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>report</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/passengerMain/report" method="post">
    <textarea name="mesage" rows="4" cols="55" wrap="virtual">Не то чтобы нам было важно ваше мнение о поездке, но вы можете его оставить тут, мы ознакомимся.</textarea>
    <input type="submit" name="send" value="Share your opinion" />
</form>
</body>
</html>

