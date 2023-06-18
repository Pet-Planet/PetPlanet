<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--글 한 개 조회하는 페이지--%>
<jsp:include page="header.jsp" />
<html>
<title>Pet planet</title>
<style>
    div.boardOne {
        margin: auto;
        text-align: center;
        width: 600px;
    }
    div.inside {
        text-align: left;
    }
</style>
<body>
    <div class="boardOne">
        <div class="inside">
            <h3>${board.category} ${board.title}</h3>
            ${board.writer}
            <fmt:parseDate value="${board.lastModifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
            <fmt:formatDate value="${ createdTime }" pattern="yyyy-MM-dd HH:mm" var="time" />
            ${time}
        </div>
        <hr>
        <div class="inside">
            ${board.content}
        </div>
        <div class="inside">
            <button type="button" onclick="location.href='/board/${memberId}'">목록</button>
            <button type="button" >수정</button>
            <button type="button" >삭제</button>
        </div>
    </div>

</body>
</html>
