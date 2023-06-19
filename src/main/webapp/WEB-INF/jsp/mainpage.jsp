<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<style>
    .menu {
        width: 300px;
    }
    .mypage {
        float: left;
    }

</style>
<div>
    <div>
        <a href="/mypage/${memberId}" style="text-decoration:none;">
            <img class="menu" id="mypage" src="/img/mypage_logo.png">
        </a>
        <a href="/board/${memberId}">
            <img class="menu" id="board" src="/img/board_logo.png">
        </a>
    </div>
    <div>
        <a href="/places/${memberId}">
            <img class="menu" id="place" src="/img/place_logo.png">
        </a>
        <img class="menu" src="/img/footprint.png">
    </div>

</div>
