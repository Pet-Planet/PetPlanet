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
        select {
            -moz-appearance: none;
            -webkit-appearance: none;
            appearance: none;
            padding: 0.6em 1.4em 0.5em 0.8em;
            margin: 0;

            border: 1px solid #aaa;
            border-radius: 0.5em;
            box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);

            font-family: 'GmarketSansMedium';
            text-align: center;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;

            color: #444;
            background-color: #fff;

        }
    </style>
</head>
<body class="text-center">
<form id="filterForm" action="/places/filter/${memberId}" method="POST">
    <select class="selectpicker show-tick" name="placeType">
        <option selected value="">장소 타입 선택</option>
        <option value="all">전체 선택</option>
        <option value="restaurant">레스토랑</option>
        <option value="cafe">카페</option>
        <option value="hotel">호텔</option>
    </select>
    <select class="selectpicker show-tick" name="regionId">
        <option selected value="">지역 선택</option>
        <option value="-1">전체 지역</option>
        <option value="11">서울</option>
        <option value="12">경기도</option>
        <option value="13">제주도</option>
        <option value="22">강원도</option>
    </select>
    <select class="selectpicker show-tick" name="sortOption">
        <option selected value="">정렬 선택</option>
        <option value="reviewCountDesc">리뷰 많은 순</option>
        <option value="reviewCountAsc">리뷰 적은 순</option>
        <option value="avgRatingDesc">평점 높은 순</option>
        <option value="avgRatingAsc">평점 낮은 순</option>
    </select>
    <input type="text" name="keyword" placeholder="장소 이름을 입력하세요" value="${param.keyword}">
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