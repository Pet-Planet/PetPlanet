<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<head>
    <%@ include file="header2.jsp"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<script>
    $(function (){
        $("btnSave").click(function () {
            var title = $("#title").val();
            var content = $("#content").val();
            var category = $("#category").val();
            if(title = "") {
                alert("제목을 입력하세요");
                $("#form_board").title.focus();
                return;
            }
            if(content == "") {
                alert("내용을 입력하세요");
                $("#form_board").content.focus();
                return;
            }
            $("#form_board").action="/board/${memberId}/update/${postId}";
            $("#form_board").submit();

        })
    });
</script>
<body>
<div class="container" role="main">
    <form id="form" action="/board/${memberId}/update/${postId}" method="post" >
        <input type="hidden" name="memberId" value="${memberId}">
        <input type="hidden" name="_method" value="post"/>
        <div class="mb-3">
            <input type="radio" name="category" value="[잡담]" checked/>잡담
            <input type="radio" name="category" value="[질문]"/>질문
            <input type="radio" name="category" value="[정보]"/>정보
        </div>
        <div class="mb-3">
            <label for="title">제목</label>
            <input class="form-control" name="title" id="title"  value="${board.title}" required>
        </div>
        <div class="mb-3">
            <label for="content">내용</label>
            <textarea class="form-control"  name="content" id="content" rows="5"  required>${board.content}</textarea>
        </div>
        <div style="text-align: center">
            <button class="btn btn-sm btn-primary" type="submit">확인</button>
            <button class="btn btn-sm btn-primary" type="button" onclick="history.back()">취소</button>
        </div>
    </form>
</div>

</body>
</html>