<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .average-rating {
            position: relative;
            appearance: none;
            color: transparent;
            width: auto;
            display: inline-block;
            vertical-align: baseline;
        }

        .average-rating::before {
            --percent: calc(var(--rating) / 5 * 100%);
            content: '★★★★★';
            position: absolute;
            top: 0;
            left: 0;
            color: rgba(0, 0, 0, 0.2);
            background: linear-gradient(90deg, gold var(--percent), rgba(0, 0, 0, 0.2) var(--percent));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }
    </style>
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
                            <img src="<c:url value='${place.imageUrl}'/>" width="100" height="100" decoding="async">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${place.placeTitle}</h5>
                                <p class="card-text">${place.placeType}</p>
                                <p class="card-text"><meter class="average-rating" min="0" max="5" value="${place.avgRating}" title="${place.avgRating} out of 5 stars" style="--rating: ${place.avgRating}"> ${place.avgRating} out of 5</meter>(${place.avgRating})</p>
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
