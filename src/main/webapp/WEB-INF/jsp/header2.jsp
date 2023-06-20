<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pet Planet</title>
    <link rel="stylesheet" href="/static/headermenu.css" />
    <script type="text/javascript" src="/static/menu.js"></script>
</head>
<body>
    <div id="head">
        <div class="logo">
            <a href="/main/${memberId}" style="text-decoration:none;">
                <img id="logo" src="/img/main_logo_ver2.png" alt="Pet Planet Logo"/>
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" id="a_board" style="text-decoration:none;" href="<c:url value="/board/${memberId}" />" >
                커뮤니티
<%--                <img class="menu_a" src="/img/board_logo_letter2.png">--%>
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" id="a_palce" style="text-decoration:none;" href="<c:url value="/places/${memberId}" />" >
                펫플레이스
<%--                <img class="menu_a" src="/img/place_logo_letter2.png">--%>
            </a>
        </div>
        <div class="menu">
            <a class="menu_a" id="a_mypate" style="text-decoration:none;" href="<c:url value="/mypage/${memberId}" />">
                마이페이지
<%--                <img class="menu_a" src="/img/mypage_logo_letter2.png">--%>
            </a>
        </div>
    </div>
    <hr/>
</body>
</html>