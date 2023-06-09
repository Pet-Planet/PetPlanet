<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Review</title>
    <script>
        function setRating(rating) {
            document.getElementById("rating").value = rating;
        }
    </script>
</head>
<body>
    <h1>Create Review</h1>
    <form action="/review" method="post">
        <label>Rating: </label>
        <div>
            <span onclick="setRating(1)">☆</span>
            <span onclick="setRating(2)">☆</span>
            <span onclick="setRating(3)">☆</span>
            <span onclick="setRating(4)">☆</span>
            <span onclick="setRating(5)">☆</span>
        </div>
        <label>Content: <textarea name="content"></textarea></label><br>
        <input type="hidden" id="rating" name="rating" value="0">
        <input type="submit" value="Submit">
    </form>
</body>
</html>