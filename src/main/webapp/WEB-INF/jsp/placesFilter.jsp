<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="text-center">
<form id="filterForm" action="/places/filter/${memberId}" method="POST">
    <select class="form-select" aria-label="Default select example" name="placeType">
        <option selected>장소 타입 선택</option>
        <option value="all">전체 선택</option>
        <option value="restaurant">restaurant</option>
        <option value="cafe">cafe</option>
        <option value="hotel">hotel</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="regionId">
        <option selected>지역 선택</option>
        <option value="-1">전체 지역</option>
        <option value="11">서울</option>
        <option value="12">경기도</option>
        <option value="13">제주도</option>
        <option value="22">강원도</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="sortOption">
        <option selected>정렬 선택</option>
        <option value="reviewCountDesc">리뷰 많은 순</option>
        <option value="reviewCountAsc">리뷰 적은 순</option>
        <option value="avgRatingDesc">평점 높은 순</option>
        <option value="avgRatingAsc">평점 낮은 순</option>
    </select>
    <button type="submit" class="btn btn-primary">필터 적용</button>
</form>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>