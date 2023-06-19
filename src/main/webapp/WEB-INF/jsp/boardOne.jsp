<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--글 한 개 조회하는 페이지--%>
<html>
<%@ include file="header.jsp"%>
<style>
    #view {
        float: right;
    }
    div.boardOne {
        margin: auto;
        text-align: center;
        width: 600px;
    }
    div.inside {
        text-align: justify;
    }
    div#bookmark {
        display: inline-block;
        float: right;
    }
    div#boardBtn {
        float: left;
    }
    #btnDel, #btnUp, #create, #cancle{
        visibility: hidden;
    }
</style>
<%--<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>--%>
<script>
    window.onload=function matchMember () {
        const btn1 = document.getElementById('btnDel');
        const btn2 = document.getElementById('btnUp');
        const cancel = document.getElementById('cancel');
        const bookmark = document.getElementById('create');

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
                "postId": ${postId},
                "memberId": ${memberId}
            }),
        }).then((response) => response.json())
            .then((data) => {
                console.log(data);
                if(data) {
                    cancel.style.visibility = "visible";
                    bookmark.style.visibility = "hidden";
                }
                else {
                    bookmark.style.visibility = "visible";
                    cancel.style.visibility = "hidden";
                }

            });

    }

    function deleteById() {
        fetch("/board/${memberId}/delete/${postId}", {
            method: "DELETE"
        }).then((response) => response.json())
        location.href="/board/${memberId}";
    }
    function bookmark() {
        fetch("/board/${memberId}/post/${postId}/bookmark", {
            method: "POST"
        }).then((response) => console.log("response", response))

        // 버튼 클릭하면 북마크 버튼 사라지고 북마크 취소 버튼 나타나기




    }
    function bookmarkCancel() {
        fetch("/board/${memberId}/post/${postId}/bookmark", {
            method: "DELETE"
        }).then((response) => console.log("response", response))

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
            <span id="view">${board.countView}</span>
        </div>
        <hr>
        <div class="inside">
            ${board.content}
        </div>
        <div class="boardBtn">
            <div class="inside" id="boardBtn">
                <button type="button" onclick="location.href='/board/${memberId}'">목록</button>
                <button type="button" id="btnUp" onclick="location.href='/board/${memberId}/update/${postId}'">수정</button>
                <button type="button" id="btnDel" onclick="deleteById()">삭제</button>
            </div>
            <div id="bookmark">
                <button class="bookmarkBtn" id="create" type="button" onclick="bookmark()">북마크</button>
                <button class="bookmarkBtn" id="cancel" type="button" onclick="bookmarkCancel()">북마크 취소</button>
            </div>
        </div>
    </div>

</body>
</html>
