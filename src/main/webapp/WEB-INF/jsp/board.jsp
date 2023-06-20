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
    <%--    페이징 스타일 시트--%>
    <link rel="stylesheet" href="/static/board_page.css" />
    <jsp:include page="header2.jsp" />
</head>
<body>
    <%--    board search area    --%>
    <div id="board-search">
        <div class="board-sort">
            <form>
                <select id="sortType" name="sortType">
                    <option value="time">최신순</option>
                    <option value="view">조회수순</option>
                </select>
            </form>
        </div>
        <div class="container">
            <div class="search-window">
                <form action="/board/${memberId}" method="get">
                    <div class="search-wrap">
                        <label for="search" class="blind">자유게시판 내용 검색</label>
                        <input id="search" type="text" name="searchText" placeholder="검색어를 입력해주세요." value="${searchText}">
                        <button type="submit" class="btn btn-dark">검색</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%-- board list --%>
    <div id="board_list">
        <table class="board-table">
            <tr>
                <th cope="col" class="th-num">번호</th>
                <th>카테고리</th>
                <th scope="col" class="th-title">제목</th>
                <th>작성자</th>
                <th scope="col" class="th-date">작성일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="row" items="${boardList.content}">
                <tr>
                    <td>${row.postId}</td>
                    <td>${row.category}</td>
                    <td id="board-title"><a href="/board/${memberId}/post/${row.postId}" >${row.title}</a></td>
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
    </div>
    <div class="page_wrap">
        <div class="page_nation">
            <c:forEach begin="1" end="${totalPage}" var="i">
                <a id="pageBtn" href="<c:url value="/board/${memberId}/?page=${i - 1}" />">${i}</a>
            </c:forEach>
        </div>
        <button id="btnwrite" class="btn btn-dark" type="button" onclick="location.href='/board/${memberId}/post'">작성하기</button>
    </div>
</body>
</html>