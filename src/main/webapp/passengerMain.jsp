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
<h2>Welcome to TaxiApp!</h2>
<h3>Create a new order:</h3>
<form method="post">
    <p>From:
        <input type="text" size="36" name="from">
    </p>
    <p>To:
        <input type="text" size="39" name="to">
    </p>
    <p>You are ready to pay:
        <input type="number" size="19" name="price">
    </p>
    <input type="submit" name="Order" value="Send" />
</form>
<h2>You active orders:</h2>
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
    <c:forEach items="${requestScope.currentTrips}" var="trip">
        <tr>
            <td><c:out value="${trip.trips_pkey}"></c:out></td>
            <td><c:out value="${trip.addressFrom}"></c:out></td>
            <td><c:out value="${trip.addressTo}"></c:out></td>
            <td><c:out value="${trip.price}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/passengerMain?trips_pkey_to_delete=${trip.trips_pkey}">Change my mind</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>History:</h2>
<table border="1">
    <thead>
    <tr>
        <th>Order number</th>
        <th>AddressFrom</th>
        <th>AddressTo</th>
        <th>Price</th>
        <th>Report</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.historicalTrips}" var="trip">
        <tr>
            <td><c:out value="${trip.trips_pkey}"></c:out></td>
            <td><c:out value="${trip.addressFrom}"></c:out></td>
            <td><c:out value="${trip.addressTo}"></c:out></td>
            <td><c:out value="${trip.price}"></c:out></td>
            <td><c:out value="${trip.status}"></c:out></td>
            <td><c:out value="${trip.report}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/passengerMain?trips_pkey=${trip.trips_pkey}">Send a comment</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
$(function() {
$("#number").mask("999999");
});
</html>