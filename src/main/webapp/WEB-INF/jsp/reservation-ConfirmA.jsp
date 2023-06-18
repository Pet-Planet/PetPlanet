<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
</head>
<body>
<h1>Reservation Confirmation</h1>
<p>장소명: ${rev.placeName}</p>
<p>예약일자: ${rev.startDate}</p>
<p>예약시간: ${rev.time}</p>
<p>예약자명: ${rev.revName}</p>
<p>연락처: ${rev.phoneNum}</p>
<p>이용인원: ${rev.guests}명</p>
<p>동반 반려동물 수: ${rev.pets}마리</p>
<p>총 이용금액: ${rev.amount}원</p>


<form action="/reservation/${memberId}" method="post" >
    <input type="hidden" name="placeId" value="${rev.placeId}">
    <input type="hidden" name="placeName" value="${rev.placeName}">
    <input type="hidden" name="startDate" value="${rev.startDate}">
    <input type="hidden" name="time" value="${rev.time}">
    <input type="hidden" name="revName" value="${rev.revName}">
    <input type="hidden" name="phoneNum" value="${rev.phoneNum}">
    <input type="hidden" name="guests" value="${rev.guests}">
    <input type="hidden" name="pets" value="${rev.pets}">
    <input type="hidden" name="amount" value="${rev.amount}">

    <input type="submit" value="예약하기">
</form>
</body>
</html>