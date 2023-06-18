<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<title>PetPlanet🙋‍♀️</title>
<h1>게시글 작성</h1>
<script>
    $(function (){
        // 서버에서 받아온 데이터 체크하기
        <%--var checkedCategory = ${board.category};--%>
        <%--$("input[name=category][value=" + checkedCategory + "]").attr("checked", true);--%>
    });
</script>
<body>
<form name="form_board" method="post" action="/board/${memberId}/update/${postId}">
    <input type="hidden" name="_method" value="put"/>
    <div>
        <input type="radio" name="category" value="[잡담]" checked/>잡담
        <input type="radio" name="category" value="[질문]"/>질문
        <input type="radio" name="category" value="[정보]"/>정보
    </div>
    <div>
        제목
        <input name="title" id="title" size="80" value="${board.title}">
    </div>
    <div>
        내용
        <textarea name="content" id="content" rows="4" cols="80">${board.content}</textarea>
    </div>
    <div style="text-align: center">
        <button type="submit" id="btnSave">확인</button>
        <button type="button" onclick="history.back()">취소</button>
    </div>
</form>
</body>
</html>