<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pet Planet</title>
    <link rel="stylesheet" href="/static/headermenu.css" />
</head>
<body>
    <div id="head">
        <div class="logo">
            <a href="/main/${memberId}" style="text-decoration:none;">
                <img id="logo" src="/img/logo.png" alt="Pet Planet Logo"/>
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" style="text-decoration:none;" href="<c:url value="/board/${memberId}" />" >
                <img class="menu_a" src="/img/board_menu_logo.png">
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" style="text-decoration:none;" href="<c:url value="/places/${memberId}" />" >
                <img class="menu_a" src="/img/place_menu_logo.png">
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" style="text-decoration:none;" href="<c:url value="/mypage/${memberId}" />">
                <img class="menu_a" src="/img/mypage_logo.png">
            </a>
        </div>
    </div>
    <hr/>
</body>
</html>