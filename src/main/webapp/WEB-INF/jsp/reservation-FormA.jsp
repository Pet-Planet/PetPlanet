<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation Form</title>
    <jsp:include page="header2.jsp" />
    <style>
        .reservation-form {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            justify-content: center; /* 가운데 정렬 */
        }

        .reservation-form .input-row {
            display: flex;
            align-items: center;
            margin-bottom: 30px; /* 줄간격을 조정할 값 */
        }

        .reservation-form .input-row span {
            margin-right: 40px; /* 탭 간격 조정 */
            text-align: left;
            min-width: 150px; /* 필요한 경우 폭 조정 */
            font-weight: bold;
        }

        .reservation-form select,
        .reservation-form input[type="date"],
        .reservation-form input[type="time"],
        .reservation-form input[type="number"],
        .reservation-form input[type="text"] {
            width: 200px; /* 필요한 경우 가로 크기 조정 */
            height: 30px; /* 필요한 경우 세로 크기 조정 */
            box-sizing: border-box;
        }


        .title{
            font-size: 25px;
            color: #98C0DC;
            margin-bottom: 50px;
        }

        .rev-button{

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
    </style>
</head>

<body>
<div class="reservation-form">
    <div class="title"><h1>예약정보 작성</h1></div>
    <form action="/reservation/${memberId}/confirm/a" method="post">
        <input type="hidden" name="placeId" value="${placeId}">

        <div class="input-row">
            <span>예약날짜</span>
            <input type="date" id="startDate" name="startDate" required>
        </div>

        <div class="input-row">
            <span>예약시간</span>
            <select id="time" name="time" required>
                <option value="">시간을 선택해주세요</option>
            </select>
        </div>

        <div class="input-row">
            <span>인원</span>
            <input type="number" name="guests" required min="1">
        </div>

        <div class="input-row">
            <span>반려동물</span>
            <input type="number" name="pets" min="1" required>
        </div>

        <div class="input-row">
            <span>예약자명</span>
            <input type="text" name="revName" placeholder="성함을 입력해주세요" required>
        </div>

        <div class="input-row">
            <span>연락처</span>
            <input type="text" name="phoneNum" placeholder="핸드폰 번호를 입력해주세요" required>
        </div>

        <button type="submit" class="rev-button">작 성</button>
        <button type="button" onclick="history.back()" class="rev-button">취 소</button>
    </form>
</div>

<script>
    // 30분 간격으로 시간 옵션 선택 (24시간제)
    var selectTime = document.getElementById("time");

    for (var hour = 0; hour < 24; hour++) {
        for (var minute = 0; minute < 60; minute += 30) {
            var timeString = String(hour).padStart(2, "0") + ":" + String(minute).padStart(2, "0");
            var option = document.createElement("option");
            option.value = timeString;
            option.textContent = timeString;
            selectTime.appendChild(option);
        }
    }
</script>
</body>
</html>