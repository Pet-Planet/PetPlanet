<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
</head>
<body>
<h1>Reservation Confirmation</h1>
<p>숙소명: ${rev.placeName}</p>
<p>체크인 날짜: ${rev.startDate}</p>
<p>체크아웃 날짜: ${rev.endDate}</p>
<p>예약자명: ${rev.revName}</p>
<p>연락처: ${rev.phoneNum}</p>
<p>투숙객 인원: ${rev.guests}명</p>
<p>동반 반려동물 수: ${rev.pets}마리</p>
<p>총 투숙금액: ${rev.amount}원</p>


<form action="/reservation/${memberId}" method="post" >
    <input type="hidden" name="placeId" value="${rev.placeId}">
    <input type="hidden" name="placeName" value="${rev.placeName}">
    <input type="hidden" name="startDate" value="${rev.startDate}">
    <input type="hidden" name="endDate" value="${rev.endDate}">
    <input type="hidden" name="revName" value="${rev.revName}">
    <input type="hidden" name="phoneNum" value="${rev.phoneNum}">
    <input type="hidden" name="guests" value="${rev.guests}">
    <input type="hidden" name="pets" value="${rev.pets}">
    <input type="hidden" name="amount" value="${rev.amount}">

    <input type="submit" value="예약하기">
</form>
</body>
</html>






