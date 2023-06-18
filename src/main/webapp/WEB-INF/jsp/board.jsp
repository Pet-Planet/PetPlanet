<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<title>PetPlanet🙋‍♀️</title>
<script>
    $(document).ready(function (){
        $("#btnWrite").click(function (){
            //페이지 이동
            location.href = "/board/write";
        });
    });
</script>
<body>
<button type="button" id="btnWrite">작성하기</button>
<table border="1" width="600px">
    <tr>
        <th>번호</th>
        <th>카테고리</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    <c:forEach var="row" items="${boardList}">
        <tr>
            <td>${row.postId}</td>
            <td>${row.category}</td>
            <td>${row.title}</td>
            <td>${row.writer}</td>
            <td>
                <fmt:parseDate value="${ row.createdDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="yy.MM.dd HH:mm" value="${ parsedDateTime }" />
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>