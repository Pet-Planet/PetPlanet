<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 부트스트랩 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>전체 장소 목록</title>
</head>
<body class="text-center">
<h1>전체 장소 목록</h1>
<c:forEach var="place" items="${places}">
    <div class="container text-center" onclick="goToPlaceDetail(${place.placeId})">
        <div class="card mb-3" style="max-width: 540px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<c:url value="../../img/image01.jpg"/>" width="100" height="100">  <%--이미지도 읽어오는 것도 수정 예정--%>
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">장소명 : ${place.placeTitle}</h5>
                        <p class="card-text">장소 종류 : ${place.placeType}</p>
                        <p class="card-text">${place.avgRating}</p>   <%--별 그림으로 표시되도록 수정 예정--%>
                        <p class="card-text">${place.reviewCnt}</p>
                        <button type="button" class="btn btn-outline-info" onclick="goToPlaceDetail(${place.placeId})">상세보기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<script>
    document.getElementById('filterForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 폼의 기본 동작인 페이지 새로고침을 막습니다.

        var placeType = document.getElementsByName('placeType')[0].value;
        var region = document.getElementsByName('region')[0].value;
        var sorting = document.getElementsByName('sorting')[0].value;

        // 선택한 옵션 값을 사용하여 필터링 된 결과를 표시하거나 처리하는 함수를 호출합니다.
        showFilteredResults(placeType, region, sorting);
    });

    function showFilteredResults(placeType, region, sorting) {
        // 선택한 옵션 값에 따라 필터링 된 결과를 생성하고 화면에 표시합니다.
        // 이 함수에서 선택한 옵션 값을 사용하여 적절한 결과를 생성하고 화면에 표시합니다.
        // 필요한 경우 Ajax 요청을 사용하여 서버에 필터링된 결과를 요청할 수도 있습니다.
        // 예를 들어:
        // $.ajax({
        //     url: 'filterResults',
        //     method: 'POST',
        //     data: { placeType: placeType, region: region, sorting: sorting },
        //     success: function(response) {
        //         // 필터링된 결과를 사용하여 화면을 업데이트합니다.
        //     },
        //     error: function(error) {
        //         // 오류 처리를 수행합니다.
        //     }
        // });
    }

    function goToPlaceDetail(placeId) {
        window.location.href = 'placeDetail/' + placeId;
    }
</script>
<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
