<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
</head>
<body>
<h1>Reservation Confirmation</h1>
<p>숙소명: ${rev.placeName}</p>
<p>체크인 날짜: ${rev.checkInDate}</p>
<p>체크아웃 날짜: ${rev.checkOutDate}</p>
<p>숙박 인원: ${rev.guests}명</p>
<p>결제 금액: ${rev.amount}원</p>

<%--헤더 정보가 안넘어감--%>
<%
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYW5ldWwwODA0QGRhdW0ubmV0Iiwibmlja25hbWUiOiLsoJXrr7zsp4AiLCJpZCI6MywiZXhwIjoxNjg3NjIwNjQ0fQ.eeI6ocSc4I3AFBqeId78XE7GFnRuY5h6QyZqRz6pDp_BquUOqBIefscIOo8rEA5QV4xpOJPgl4lMdj5HtHiMBg";
    response.setHeader("Authorization", token);
%>

<form action="/reservation/b" method="post" >
    <label>Name: <input type="text" name="revName" required></label><br>
    <label>Phone Number: <input type="text" name="phoneNum" required></label><br>
    <input type="hidden" name="checkInDate" value="${rev.checkInDate}">
    <input type="hidden" name="checkOutDate" value="${rev.checkOutDate}">
    <input type="hidden" name="amount" value="${rev.amount}">
    <input type="hidden" name="amount" value="${rev.guests}">
    <input type="hidden" name="amount" value="${rev.placeName}">

    <input type="submit" value="Reserve">
</form>
</body>
</html>






