<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="header2.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        /*상세 설명*/
        .item-place {
            background-color: #ffffff;
            border-radius: 1rem;
            box-shadow: 0 0 0 rgba(0, 0, 0, 0.05), 0 2.1rem 3rem rgba(0, 0, 0, 0.0500000007), 0 33.3rem 13.3rem rgba(0, 0, 0, 0.0099999998), 0 52rem 14.6rem rgba(0, 0, 0, 0);
            height: 20rem;
            overflow: hidden;
            position: relative;
            width: 100%;
            text-align: left;
        }

        .review-top {
            background-color: #ffffff;
            border-radius: 0rem;
            height: 6rem;
            overflow: hidden;
            position: relative;
            width: 100%;
        }

        .reviews {
            background-color: #ffffff;
            border-radius: 0.5rem;
            overflow: hidden;
            position: relative;
            width: 100%;
            height: auto;
        }

        /*리뷰 스타일*/
        .item--QDh {
            background-color: #ffffff;
            border-radius: 1rem;
            box-shadow: 0 0 0 rgba(0, 0, 0, 0.05), 0 2.1rem 3rem rgba(0, 0, 0, 0.0500000007), 0 33.3rem 13.3rem rgba(0, 0, 0, 0.0099999998), 0 52rem 14.6rem rgba(0, 0, 0, 0);
            height: 13rem;
            overflow: hidden;
            position: relative;
            width: 99%;
            margin-left: auto;
            margin-right: auto;
        }

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
        .btn {
            background-color: #fff;
            color: #98C0DC;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .btn:hover {
            background-color: #98C0DC;
            color: #fff;
        }
        html, body {
            height: 100%;
        }

    </style>
    <title>장소 상세 설명</title>
</head>
<script>
    function formatReviewDate(dateString) {
        const writtenDate = new Date(dateString);
        const formattedDate = writtenDate.toLocaleString('ko-KR', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
        });
        return formattedDate;
    }

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
            fetch('/review/delete/' + reviewId, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        const reviewElement = document.getElementById('review-' + reviewId);
                        reviewElement.remove();
                    } else {
                        console.error('리뷰 삭제 중 오류가 발생했습니다.');
                    }
                })
                .catch(error => {
                    console.error('리뷰 삭제 중 오류가 발생했습니다:', error);
                });
            location.reload();
        }
    }

</script>
<body>
<div style="margin:50px;">
    <button type="button" class="btn btn-outline-info review-button" style="float: left; margin-bottom: 10px;"
            onClick="location.href='/places/${memberId}'">목록으로 돌아가기
    </button>

    <%--    장소 상세 설명--%>
    <div class="item-place">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="<c:url value='${placeDetail.imageUrl}'/>" width="300" height="300">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title" style="font-weight: bold; font-size: 3em;">${placeDetail.placeTitle}</h5>
                    <p class="card-text">${placeDetail.address}</p>
                    <p class="card-text">${placeDetail.placeContent}</p>
                    <p class="card-text">1인당 이용 금액 : <fmt:formatNumber value="${placeDetail.price}" pattern="#,###"/>원</p>
                    <p class="card-text"><small class="text-muted"></small></p>
                    <button id="btn-reservation" type="button" class="btn btn-outline-info"
                            onclick="goToReservationForm('${placeDetail.placeType}', '${placeDetail.placeId}')">예약하기
                    </button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <%--    평균 평점 출력 부분--%>
    <div class="review-top">
        <div class="review-top" style="display: flex; align-items: center;">
            <div style="margin-left:40px; width: 200px; height: 90px; font-weight: bold; font-size: 30px; line-height: 100px;">
                Reviews
            </div>
            <div style="width: 100px; height: 90px; font-weight: bold; font-size: 35px; line-height: 100px;">
                <p>
                    <meter class="average-rating" min="0" max="5" value="${placeDetail.avgRating}"
                           title="${placeDetail.avgRating} out of 5 stars"
                           style="--rating: ${placeDetail.avgRating}"> ${placeDetail.avgRating} out of 5
                        (${placeDetail.avgRating})
                    </meter>
                </p>
            </div>
            <div style="width: 200px; height: 90px; margin-left: 100px; font-weight: bold; font-size: 30px; line-height: 100px;">
                (${placeDetail.reviewCnt})
            </div>
            <div style="flex-grow: 1; display: flex; justify-content: flex-end; margin-right: 50px;">
                <button id="btn-reviewWrite" type="button" class="btn btn-outline-info"
                        onclick="goToReviewForm('${placeDetail.placeId}')">리뷰 작성
                </button>
            </div>
        </div>
    </div>
    <br>
    <hr>

    <!-- reviews -->
    <div class="reviews">
        <c:forEach var="review" items="${placeDetail.reviews}">
            <div class="item--QDh" id="review-${review.id}" style="position: relative;">
                <div style="float: left; margin-left: 50px; margin-top: 30px;">
                    <img src="/img/account_icon.png" width="70" height="70"/>
                </div>
                <div style="float: left; margin-left: 20px; margin-top: 38px; font-size: larger">
                    <p>
                        <strong>${review.nickName.trim()}</strong>
                        <br>
                        <meter class="average-rating" min="0" max="5" value="${review.rating}" style="--rating: ${review.rating}; float: left; ">
                                ${review.rating} out of 5 (${review.rating})
                        </meter>
                    </p>
                    <br>
                    <br>
                    <p>${review.content}</p>
                </div>
                <div style="position: absolute; top: 20px; right: 300px;">
                    <script>
                        document.write(formatReviewDate('${review.writtenDate}'));
                    </script>
                </div>
                <c:if test="${review.memberId == memberId}">
                    <div style="position: absolute; top: 10px; right: 10px;">
                        <button id="btn-edit" type="button" class="btn btn-outline-info"
                                onclick="goToReviewEditForm('${review.id}','${placeDetail.placeId}')">수정
                        </button>
                        <button id="btn-delete" type="button" class="btn btn-outline-info"
                                onclick="deleteReview(${review.id}, ${placeDetail.placeId})">삭제
                        </button>
                    </div>
                </c:if>
            </div>
            <br>
        </c:forEach>
    </div>


</div>
<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
