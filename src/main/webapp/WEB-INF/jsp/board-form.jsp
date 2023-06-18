<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<title>PetPlanet🙋‍♀️</title>
<h1>게시글 작성</h1>
<script>
    $(document).ready(function (){
        $("#btnSave").click(function (){
            var category = ${"input[name=category]"}.val();
            var title = ${"#title"}.val();
            var content = ${"content"}.val();
            if(title == ""){
                alert("제목을 입력하세요");
            }
            document.form_board.submit();
        });
    });
</script>
<body>
<form name="form_board" method="post" action="/board/${memberId}/post">
    <div>
        <input type="radio" name="category" value="[잡담]" checked/>잡담
        <input type="radio" name="category" value="[질문]"/>질문
        <input type="radio" name="category" value="[정보]"/>정보
    </div>
    <div>
        제목
        <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
    </div>
    <div>
        내용
        <textarea name="content" id="content" rows="4" cols="80"></textarea>
    </div>
    <div style="text-align: center">
        <button type="submit" id="btnSave">확인</button>
        <button type="reset">취소</button>
    </div>
</form>
</body>
</html>