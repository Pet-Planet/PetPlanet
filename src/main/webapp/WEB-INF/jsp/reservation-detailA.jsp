<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="header2.jsp" />
    <title>Reservation Detail</title>
    <style>
        .cancel {
            background-color: #B9E9FC;
            color: white;
            padding: 15px 30px;
            margin: 70px 50px;
            border: none;
            cursor: pointer;
            width: 200px;
            text-align: center;
            border-radius: 10px;
            font-size: 16px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="reservation-detail">
    <h2>No.${revDetail.revId}</h2>
    <h1>${revDetail.placeName}</h1>
    <p>장소: ${revDetail.address}</p>
    <p>예약일자: ${revDetail.startDate}</p>
    <p>예약시간: ${revDetail.time}</p>
    <p>예약자명: ${revDetail.revName}</p>
    <p>연락처: ${revDetail.phoneNum}</p>
    <p>이용인원: ${revDetail.guests}명 (${revDetail.pets}마리)</p>
    <p>총 결제금액: ${revDetail.amount}원</p>

    <button type="button" onclick="cancelRev(${revDetail.revId})" class="cancel">취 소</button>
</div>


<script>
    function cancelRev(revId) {
        if (confirm("예약을 취소하시겠습니까?")) {
            fetch(`/reservation/cancel/` + revId, {
                method: 'DELETE'
            })
                .then(response => {
                    alert("예약이 취소되었습니다.");
                    window.location.href = `/main/${memberId}`;
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert("예약 취소 중 오류가 발생했습니다.");
                });
        }
    }

</script>
</body>
</html>