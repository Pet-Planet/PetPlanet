<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

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

            if(${board.memberId} == ${memberId}) {
                btn1.style.visibility = 'visible';
                btn2.style.visibility = 'visible';
            }
        }
    </script>
    <jsp:include page="header.jsp" />
    <jsp:include page="menu.jsp" />
</head>
<br><br><br>
<body>
    <table align="center">
        <tr>
            <th>번호</th>
            <th>작성자</th>
            <th>내용</th>
            <th>작성일</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.writer}</td>
                <td>${comment.content}</td>
                <td>${comment.createdDate}</td>
                <td><button type="button">수정</button></td>
                <td><button type="button">삭제</button></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/board/${memberId}/post/${postId}/comment" method="post">
        <br><br><br>
        <table align="center">
            <tr>
                <td>작성자</td>
                <td><input class="hidden" name="writer" size="80" value="${member.kakaoNickname}">${member.kakaoNickname}</td>
            </tr>
            <tr>
                <td>댓글</td>
                <td>
                    <textarea name="content" rows="4" cols="80" placeholder="댓글을 입력하세요"></textarea>
                    <button type="submit">등록</button>
                </td>
            </tr>
        </table>
        <div style="text-align: center">
        </div>
    </form>
</body>
<br><br><br>
</html>
