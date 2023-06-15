<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Form</title>
</head>
<body>
<h1>Reservation Form</h1>
<form action="/reservation/check" method="post">
<%--   임의--%>
    <input type="hidden" name="placeId" value="5">
    <label>Check-in Date: <input type="date" name="checkInDate" required></label><br>
    <label>Check-out Date: <input type="date" name="checkOutDate" required></label><br>
    <label>Guests: <input type="number" name="guests" required></label><br>
    <input type="submit" value="Confirm">
</form>
</body>
</html>