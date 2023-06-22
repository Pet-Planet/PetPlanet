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
        // 글 삭제
        function deleteBoard() {
            if (confirm("해당 글을 삭제하시겠습니까?")) {
                fetch('/board/${memberId}/delete/${postId}' , {
                    method: 'DELETE'
                })
                alert("해당 글이 삭제되었습니다")
                location.href="/board/${memberId}";
            }
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
            alert("북마크를 취소했습니다");
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
            <a class="bookmarkBtn" id="create" onclick="createBookmark()">
                <img style="width: 40px" src="/img/bookmark_cancel.png">
            </a>
            <a class="bookmarkBtn" id="cancel" onclick="cancelBookmark()">
                <img style="width: 40px" src="/img/bookmark_ok.png">
            </a>
            <button type="button" class="btn btn-sm btn-primary" onclick="location.href='/board/${memberId}'">목록</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnUp" onclick="location.href='/board/${memberId}/update/${postId}'">수정</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnDel" onclick="deleteBoard()">삭제</button>
        </div>

        <!--댓글 목록-->
        <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
            <c:forEach var="comment" items="${comments}">
                <div class="media text-muted pt-3">
                    <p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">
                        <span class="d-block">
                            <strong class="text-gray-dark">${comment.writer}</strong>
                            <span style="padding-left: 950px; font-size: 9pt">
                                <c:if test="${comment.member.memberId == memberId}">
                                    <a style="padding-right:5px" id="btnUpdate" type="button" onclick="location.href='/board/${memberId}/post/${postId}/update/${comment.id}'">수정</a>
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
        </div>

    <!--댓글 입력창-->
    <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
        <form name="form" id="form" role="form" action="/board/${memberId}/post/${postId}" method="post">
            <div class="row">
                <div class="col-sm-10">
                    <textarea name="content" class="form-control" name="writer" id="content" rows="3" placeholder="댓글을 입력하세요"></textarea>
                </div>
                <div class="col-sm-2">
                    <input path="reg_id" name="writer" class="form-control" id="reg_id" value="${member.nickname}" readonly/>
                    <button class="btn btn-sm btn-primary" type="submit" id="btnReplySave" style="width: 100%; margin-top: 10px">등록</button>
                </div>
            </div>
        </form>
    </div>
    <br><br><br>
    </div>
</article>
</body>
</html>
