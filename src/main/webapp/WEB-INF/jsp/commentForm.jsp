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
<form action="/board/${memberId}/post/${postId}" method="post">

    <table align="center">
        <tr class="hidden">
            <td>작성자</td>
            <td><input name="writer" size="80" value="${member.nickname}"></td>
        </tr>
        <tr>
            <td style="width:100px;">${member.nickname}</td>
            <td style="width:600px;"><textarea name="content" rows="4" cols="100" placeholder="댓글을 입력하세요"></textarea></td>
            <td style="width:50px;">
                <button type="submit">등록</button>
            </td>
        </tr>
    </table>
</form>
<br><br><br>
</body>
</html>