<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet Planet</title>
    <style>
        table {
            background-color: white;
            border: none;
            border-spacing: 2px;
        }
        tr {
            background-color: white;
        }
        tr td {
            padding: 20px;
        }

        h1 {
            margin-top: 0;
            margin-bottom: 15px; /* 줄간격을 조절합니다. */
            color: #98C0DC;
        }
        h3 {
            margin-top: 0;
            margin-bottom: 5px; /* 줄간격을 조절합니다. */
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
            margin-bottom: 20px;
            border: 3px solid #98C0DC;
            border-radius: 10px;
            background-color: white;
            padding: 0px;
            width: 620px;
            height: 170px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        a {
            text-decoration-line: none; /* 링크의 밑줄 제거 */
            color: inherit; /* 링크의 색상 제거 */
        }
    </style>
</head>
<jsp:include page="header2.jsp" />
<br><br><br><br><br><br>
<body>
<div class="container">
    <c:forEach var="reservation" items="${reservationList}">
        <div class="reservation-detail">
            <table align="center">
                <tr>
                <td><img src="<c:url value='${reservation.imageUrl}'/>" width="127" height="127" decoding="async"></td>
                <td width="400px;">
                    <a href="/reservation/${memberId}/${reservation.revId}"><h1><b>${reservation.placeName}</b></h1></a>
                    <h3>${reservation.startDate}&nbsp;/&nbsp;${reservation.endDate}${reservation.time}</h3>
                        성인 ${reservation.guests}명&nbsp;반려동물 ${reservation.pets}마리
                </td>
            </tr>
            </table>
        <br>
        </div>
    </c:forEach>
</div>
<br><br><br><br><br><br>
</body>
</html>