<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet Planet</title>
    <style>
        table {
            background-color: #008800;
            border: none;
            border-spacing: 2px;
        }
        tr {
            background-color: white;
        }
        tr td {
            padding: 20px;
        }
        #profile {
            width: 100px;
        }
    </style>
</head>
<jsp:include page="header2.jsp" />
<br><br><br><br><br><br>
<body>
<table align="center">
    <c:forEach var="reservation" items="${reservationList}">
        <tr>
            <td><img src="<c:url value='${reservation.imageUrl}'/>" width="100" height="100" decoding="async"></td>
            <td width="300px;">
                <a href="/reservation/${memberId}/${reservation.revId}"><h2><b>${reservation.placeName}</b></h2></a>
                ${reservation.startDate}<br>${reservation.endDate}${reservation.time}
            </td>
            <td width="50px;" align="center"><b>[Guest]</b><br><br>${reservation.guests}인<br>${reservation.pets}마리</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>