<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<%@ include file="header2.jsp"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<body>
<div class="container" role="main">
    <form id="form" name="form_board" method="post" action="/board/${memberId}/post">
        <div class="mb-3">
            <input type="radio" name="category" value="[잡담]" checked/>잡담
            <input type="radio" name="category" value="[질문]"/>질문
            <input type="radio" name="category" value="[정보]"/>정보
        </div>
        <div class="mb-3">
            <label for="title">제목</label>
            <input class="form-control" name="title" id="title" placeholder="제목을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="content">내용</label>
            <textarea class="form-control" name="content" id="content" rows="5">내용을 입력하세요</textarea>
        </div>
        <div style="text-align: center">
            <button type="submit" class="btn btn-sm btn-primary" id="btnSave">등록</button>
            <button type="button" class="btn btn-sm btn-primary" onclick="history.back()">취소</button>
        </div>
    </form>
</div>

</body>
</html>