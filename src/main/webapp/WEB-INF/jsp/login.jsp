<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<%--대표 이미지 같은거 들어가면 좋을 것 같아요--%>
<div>
    <img width="300" src="../../img/image01.jpg">
</div>
<div>
    <a href="${reqUrl}"><img width="500px" src="<c:url value="../../img/kakao_login.png"/>"></a>
</div>
