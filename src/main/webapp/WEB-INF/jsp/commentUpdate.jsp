<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<head>
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
        .hidden {
            display:none;
        }
    </style>
</head>
<body>
<form action="/board/${memberId}/post/${postId}/update/${commentId}" method="post">
    <br><br><br>
    <table align="center">
        <tr class="hidden">
            <td>작성자</td>
            <td><input name="writer" size="80" value="${comment.writer}"></td>
        </tr>
        <tr>
            <td style="width:100px;">${comment.writer}</td>
            <td style="width:600px;"><textarea name="content" rows="4" cols="100">${comment.content}</textarea></td>
            <td style="width:50px;">
                <button type="submit">수정</button>
                <button type="button" onclick="history.back()">취소</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>