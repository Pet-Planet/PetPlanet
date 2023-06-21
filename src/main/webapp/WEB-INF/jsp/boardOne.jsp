<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--글 한 개 조회하는 페이지--%>
<html>
<head>
    <%@ include file="header2.jsp"%>
    <link rel="stylesheet" href="/static/board-one.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/button.css">
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
    <script>
        window.onload=function matchMember () {
            const btn1 = document.getElementById('btnDel');
            const btn2 = document.getElementById('btnUp');
            const btnCancel = document.getElementById('cancel');
            const btnBookmark = document.getElementById('create');
            if(${board.memberId} == ${memberId}) {
                btn1.style.visibility = 'visible';
                btn2.style.visibility = 'visible';
            }
            fetch("/bookmark/check", {
                method: "POST",
                headers: {
                    "Content-Type" : "application/json",
                },
                body: JSON.stringify({
                    postId: ${postId},
                    memberId: ${memberId}
                }),
            }).then((response) => response.json())
                .then((data) => {
                    console.log(data);
                    if(data) {
                        btnCancel.style.display = "inline-block";
                        btnBookmark.style.display = "none";
                    }
                    else {
                        btnBookmark.style.display = "inline-block";
                        btnCancel.style.display = "none";
                    }
                });
        }
        function deleteById() {
            fetch("/board/${memberId}/delete/${postId}", {
                method: "DELETE"
            }).then((response) => response.json())
            location.href="/board/${memberId}";
        }
        function createBookmark() {
            const btnCancel = document.getElementById('cancel');
            const btnBookmark = document.getElementById('create');
            alert("해당 글을 북마크했습니다");
            btnCancel.style.display = "inline-block";
            btnBookmark.style.display = "none";
            fetch("/bookmark/create", {
                method: "POST",
                headers: {
                    "Content-Type" : "application/json",
                },
                body: JSON.stringify({
                    postId: ${postId},
                    memberId: ${memberId}
                }),
            }).then((response) => console.log(response));
        }
        function cancelBookmark() {
            const btnCancel = document.getElementById('cancel');
            const btnBookmark = document.getElementById('create');
            alert("북마크를 취고했습니다");
            btnBookmark.style.display = "inline-block";
            btnCancel.style.display = "none";
            fetch("/bookmark/delete", {
                method: "DELETE",
                headers: {
                    "Content-Type" : "application/json",
                },
                body: JSON.stringify({
                    postId: ${postId},
                    memberId: ${memberId}
                }),
            }).then((response) => console.log(response));
        }
        //댓글 삭제
        function deleteComment(commentId) {
            if (confirm("댓글을 삭제하시겠습니까?")) {
                fetch('/board/${memberId}/post/${postId}/delete/' + commentId, {
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
</head>

<body>
<article>
    <div class="container" role="main">
        <div class="bg-white rounded shadow-sm">
            <div class="board_title">${board.category} ${board.title}</div>
            <div class="board_info_box">
                <span class="board_author">${board.writer}</span>
                <span class="board_date">
                        <fmt:parseDate value="${board.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
                        <fmt:formatDate value="${ createdTime }" pattern="yyyy-MM-dd HH:mm" var="time" />
                        ${time}
                    </span>
                <span class="board_view">조회수 : ${board.countView}</span>
            </div>
            <hr>
            </div>
        <%--본문--%>
        <div class="board_content" >
            ${board.content}
        </div>
        <%--        버튼--%>
        <div class="boardBtn" style="margin-top : 20px">
            <div class="inside" id="boardBtn">
                <button type="button" class="btn btn-sm btn-primary" onclick="location.href='/board/${memberId}'">목록</button>
                <button type="button" class="btn btn-sm btn-primary" id="btnUp" onclick="location.href='/board/${memberId}/update/${postId}'">수정</button>
                <button type="button" class="btn btn-sm btn-primary" id="btnDel" onclick="deleteById()">삭제</button>
            </div>
            <div id="bookmark">
                <a class="bookmarkBtn" id="create" onclick="createBookmark()">
                    <img style="width: 40px" src="/img/bookmark_cancel.png">
                </a>
                <a class="bookmarkBtn" id="cancel" onclick="cancelBookmark()">
                    <img style="width: 40px" src="/img/bookmark_ok.png">
                </a>
            </div>
        </div>
    </div>
</article>

<br><br><br>
    <table align="center">
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td style="width:100px;">${comment.writer}</td>
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
                        <button id="btnUpdate" type="button" onclick="location.href='/board/${memberId}/post/${postId}/update/${comment.id}'">수정</button>
                    </c:if>
                </td>
                <td style="width:50px;">
                    <c:if test="${comment.member.memberId == memberId}">
                        <button id="btnDelete" type="button" onclick="deleteComment(${comment.id})">삭제</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br><br>
    <form action="/board/${memberId}/post/${postId}" method="post">
        <table align="center">
            <tr class="hidden">
                <td>작성자</td>
                <td><input name="writer" size="80" value="${member.nickname}"></td>
            </tr>
            <tr>
                <td style="width:100px;">${member.nickname}</td>
                <td style="width:600px;"><textarea name="content" rows="4" cols="100" placeholder="댓글을 입력하세요"></textarea></td>
                <td style="width:50px;">
                    <button type="submit">등록</button>
                </td>
            </tr>
        </table>
    </form>
    <br><br><br>
</body>
</html>
