<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="/static/headermenu.css" />
    <script type="text/javascript" src="/static/menu.js"></script>
    <link rel="stylesheet" href="/static/board-one.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/button.css">
    <meta charset="UTF-8">
    <title>Pet Planet</title>
    <style>
        table {
            background-color: #008800;
            border: none;
            border-spacing: 2px;
        }
        tr {
            background-color: white;
        }
        tr td {
            padding: 20px;
        }
    </style>
    <script>
        function goSubmit() {
            window.opener.name = "parentPage";
            document.form.target = "parentPage";
            document.form.action = "/board/${memberId}/post/${postId}/update/${commentId}";
            document.form.submit();
            self.close();
        }
    </script>
</head>
<br><br><br>
<body>
<article>
    <div class="container" role="main">
        <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
            <form name="form" id="form" role="form" action="/board/${memberId}/post/${postId}/update/${commentId}" method="post">
                <div class="row">
                    <div class="col-sm-10">
                        <textarea name="content" class="form-control" name="writer" id="content" rows="3" placeholder="댓글을 입력하세요">${comment.content}</textarea>
                    </div>
                    <div class="col-sm-2">
                        <input path="reg_id" name="writer" class="form-control" id="reg_id" value="${comment.writer}" readonly/>
                        <button class="btn btn-sm btn-primary" type="submit" id="btnReplySave" style="width: 100%; margin-top: 10px" onclick="goSubmit()">등록</button>
                    </div>
                </div>
            </form>
        </div>
        <br><br><br>
</div>
</article>
</body>
</html>