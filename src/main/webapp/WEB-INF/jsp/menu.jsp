<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    a.menu_a {
        text-decoration: none;
    }
    img.menu_a {
        width: 150px;
        margin-right: 20px;
        margin-left: 20px;
    }
</style>
<div>
    <a class="menu_a" href="<c:url value="/mypage/${memberId}" />">
        <img class="menu_a" src="/img/mypage_logo.png">
    </a>
    <a class="menu_a" href="<c:url value="/board/${memberId}" />" >
        <img class="menu_a" src="/img/board_menu_logo.png">
    </a>
    <a class="menu_a" href="<c:url value="/places/${memberId}" />" >
        <img class="menu_a" src="/img/place_menu_logo.png">
    </a>
</div>