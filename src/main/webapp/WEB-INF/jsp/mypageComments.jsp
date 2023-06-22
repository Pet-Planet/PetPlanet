<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <%@ include file="header2.jsp"%>
    <link rel="stylesheet" href="/static/board-one.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/button.css">
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
    </style>

    <script>
        //댓글 삭제
        function deleteComment(commentId) {
            if (confirm("댓글을 삭제하시겠습니까?")) {
                fetch('/mypage/${memberId}/deleteMy/' + commentId, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            window.location.reload();
                        } else {
                            console.error('Error');
                        }
                    })
                    .catch(error => {
                        console.error('Error', error);
                    });
            }
        }

        function openCommentUpdateWindow(commentId) {
            const url = `/mypage/${memberId}/updateMy/` + commentId;

            var _width = '800';
            var _height = '300';

            var _left = Math.ceil(( window.screen.width - _width )/2);
            var _top = Math.ceil(( window.screen.height - _height )/2);

            window.open(url, 'commentUpdateWindow', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
        }
    </script>
</head>
<br><br><br><br><br><br>
<body>
<article>
    <div class="container" role="main">
<!--댓글 목록-->
<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
    <c:forEach var="comment" items="${boardCommentList}">
        <div class="media text-muted pt-3">
            <p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">
                        <span class="d-block">
                            <strong class="text-gray-dark">[ 글 ${comment.board.postId} ]</strong>
                            <span style="padding-left: 950px; font-size: 9pt">
                                <c:if test="${comment.member.memberId == memberId}">
<%--                                    <a style="padding-right:5px" id="btnUpdate" type="button" onclick="location.href='/mypage/${memberId}/updateMy/${comment.id}'">수정</a>--%>
                                    <a style="padding-right:5px" id="btnUpdate" type="button" onclick="openCommentUpdateWindow(${comment.id})">수정</a>
                                    <a id="btnDelete" type="button" onclick="deleteComment(${comment.id})">삭제</a>
                                </c:if>
                            </span>
                            <br>
                                ${comment.content}<br>
                            <c:set var="parsedDate" value="${fn:split(comment.createdDate, 'T')[0]}"/>
                            <c:set var="time" value="${fn:split(comment.createdDate, 'T')[1]}"/>
                            <c:set var="hours" value="${fn:split(time, ':')[0]}"/>
                            <c:set var="minutes" value="${fn:split(time, ':')[1]}"/>
                                ${parsedDate} ${hours}:${minutes}
                        </span>
            </p>
        </div>
    </c:forEach>
</article>
</body>
</html>
<br><br><br><br><br><br>