<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%--글 한 개 조회하는 페이지--%>
<html>
<title>Pet planet</title>
<body>
    <div>
        <h1>${board.category} ${board.title}</h1>
        <div>
            ${board.writer}
        </div>
        <div>
            <fmt:parseDate value="${board.lastModifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
            <fmt:formatDate value="${ createdTime }" pattern="yyyy-MM-dd HH:mm" var="time" />
            ${time}
        </div>
        <hr>
        <div>
            ${board.content}
        </div>
    </div>
    <div>
        <button type="button" onclick="location.href='/board/${memberId}'">목록</button>
    </div>
</body>
</html>
