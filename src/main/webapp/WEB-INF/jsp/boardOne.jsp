<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--글 한 개 조회하는 페이지--%>
<html>
<head>
    <%@ include file="header2.jsp"%>
    <link rel="stylesheet" href="/static/board-one.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/button.css">
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


</body>
</html>
