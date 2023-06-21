<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
    <jsp:include page="header2.jsp" />
</head>
<style>
    .rev-button {
        background-color: #98C0DC;
        color: white;
        padding: 15px 30px;
        margin: 100px 70px;
        border: none;
        cursor: pointer;
        width: 100px;
        text-align: center;
        border-radius: 10px;
        font-size: 16px;
        font-weight: bold;
    }

    .reservation-form {
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }

    .title {
        color: #98C0DC;
        margin-top: 50px;
        margin-bottom: 50px;
        font-size: 25px;
        font-weight: bold;
    }

    .reservation-details {
        text-align: center;
        display: flex;
        flex-direction: column;
        align-items: flex-start; /* 왼쪽 정렬 */
    }

    .reservation-details p {
        margin-bottom: 30px;
        text-align: left;
    }

    .reservation-details span {
        text-align: left;
        display: inline-block;
        width: 200px; /* 조정 가능한 값 */
    }

    .amount {
        font-size: 25px;
        margin-top: 50px;
    }

    .amount-value {
        color: red;
    }

    .reservation-details strong {
        width: 150px; /* 조정 가능한 값 */
        display: inline-block;
        margin-right: 30px;
    }
    .placeName{

        text-align: center;
    }

    .amount p {
        background-color: white; /* 사각형 배경색을 흰색으로 설정 */
        border: 2px solid #98C0DC; /* 테두리를 하늘색으로 설정 */
        border-radius: 10px; /* 둥근 모서리 설정 */
        padding: 10px; /* 내부 여백 설정 */
        text-align: center; /* 가운데 정렬 */
    }
</style>

<div class="reservation-form">
    <div class="title">
        <h1>입력정보 확인</h1>
    </div>
    <div class="placeName"><h1>${rev.placeName}</h1><br></div>
    <div class="reservation-details">
        <p><strong>체크인</strong>  <span>${rev.startDate}</span></p>
        <p><strong>체크아웃</strong> <span>${rev.endDate}</span></p>
        <p><strong>총인원수</strong> <span>${rev.guests}명 (동반 반려동물 ${rev.pets}마리)</span></p>
        <p><strong>예약자명</strong> <span>${rev.revName}</span></p>
        <p><strong>연락처</strong>   <span>${rev.phoneNum}</span></p>
        <div class="amount">
            <p><strong>결제금액</strong> <span class="amount-value"><fmt:formatNumber value="${rev.amount}" pattern="#,###"/>원</span></p>
        </div>
    </div>

    <form action="/reservation/${memberId}" method="post" onsubmit="return showReservConfirmation()">
        <input type="hidden" name="placeId" value="${rev.placeId}">
        <input type="hidden" name="placeName" value="${rev.placeName}">
        <input type="hidden" name="startDate" value="${rev.startDate}">
        <input type="hidden" name="endDate" value="${rev.endDate}">
        <input type="hidden" name="revName" value="${rev.revName}">
        <input type="hidden" name="phoneNum" value="${rev.phoneNum}">
        <input type="hidden" name="guests" value="${rev.guests}">
        <input type="hidden" name="pets" value="${rev.pets}">
        <input type="hidden" name="amount" value="${rev.amount}">

        <button type="submit" class="rev-button">예 약</button>
        <button type="button" onclick="history.back()" class="rev-button">수 정</button>
    </form>
</div>

<script>
    function showReservConfirmation() {
        alert("예약 되었습니다.");
        return true;
    }
</script>
</body>
</html>







