<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<html>
<h1>게시글 작성</h1>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(function (){
        $("btnSave").click(function () {
            var title = $("#title").val();
            var content = $("#content").val();
            var category = $("#category").val();
            if(title = "") {
                alert("제목을 입력하세요");
                $("#form_board").title.focus();
                return;
            }
            if(content == "") {
                alert("내용을 입력하세요");
                $("#form_board").content.focus();
                return;
            }
            $("#form_board").action="/board/${memberId}/update/${postId}";
            $("#form_board").submit();

        })
    });
</script>
<body>
<form id="form_board" method="post" >
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
        <button type="button" id="btnSave">확인</button>
        <button type="button" onclick="history.back()">취소</button>
    </div>
</form>
</body>
</html>