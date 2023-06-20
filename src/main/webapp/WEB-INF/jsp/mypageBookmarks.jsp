<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<jsp:useBean id="now" class="java.util.Date" />
<%--현재 시간--%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />

<html>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<style>
    #board {
        margin-left: auto;
        margin-right: auto;
    }
    table {
        width: 600px;
    }
    table, td, th {
        border-collapse: collapse;
        border : 1px solid black;
        text-align: center;
    }
    div#btn {
        width: 600px;
        margin: auto;
    }
    #btnwrite {
        width: 70px;
        margin-right: 530px;
    }

</style>

<body>
<div>
    <table id="board">
        <thead>
        <tr>
            <th>번호</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bookmark" items="${bookmarkList}">
            <tr>
                <td>${bookmark.postId}</td>
                <td>${bookmark.category}</td>
                <td><a href="/board/${memberId}/post/${bookmark.postId}">${bookmark.title}</a></td>
                <td>${bookmark.writer}</td>
                <td>
                    <fmt:parseDate value="${bookmark.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
                    <fmt:formatDate value="${createdTime}" pattern="yyyy-MM-dd" var="formattedDate" />
                    <c:choose>
                        <c:when test="${formattedDate < today}">
                            ${formattedDate}
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${createdTime}" pattern="HH:mm" var="formattedTime" />
                            ${formattedTime}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${bookmark.countView}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>