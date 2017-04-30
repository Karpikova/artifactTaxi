<%--
  Created by IntelliJ IDEA.
  User: makar
  Date: 20.04.2017
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>driverMain</title>
</head>
<body>
<h2>Welcome to TaxiApp!
You can take any order:</h2>
<table border="1">
    <thead>
    <tr>
        <th>Order number</th>
        <th>AddressFrom</th>
        <th>AddressTo</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.allTrips}" var="trip">
        <tr>
            <td><c:out value="${trip.trips_pkey}"></c:out></td>
            <td><c:out value="${trip.addressFrom}"></c:out></td>
            <td><c:out value="${trip.addressTo}"></c:out></td>
            <td><c:out value="${trip.price}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/driver?trips_pkey_takeIn=${trip.trips_pkey}">Take in</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Your orders:</h2>
<table border="1">
    <thead>
    <tr>
        <th>Order number</th>
        <th>AddressFrom</th>
        <th>AddressTo</th>
        <th>Price</th>
        <th>Status</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.myTrips}" var="trip">
        <tr>
            <td><c:out value="${trip.trips_pkey}"></c:out></td>
            <td><c:out value="${trip.addressFrom}"></c:out></td>
            <td><c:out value="${trip.addressTo}"></c:out></td>
            <td><c:out value="${trip.price}"></c:out></td>
            <td><c:out value="${trip.status}"></c:out></td>
            <td><c:out value="${trip.dateStart}"></c:out></td>
            <c:if test="${trip.status!= 'Excecuted'}">
                <td><a href="${pageContext.request.contextPath}/driver?trips_pkey_done=${trip.trips_pkey}">Done</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/driver?logout=1">Log out</a>
</body>
</html>
