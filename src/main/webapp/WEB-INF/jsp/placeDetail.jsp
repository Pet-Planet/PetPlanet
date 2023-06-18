<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>장소 상세 설명</title>
</head>
<body>
<div class="container text-center">
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="<c:url value="../../img/image01.jpg"/>" width="100" height="100">  <%--이미지도 읽어오도록 추후 수정 --%>
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">장소명 : ${placeDetail.placeTitle}</h5>
                    <p class="card-text">장소 종류 : ${placeDetail.placeType}</p>
                    <p class="card-text">평점(리뷰 수) : ${placeDetail.avgRating}(${placeDetail.reviewCnt})</p>   <%--별 그림으로 표시되도록 추후 수정--%>
                    <p class="card-text"><small class="text-muted"></small></p>
                </div>
            </div>
        </div>
    </div>
    <button type="button" class="btn btn-outline-info" onclick="goToReviewForm('${placeDetail.placeId}')">리뷰 쓰기</button>
    <button type="button" class="btn btn-outline-info" onclick="goToReservationForm('${placeDetail.placeId}')">예약하기</button>
    <script>
        function goToReviewForm(placeId) {
            window.location.href = '/review?placeId=' + placeId;
        }

        function goToReservationForm(placeId) {
            window.location.href = '/reservation?placeId=' + placeId;
        }

    </script>

</div>
</body>
</html>
