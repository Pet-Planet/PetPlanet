<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        /* 리뷰 스타일 */
        .item--QDh {
            background-color: #ffffff;
            border-radius: 4.7647rem;
            box-shadow: 0 0 0 rgba(0, 0, 0, 0.1000000015), 0 2.1rem 4.6rem rgba(0, 0, 0, 0.1000000015), 0 8.3rem 8.3rem rgba(0, 0, 0, 0.0900000036), 0 18.7rem 11.2rem rgba(0, 0, 0, 0.0500000007), 0 33.3rem 13.3rem rgba(0, 0, 0, 0.0099999998), 0 52rem 14.6rem rgba(0, 0, 0, 0);
            height: 15rem;
            overflow: hidden;
            position: relative;
            width: 100%;
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
    </style>
    <title>Pet Planet</title>
</head>

<jsp:include page="header2.jsp" />

<br><br><br>
<body>
<div class="container text-center">
<!-- 리뷰 목록 출력 -->
<c:forEach var="review" items="${reviewList}">
    <div class="item--QDh" id="20:436" style="width:800px; height:200px; margin:0 auto;"><br>
        <div class="auto-group-wa9h-zSo" id="31Kg9SBK9SRYmoUE7HWa9h">
            <p class="abcd-idh" id="20:448"><b>${review.placeTitle}</b></p>
            <span class="rating-stars-review"></span>
        </div>
        <div style="width:100px; height:100px;  float:left; font-weight: bold; font-size: 40px;">
            <p>
                <meter class="average-rating" min="0" max="5" value="${review.rating}" title="${review.rating} out of 5 stars" style="--rating: ${review.rating}">
                        ${review.rating} out of 5(${review.rating})
                </meter>
            </p>
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
                    onclick="goToReviewEditForm('${review.id}','${review.placeId}')">수정
            </button>
            <button id="btn-delete" type="button" class="btn btn-outline-info"
                    onclick="deleteReview(${review.id})">삭제
            </button>
        </c:if>
        <p></p>
    </div>
    <p></p>
    <!-- memberId가 일치하는 경우에만 수정/삭제 표시 -->
</c:forEach>
</div>

<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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

    function deleteReview(reviewId) {
        if (confirm("리뷰를 정말 삭제하시겠습니까?")) {
            // Send an AJAX request to delete the review
            fetch('/review/delete/' + reviewId, {
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
</body>
</html>