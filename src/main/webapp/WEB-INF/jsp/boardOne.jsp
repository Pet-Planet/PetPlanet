<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--글 한 개 조회하는 페이지--%>
<html>
<title>Pet planet</title>
<%@ include file="header.jsp"%>
<style>
    div.boardOne {
        margin: auto;
        text-align: center;
        width: 600px;
    }
    div.inside {
        text-align: left;
    }
    #btnDel{
        visibility: hidden;
    }
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    window.onload=function matchMember () {
        const btn = document.getElementById('btnDel');

        if(${board.memberId} == ${memberId}) {
            btn.style.visibility = 'visible';
        }
    }
</script>
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
            <button type="button" onclick="location.href='/board/${memberId}/update/${postId}'">수정</button>
            <button type="button" id="btnDel">삭제</button>
        </div>
    </div>

</body>
</html>
