<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Review</title>
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
            background-color: black;
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
    <h1>리뷰</h1>
    <form action="/review" method="post">
        <label>별점과 이용 후기를 남겨주세요</label><br/>
        <div>
            <span class="rating-star" data-rating="1" onclick="setRating(1, this)">☆</span>
            <span class="rating-star" data-rating="2" onclick="setRating(2, this)">☆</span>
            <span class="rating-star" data-rating="3" onclick="setRating(3, this)">☆</span>
            <span class="rating-star" data-rating="4" onclick="setRating(4, this)">☆</span>
            <span class="rating-star" data-rating="5" onclick="setRating(5, this)">☆</span>
        </div><br/>
        <textarea name="content" placeholder="리뷰를 입력해 주세요."></textarea><br>
        <input type="hidden" id="rating" name="rating" value="0">
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