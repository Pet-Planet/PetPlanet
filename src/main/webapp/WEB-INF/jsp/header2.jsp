<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        div {
            text-align: center;
            margin: 20px;
        }
        #logo { width: 200px;  }
        /*#head { background-color: #D8D2CA}*/
        a.menu_a {
            text-decoration: none;
        }
        img.menu_a {
            width: 150px;
            margin-right: 20px;
            margin-left: 20px;
        }
        div#head {
            display: flex;
            margin: 0;
        }
        div.logo, div.menu {
            flex: 1;
        }
    </style>
    <title>Pet Planet</title>
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