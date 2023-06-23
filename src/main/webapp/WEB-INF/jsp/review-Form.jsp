<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Review</title>
    <jsp:include page="header2.jsp" />
    <style>

        .container {
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .review-form {
            text-align: center;
            margin-top: 100px;
            margin-bottom: 100px;
            border: 3px solid #98C0DC;
            border-radius: 10px;
            background-color: white;
            padding: 20px;
            width: 600px;
            height: 550px;
        }

        .rating-star {
            color: #ccc;
            cursor: pointer;
            font-size: 45px;
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
            background-color: #98C0DC;
            color: white;
            padding: 15px 30px;
            margin: 100px 50px;
            border: none;
            cursor: pointer;
            width: 100px;
            text-align: center;
            border-radius: 10px;
            font-size: 16px;
            font-weight: bold;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="review-form">
        <h1>리뷰</h1><br/>
        <form action="/review/${memberId}" method="post" onsubmit="return showReviewConfirmation()">
            <label>별점과 이용 후기를 남겨주세요</label><br/>
            <div>
                <span class="rating-star" data-rating="1" onclick="setRating(1, this)">☆</span>
                <span class="rating-star" data-rating="2" onclick="setRating(2, this)">☆</span>
                <span class="rating-star" data-rating="3" onclick="setRating(3, this)">☆</span>
                <span class="rating-star" data-rating="4" onclick="setRating(4, this)">☆</span>
                <span class="rating-star" data-rating="5" onclick="setRating(5, this)">☆</span>
            </div><br/>
            <textarea name="content" placeholder="리뷰를 입력해 주세요."></textarea><br>
            <input type="hidden" name="placeId" value="${placeId}">
            <input type="hidden" name="memberId" value="${memberId}">
            <input type="hidden" id="rating" name="rating" value="0">
            <button type="submit" class="review-button">등 록</button>
            <button type="button" onclick="history.back()" class="review-button">취 소</button>
        </form>
    </div>
</div>

<script>
    window.onload = function() {
        var stars = document.getElementsByClassName("rating-star");
        for (var i = 0; i < stars.length; i++) {
            stars[i].addEventListener("click", function() {
                setRating(this.dataset.rating, this);
            });
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

        if (confirm("리뷰를 등록하시겠습니까?")) {

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

            return true;
        }
    }
</script>
</body>
</html>