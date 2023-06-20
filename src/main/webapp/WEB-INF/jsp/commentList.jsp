<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<fmt:parseDate var="parsedDate" value="${comment.createdDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
<fmt:formatDate var="newDate" value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss "/>
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
        <%--window.onload=function matchMember () {--%>
        <%--    const btn1 = document.getElementById('btnDel');--%>
        <%--    const btn2 = document.getElementById('btnUp');--%>

        <%--    if(${comment.memberId} == ${memberId}) {--%>
        <%--        btn1.style.visibility = 'visible';--%>
        <%--        btn2.style.visibility = 'visible';--%>
        <%--    }--%>
        <%--}--%>

        //댓글 삭제
        function deleteReview(commentId) {
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

        //댓글 수정 페이지 삽입
        function includePage(memberId, postId, commentId) {
            var url = "/board/" + memberId + "/post/" + postId + "/update/" + commentId;

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState === 4 && this.status === 200) {
                    document.getElementById("includedPage").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
        }
    </script>
    <jsp:include page="header2.jsp" />
</head>
<br><br><br>
<body>
    <table align="center">
        <tr>
            <th>작성자</th>
            <th>내용</th>
            <th>작성일</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td style="width:100px;">${comment.writer}</td>
                <td style="width:400px;">${comment.content}</td>
                <td style="width:100px;">${comment.createdDate}</td>
                <td style="width:50px;"><button id="btnUpdate" type="button" onclick="location.href='/board/${memberId}/post/${postId}/update/${comment.id}'">수정</button></td>
<%--                <td><button id="btnUpdate" type="button" onclick="includePage(${memberId}, ${postId}, ${comment.id})">수정</button></td>--%>
                <td style="width:50px;"><button id="btnDelete" type="button" onclick="deleteReview(${comment.id})">삭제</button></td>
            </tr>
        </c:forEach>
    </table>
    <div id="includedPage"></div>   <!--수정 페이지 삽입-->
</body>
<br><br><br>
<jsp:include page="commentForm.jsp" />
</html>