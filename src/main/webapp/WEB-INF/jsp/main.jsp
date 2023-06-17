<% String jwtToken = response.getHeader("Authorization"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.http.HttpHeaders" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
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
            url: "http://localhost:8088/memberinfo", //여기에서 현재 유저 멤버 아이디 가져옴
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            },
            success: function (id) {
                console.log(id);
                location.href = "http://localhost:8088/mypage/" + id;
            }
        })
    }


</script>
<body>
<h1>환영합니다</h1>
<button type="button" onclick="sendRequest()">PET Planet 시작하기</button>
</body>
</html>