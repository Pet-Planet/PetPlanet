<% String jwtToken = response.getHeader("Authorization"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
    function sendRequest(){
        var token = "<%=jwtToken%>";
        console.log("헤더에서 잘 가져왔나" + token);
        $.ajax({
            type: "GET",
            url: "http://localhost:8088/memberinfo", // 2) 컨트롤러에 보내서 헤더로부터 회원 id를 반환받는다
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);   // 1) 헤더에 토큰을 넣어준다
            },
            success: function (id) {
                console.log(id);
                location.href = "http://localhost:8088/main/" + id;     // 3) 찾아온 id를 가지고 메인으로 간다.
                // 4) 이제 이 id를 계속 가지고 다니면서 회원 구분한다
            }
        })
    }


</script>
<body>
<h1>환영합니다</h1>
<button type="button" onclick="sendRequest()">PET Planet 시작하기</button>
</body>
</html>