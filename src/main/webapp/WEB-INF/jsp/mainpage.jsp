<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>Pet Planet</h1>

    <a href="<c:url value="/mypage/${memberId}" />">마이페이지</a>
    <a href="<c:url value="/board/${memberId}" />">자유게시판</a>
    <a href="">장소게시판</a>
</div>
