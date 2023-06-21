<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Pet Planet</title>--%>
<%--    <style>--%>
<%--        table {--%>
<%--            background-color: #008800;--%>
<%--            border: none;--%>
<%--            border-spacing: 2px;--%>
<%--        }--%>
<%--        tr {--%>
<%--            background-color: white;--%>
<%--        }--%>
<%--        tr td {--%>
<%--            padding: 20px;--%>
<%--        }--%>
<%--        .hidden {--%>
<%--            display:none;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="/board/${memberId}/post/${postId}/comment" method="post">--%>

<%--    <table align="center">--%>
<%--        <tr class="hidden">--%>
<%--            <td>작성자</td>--%>
<%--            <td><input name="writer" size="80" value="${member.nickname}"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td style="width:100px;">${member.nickname}</td>--%>
<%--            <td style="width:600px;"><textarea name="content" rows="4" cols="100" placeholder="댓글을 입력하세요"></textarea></td>--%>
<%--            <td style="width:50px;">--%>
<%--                <button type="submit">등록</button>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>
<%--<br><br><br>--%>
<%--</body>--%>
<%--</html>--%>

<%--        댓글 입력 창--%>
<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
    <form name="form" id="form" role="form" action="/board/${memberId}/post/${postId}/comment" method="post">
        <div class="row">
            <div class="col-sm-10">
                <textarea name="content" class="form-control" name="writer" id="content" rows="3" placeholder="댓글을 입력하세요"></textarea>
            </div>
            <div class="col-sm-2">
                <input path="reg_id" name="writer" class="form-control" id="reg_id" value="${member.nickname}" readonly/>
                <button class="btn btn-sm btn-primary" type="submit" id="btnReplySave" style="width: 100%; margin-top: 10px">등록</button>
            </div>
        </div>
    </form>
</div>
<%--        댓글 입력 창--%>