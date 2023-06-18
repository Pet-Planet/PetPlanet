<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 부트스트랩 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>전체 장소 목록</title>
</head>
<body class="text-center">
<form id="filterForm">
    <select class="form-select" aria-label="Default select example" name="placeType">
        <option selected>장소 타입 선택</option>
        <option value="1">전체 선택</option>
        <option value="2">restaurant</option>
        <option value="3">cafe</option>
        <option value="4">hotel</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="region">
        <option selected>지역 선택</option>
        <option value="1">전체 지역</option>
        <option value="2">서울</option>
        <option value="3">경기도</option>
        <option value="4">제주도</option>
    </select>
    <select class="form-select" aria-label="Default select example" name="sorting">
        <option selected>정렬 선택</option>
        <option value="1">리뷰 많은 순</option>
        <option value="2">리뷰 적은 순</option>
        <option value="3">평점 높은 순</option>
        <option value="4">평점 낮은 순</option>
    </select>
    <button type="submit" class="btn btn-primary">필터 적용</button>
</form>
</body>
</html>