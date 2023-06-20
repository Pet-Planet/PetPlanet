<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Planet</title>
    <style>
        table {
            background-color: #008800;
            border: none;
            border-spacing: 2px;
        }
        tr {
            background-color: white;
        }
        tr td {
            padding: 20px;
        }
        #profile {
            width: 100px;
        }
    </style>
    <jsp:include page="header2.jsp" />
</head>
<body>
<br><br><br>
<table align="center">
    <tr>
        <td style="width:100px;"><img id="profile" src="${member.kakaoProfileImg}"></td>
        <td style="width:300px;">
            <h1 style="font-size: 30px"><b>${member.nickname}</b></h1>
            ${member.kakaoEmail}
            <br>
            [${member.petType}] ${member.petName}
        </td>
    </tr>
</table>
<br>
<table align="center">
    <tr>
        <td style="width:200px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/reservations"/>'>예약 내역</a></td>
        <td style="width:200px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/reviews"/>'>나의 리뷰</a></td>
    </tr>
    <tr>
        <td colspan="2"><a href='<c:url value="/mypage/${memberId}/edit"/>'>회원 정보 수정</a></td>
    </tr>
    <tr>
        <td colspan="2"><a href='<c:url value="/mypage/${memberId}/posts"/>'>내가 쓴 글</a></td>
    </tr>
    <tr>
        <td colspan="2"><a href='<c:url value="/mypage/${memberId}/comments"/>'>내가 쓴 댓글</a></td>
    </tr>
    <tr>
        <td colspan="2"><a href='<c:url value="/mypage/${memberId}/bookmarks"/>'>북마크</a></td>
    </tr>
</table>
<h4 align="center"><a href='<c:url value="/mypage/${memberId}/withdraw"/>'>탈퇴하기</a></h4>
</body>
</html>