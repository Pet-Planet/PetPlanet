<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header2.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
            height: 100%;
            overflow: hidden;
            position: relative;
            width: 100%;
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
    </style>
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
    <title>Pet Planet</title>
</head>

<br><br><br>
<body>
<div style="margin-left:50px; margin-right:50px;">
    <div class="reviews">
        <c:forEach var="review" items="${reviewList}">
            <div class="item--QDh" id="review-${review.id}" style="position: relative;">
                <div style="float: left; margin-left: 20px; margin-top: 38px; font-size: larger">
                    <p>
                        <strong>${review.placeTitle}</strong>
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
                                onclick="goToReviewEditForm('${review.id}','${review.placeId}')">수정
                        </button>
                        <button id="btn-delete" type="button" class="btn btn-outline-info"
                                onclick="deleteReview(${review.id})">삭제
                        </button>
                    </div>
                </c:if>
            </div>
            <br>
        </c:forEach>
    </div>
</div>
</body>
</html>