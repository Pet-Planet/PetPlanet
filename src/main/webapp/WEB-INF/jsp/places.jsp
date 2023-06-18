<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>전체 장소 목록</title>
</head>
<body class="text-center">
<%@include file="placesFilter.jsp" %>
<h1>전체 장소 목록</h1>
<c:choose>
    <c:when test="${empty places}">
        <h1>해당 장소가 없습니다.</h1>
    </c:when>
    <c:otherwise>
        <c:forEach var="place" items="${places}">
            <div class="container text-center" onclick="goToPlaceDetail(${place.placeId})">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="<c:url value="../../img/image01.jpg"/>" width="100" height="100">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">장소명 : ${place.placeTitle}</h5>
                                <p class="card-text">장소 종류 : ${place.placeType}</p>
                                <p class="card-text">${place.avgRating}</p>
                                <p class="card-text">${place.reviewCnt}</p>
                                <p class="card-text"><small class="text-muted"></small></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
<script>
    function goToPlaceDetail(placeId) {
        window.location.href = '/places/${memberId}/placeDetail/' + placeId;
    }
</script>
<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>