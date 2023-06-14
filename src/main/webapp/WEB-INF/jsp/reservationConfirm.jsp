<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
</head>
<body>
<h1>Reservation Confirmation</h1>
<p>Check-in Date: ${rev.checkInDate}</p>
<p>Check-out Date: ${rev.checkOutDate}</p>
<p>Amount: ${rev.amount}</p>

<%--헤더 정보가 안넘어감--%>
<%
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYW5ldWwwODA0QGRhdW0ubmV0Iiwibmlja25hbWUiOiLsoJXrr7zsp4AiLCJpZCI6MywiZXhwIjoxNjg3MjcwMzU3fQ.42-Rj7EinOskZ02D7HfuFrz1e5J-LivURnoENvWqAkIdA5ZTc3rCmXW7DrcDBZBj_a6O7awym1Z8_MOwRyNodw";
    response.setHeader("Authorization", token);
%>
<form action="/reservation" method="post" >
    <label>Name: <input type="text" name="revName" required></label><br>
    <label>Phone Number: <input type="text" name="phoneNum" required></label><br>
    <input type="hidden" name="checkInDate" value="${rev.checkInDate}">
    <input type="hidden" name="checkOutDate" value="${rev.checkOutDate}">
    <input type="hidden" name="amount" value="${rev.amount}">

    <input type="submit" value="Reserve">
</form>
</body>
</html>






