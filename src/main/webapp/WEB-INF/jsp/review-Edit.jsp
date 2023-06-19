<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Review</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
        }

        .review-form {
            text-align: center;
            margin-top: 100px;
            margin-bottom: 100px;
            border: 3px solid #B9E9FC;
            border-radius: 10px;
            background-color: white;
            padding: 20px;
            width: 600px;
            height: 500px;
        }

        .rating-star {
            color: #ccc;
            cursor: pointer;
            font-size: 40px;
        }

        .rating-star.checked {
            color: #ffc107;
        }

        textarea {
            width: 400px;
            height: 150px;
            resize: none;
        }

        .review-button {
            background-color: #B9E9FC;
            color: white;
            padding: 15px 30px;
            margin: 70px 50px;
            border: none;
            cursor: pointer;
            width: 100px;
            text-align: center;
            border-radius: 10px;
            font-size: 16px;
            font-weight: bold;
        }
    </style>
    <jsp:include page="header.jsp" />
    <jsp:include page="menu.jsp" />
</head>
<body>
<div class="review-form">
    <h1>리뷰</h1>
    <form action="/review/${memberId}/edit/${reviewId}" method="post" onsubmit="return showReviewConfirmation()">
        <label>별점과 이용 후기를 남겨주세요</label><br/>
        <div>
            <span class="rating-star" data-rating="1" onclick="setRating(1, this)">☆</span>
            <span class="rating-star" data-rating="2" onclick="setRating(2, this)">☆</span>
            <span class="rating-star" data-rating="3" onclick="setRating(3, this)">☆</span>
            <span class="rating-star" data-rating="4" onclick="setRating(4, this)">☆</span>
            <span class="rating-star" data-rating="5" onclick="setRating(5, this)">☆</span>
        </div><br/>
        <textarea name="content">${review.content}</textarea><br>
        <input type="hidden" id="rating" name="rating" value="${review.rating}">
        <input type="hidden" name="placeId" value="${placeId}">
        <button type="submit" class="review-button">등 록</button>
        <button type="button" onclick="history.back()" class="review-button">취 소</button>
    </form>
</div>

<script>
    window.onload = function() {
        var stars = document.getElementsByClassName("rating-star");
        for (var i = 0; i < stars.length; i++) {
            stars[i].addEventListener("click", function() {
                setRating(this.dataset.rating, this);
            });
        }

        var initialRating = parseInt(document.getElementById("rating").value);
        if (initialRating > 0) {
            setRating(initialRating, document.querySelector('.rating-star[data-rating="' + initialRating + '"]'));
        }
    };

    function setRating(rating, star) {
        document.getElementById("rating").value = rating;
        var stars = document.getElementsByClassName("rating-star");
        for (var i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].classList.add("checked");
            } else {
                stars[i].classList.remove("checked");
            }
        }
    }


    function showReviewConfirmation() {

        var rating = document.getElementById("rating").value;
        var content = document.getElementsByName("content")[0].value;

        if (rating == 0) {
            alert("별점을 입력해주세요.");
            return false;
        }

        if (content.trim().length === 0) {
            alert("리뷰를 입력해주세요.");
            return false;
        }


        alert("리뷰가 수정되었습니다.");
        return true;
    }
</script>
</body>
</html>





