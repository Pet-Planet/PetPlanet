<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        /*리뷰 스타일*/
        .item--QDh {
            background-color: #ffffff;
            border-radius: 4.7647rem;
            box-shadow: 0 0 0 rgba(0, 0, 0, 0.1000000015), 0 2.1rem 4.6rem rgba(0, 0, 0, 0.1000000015), 0 8.3rem 8.3rem rgba(0, 0, 0, 0.0900000036), 0 18.7rem 11.2rem rgba(0, 0, 0, 0.0500000007), 0 33.3rem 13.3rem rgba(0, 0, 0, 0.0099999998), 0 52rem 14.6rem rgba(0, 0, 0, 0);
            height: 15rem;
            overflow: hidden;
            position: relative;
            width: 100%;
        }

        /* 평균 평점 별그림 */
        .rating-stars {
            display: inline-block;
            font-size: 20px;
            color: gold;
        }

        .rating-stars::before {
            content: "★★★★★";
            letter-spacing: 3px;
        }

        /* 하나의 리뷰 별그림 */
        .rating-stars-review {
            display: inline-block;
            font-size: 20px;
            color: gold;
        }

        .rating-stars-review::before {
            content: "★★★★★";
            letter-spacing: 3px;
        }
    </style>
    <title>장소 상세 설명</title>
</head>
<body>
<div class="container text-center">
    <button id="btn-back" type="button" onclick="history.back()" class="review-button">목록</button>
    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="<c:url value='${placeDetail.imageUrl}'/>" width="100" height="100">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${placeDetail.placeTitle}</h5>
                    <p class="card-text">${placeDetail.address}</p>
                    <p class="card-text">${placeDetail.placeContent}</p>
                    <p class="card-text"><small class="text-muted"></small></p>
                    <button id="btn-reservation" type="button" class="btn btn-outline-info"
                            onclick="goToReservationForm('${placeDetail.placeType}', '${placeDetail.placeId}')">예약하기
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 평균 평점 표시하는 부분 -->
    <div style="width:200px; height:150px;  float:left; font-weight: bold; font-size: 40px;">
        Reviews
    </div>
    <div style="width:200px; height:150px;  float:left; font-weight: bold; font-size: 40px;">
        <span class="rating-stars"></span>
    </div>
    <div style="width:200px; height:150px;  float:left; font-weight: bold; font-size: 40px;">
        (${placeDetail.avgRating})
    </div>
    <div style="width:200px; height:150px; float:left;">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <div>
                <button id="btn-reviewWrite" type="button" class="btn btn-outline-info"
                        onclick="goToReviewForm('${placeDetail.placeId}')">리뷰 쓰기
                </button>
            </div>
        </div>
    </div>

    <!-- reviews 출력 -->
    <c:forEach var="review" items="${placeDetail.reviews}">
        <div class="item--QDh" id="20:436">
            <div class="auto-group-wa9h-zSo" id="31Kg9SBK9SRYmoUE7HWa9h">
                <p class="abcd-idh" id="20:448">${review.getNickName()}</p>
                <span class="rating-stars-review"></span>
            </div>
            <p class="item--iZ9" id="20:449">${review.content}</p>
            <p class="item-20230411-10-55-DsH" id="20:461">
                <script>
                    const writtenDate = new Date('${review.writtenDate}');
                    const formattedDate = writtenDate.toLocaleString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' });
                    document.write(formattedDate);
                </script>
            </p>
            <c:if test="${review.memberId == memberId}">
                <button id="btn-edit" type="button" class="btn btn-outline-info"
                    onclick="goToReviewEditForm('${review.id}','${placeDetail.placeId}')">수정
                </button>
                <button id="btn-delete" type="button" class="btn btn-outline-info"
                    onclick="deleteReview(${review.id}, ${placeDetail.placeId})">삭제
                </button>
            </c:if>
            <p>
            </p>
        </div>
        <p>
        </p>
        <!-- memberId가 일치하는 경우에만 수정/삭제 표시 -->
    </c:forEach>
</div>
<script>
    function goToReviewForm(placeId) {
        window.location.href = '/review/${memberId}/?placeId=' + placeId;
    }

    function goToReviewEditForm(id, placeId) {
        window.location.href = '/review/${memberId}/edit/' + id + '/?placeId=' + placeId;
    }


    function goToReservationForm(placeType, placeId) {
        window.location.href = '/reservation/${memberId}/?placeId=' + placeId;
    }

    function deleteReview(reviewId, placeId) {
        if (confirm("리뷰를 정말 삭제하시겠습니까?")) {
            // Send an AJAX request to delete the review
            fetch('/review/' + reviewId, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        // If the review is deleted successfully, reload the page to reflect the changes
                        window.location.reload();
                    } else {
                        console.error('Error deleting the review.');
                    }
                })
                .catch(error => {
                    console.error('Error deleting the review:', error);
                });
        }
    }
</script>
<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>