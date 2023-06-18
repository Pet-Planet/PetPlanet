<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Review</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .review-form {
            text-align: center;
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

        .submit-button {
            background-color: #B9E9FC;
            color: white;
            padding: 10px 20px;
            margin: 50px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="review-form">
    <h1>리뷰 수정</h1>
    <form action="/review/${memberId}/edit/${reviewId}" method="put">
        <label>별점과 이용 후기를 남겨주세요</label><br/>
        <div>
            <span class="rating-star" data-rating="1" onclick="setRating(1, this)">☆</span>
            <span class="rating-star" data-rating="2" onclick="setRating(2, this)">☆</span>
            <span class="rating-star" data-rating="3" onclick="setRating(3, this)">☆</span>
            <span class="rating-star" data-rating="4" onclick="setRating(4, this)">☆</span>
            <span class="rating-star" data-rating="5" onclick="setRating(5, this)">☆</span>
        </div><br/>
        <textarea name="content" placeholder="리뷰를 입력해 주세요.">${review.content}</textarea><br>
        <input type="hidden" id="rating" name="rating" value="${review.rating}">
        <input type="submit" value="Submit" class="submit-button">
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

        var initialRating = parseInt("${review.rating}");
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
</script>
</body>
</html>