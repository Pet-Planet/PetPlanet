<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Review</title>
    <style>
        .rating-star {
            color: #ccc;
            cursor: pointer;
            font-size: 30px;
        }

        .rating-star.checked {
            color: #ffc107;
        }


    </style>
</head>
<body>
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
    <textarea name="content"></textarea><br>
    <input type="hidden" id="rating" name="rating" value="0">
<%--    <input type="hidden" name="placeId" value="5">--%>
    <input type="submit" value="Submit">
</form>

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