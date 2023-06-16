<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Success</title>
</head>

<body>
<h1>Reservation Success Page</h1>
<p>숙소명: ${rev.placeName}</p>
<%--<p>체크인 날짜: ${rev.checkInDate}</p>--%>
<%--<p>체크아웃 날짜: ${rev.checkOutDate}</p>--%>
<p>방문 날짜: ${rev.visitDate}</p>
<p>방문 시간: ${rev.time}</p>
<p>숙박 인원: ${rev.guests}명</p>
<p>예약자: ${rev.revName}</p>
<p>전화번호: ${rev.phoneNum}</p>
<%--<p>결제 금액: ${rev.amount}원</p>--%>
</body>
</html>