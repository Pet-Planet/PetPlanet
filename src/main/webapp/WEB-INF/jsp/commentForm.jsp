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
    <jsp:include page="header.jsp" />
    <jsp:include page="menu.jsp" />
</head>
<body>
<form action="/board/${memberId}/post/${postId}/comment" method="post">
    <br><br><br>
    <table align="center">
        <tr class="hidden">
            <td>작성자</td>
            <td><input name="writer" size="80" value="${member.kakaoNickname}"></td>
        </tr>
        <tr>
            <td>댓글</td>
            <td><textarea name="content" rows="4" cols="80" placeholder="댓글을 입력하세요"></textarea></td>
        </tr>
    </table>
    <div style="text-align: center">
        <button type="submit">등록</button>
        <button type="button" onclick="history.back()">취소</button>
    </div>
</form>
</body>
</html>