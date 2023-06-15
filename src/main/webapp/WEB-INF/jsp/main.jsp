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

<body>
<!-- 헤더정보(숨겨진 정보) 가져오기  -->

<%
    // 헤더에 들어온 jwt 토큰 값 가져오기
    String jwtToken = response.getHeader("Authorization");

    out.print(jwtToken);
%>
</body>
</html>