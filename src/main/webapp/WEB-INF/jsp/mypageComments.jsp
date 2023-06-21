<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            text-align: center;
            padding: 20px;
        }
        .hidden {
            display:none;
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
    </script>
    <jsp:include page="header2.jsp" />
</head>
<br><br><br>
<body>
<table align="center">
    <c:forEach var="comment" items="${boardCommentList}">
        <tr>
            <td style="width:100px;">${comment.board.postId}</td>
            <td style="width:400px;">${comment.content}</td>
            <td style="width:100px;">
                <c:set var="parsedDate" value="${fn:split(comment.createdDate, 'T')[0]}"/>
                <c:set var="time" value="${fn:split(comment.createdDate, 'T')[1]}"/>
                <c:set var="hours" value="${fn:split(time, ':')[0]}"/>
                <c:set var="minutes" value="${fn:split(time, ':')[1]}"/>
                    ${parsedDate} ${hours}:${minutes}
            </td>
            <td style="width:50px;">
                <c:if test="${comment.member.memberId == memberId}">
                    <button id="btnUp" type="button" onclick="location.href='/mypage/${memberId}/updateMy/${comment.id}'">수정</button>
                </c:if>
            </td>
            <td style="width:50px;">
                <c:if test="${comment.member.memberId == memberId}">
                    <button id="btnDel" type="button" onclick="deleteComment(${comment.id})">삭제</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%--<div id="includedPage"></div>   <!--수정 페이지 삽입-->--%>
</body>
<br><br><br>
</html>