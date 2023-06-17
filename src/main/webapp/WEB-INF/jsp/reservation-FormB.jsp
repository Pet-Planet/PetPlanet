<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Form</title>
</head>
<body>
<h1>Reservation Form</h1>
<form action="/reservation/confirm/b" method="post">
<%--   임의--%>
    <input type="hidden" name="placeId" value="5">
    <label>Check-in Date: <input type="date" name="startDate" required>
        Check-out Date: <input type="date" name="endDate" required></label><br>
    <label><input type="tim" name="time" ></label><br>

    <label>Guests: <input type="number" name="guests" required></label><br>
    <input type="submit" value="등록">
</form>
</body>
</html>