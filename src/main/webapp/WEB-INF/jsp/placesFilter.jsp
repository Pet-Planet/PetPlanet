<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header2.jsp" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
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
</head>
<body class="text-center">
<form id="filterForm" action="/places/filter/${memberId}" method="POST">
    <select class="form-select" aria-label="Default select example" name="placeType">
        <option selected value="">장소 타입 선택</option>
        <option value="all">전체 선택</option>
        <option value="restaurant">레스토랑</option>
        <option value="cafe">카페</option>
        <option value="hotel">호텔</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="regionId">
        <option selected value="">지역 선택</option>
        <option value="-1">전체 지역</option>
        <option value="11">서울</option>
        <option value="12">경기도</option>
        <option value="13">제주도</option>
        <option value="22">강원도</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="sortOption">
        <option selected value="">정렬 선택</option>
        <option value="reviewCountDesc">리뷰 많은 순</option>
        <option value="reviewCountAsc">리뷰 적은 순</option>
        <option value="avgRatingDesc">평점 높은 순</option>
        <option value="avgRatingAsc">평점 낮은 순</option>
    </select>

    <input type="text" name="keyword" value="${param.keyword}">
<%--    <input type="button" value="검색" onClick="search()">--%>
    <button type="button" class="btn btn-outline-info" onclick="search()">검색</button>
</form>
    <script>
    function search() {
        filterForm.action="/places/filter/${memberId}/" ;
        filterForm.submit();
    };
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>