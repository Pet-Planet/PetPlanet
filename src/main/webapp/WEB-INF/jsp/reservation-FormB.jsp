<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Form</title>
</head>
<body>
<h1>Reservation Form</h1>
<form action="/reservation/confirm/b" method="post">
    <input type="hidden" name="placeId" value="${placeId}">
    <label>체크인: <input type="date" name="startDate" required>
        체크아웃: <input type="date" name="endDate" ></label><br>
    <label>이용인원: <input type="number" name="guests" required>
        반려동물 수: <input type="number" name="pets" required></label><br>
    <label>예약자명: <input type="text" name="revName" required></label><br>
    <label>연락처: <input type="text" name="phoneNum" required></label><br>
    <input type="submit" value="작성완료">
</form>
</body>
</html>