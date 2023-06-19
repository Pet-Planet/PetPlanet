<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<head>
    <meta charset="UTF-8">
    <style>
        table {
            background-color: #008800;
            border: none;
            border-spacing: 1px;
        }
        tr {
            background-color: #FFFF88;
        }
        tr td {
            padding: 3px;
        }
        #profile {
            width: 30px;
        }
    </style>
</head>
<body>
    <h2>댓글</h2>
        <table>
            <tr>
                <th>번호</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.content}</td>
                    <td>${comment.writer}</td>
                    <td>${comment.createdDate}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
