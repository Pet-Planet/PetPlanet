<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="header2.jsp" />
    <title>Reservation Detail</title>
    <style>
        .title {
            color: #B9E9FC;
            margin-top: 50px;
            font-size: 25px;
            font-weight: bold;
        }

        .placeName{
            font-size: 20px;
        }

        .placeNo h2,
        .placeName h1 {
            margin-top: 0;
            margin-bottom: 10px; /* 줄간격을 조절합니다. */
        }

        .cancel {
            background-color: #B9E9FC;
            color: white;
            padding: 15px 30px;
            margin-top: 100px;
            margin-left: auto;
            margin-right: auto;
            border: none;
            cursor: pointer;
            width: 250px;
            text-align: center;
            border-radius: 10px;
            font-size: 16px;
            font-weight: bold;
        }

        .container {
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .reservation-detail {
            text-align: left;
            margin-top: 20px;
            margin-bottom: 100px;
            border: 3px solid #B9E9FC;
            border-radius: 10px;
            background-color: white;
            padding: 50px;
            width: 750px;
            height: 750px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .reservation-info {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .reservation-info p {
            margin: 0;
            padding: 15px 0; /*글자 아래위 간격*/
            font-size: 20px;
        }

        .reservation-info p span {
            display: inline-block;
            width: 100px;
            text-align: left;
            padding-right: 100px;   /*탭 간격*/
            color: darkgray;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title"><h1>예약 관리</h1></div>
    <div class="reservation-detail">
        </br><span class="placeNo"><h2>No. ${revDetail.revId}</h2></span>
        <span class="placeName"><h1>${revDetail.placeName}</h1></span></br></br>
        <div class="reservation-info">
            <p><span>장소</span>${revDetail.address}</p>
            <p><span>체크인</span>${revDetail.startDate}</p>
            <p><span>체크아웃</span>${revDetail.endDate}</p>
            <p><span>이용인원</span>${revDetail.guests}명 (${revDetail.pets}마리)</p>
            <p><span>예약자명</span>${revDetail.revName}</p>
            <p><span>연락처</span>${revDetail.phoneNum}</p>
            <p><span>결제금액</span>${revDetail.amount}원</p>
        </div>
        <button type="button" onclick="cancelRev(${revDetail.revId})" class="cancel">취 소</button>
    </div>
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