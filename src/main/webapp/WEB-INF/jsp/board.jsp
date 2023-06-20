<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<jsp:useBean id="now" class="java.util.Date" />
<%--현재 시간--%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />

<html>
<head>
    <link rel="stylesheet" href="/static/board.css" />
    <jsp:include page="header2.jsp" />
</head>
<body>
    <div id="btn">
        <button id="btnwrite" type="button" onclick="location.href='/board/${memberId}/post'">작성하기</button>
    </div>
    <div>
        <table id="board">
            <tr>
                <th>번호</th>
                <th>카테고리</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="row" items="${boardList.content}">
                <tr>
                    <td>${row.postId}</td>
                    <td>${row.category}</td>
                    <td><a href="/board/${memberId}/post/${row.postId}" >${row.title}</a></td>
                    <td>${row.writer}</td>
                    <td>
                        <fmt:parseDate value="${ row.createdDate }" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
                        <fmt:formatDate value="${ createdTime }" pattern="yyyy-MM-dd" var="time" />
                        <c:choose>
                            <c:when test="${time < today}">
                                ${time}
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${ createdTime }" pattern="HH:mm" var="time2" />
                                ${time2}
                            </c:otherwise>

                        </c:choose>
                    </td>
                    <td>${row.countView}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="paging">
            <ul>
                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <li><a href="<c:url value="/board/${memberId}/?page=${boardList.pageable.pageNumber -1})" />">Previous</a></li>
                    <li><a href="<c:url value="/board/${memberId}/?page=${i - 1}" />">${i}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>