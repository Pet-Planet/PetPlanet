<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <style>
        table {
            background-color: #008800;
            border: none;
            border-spacing: 1px;
        }
        tr {
            background-color: #FFFF88;
        }
        tr td {
            padding: 3px;
        }
        #profile {
            width: 30px;
        }
    </style>
</head>
<body>
<h1>마이페이지</h1>
<table>
    <tr>
        <td><img id="profile" src="${member.kakaoProfileImg}"></td>
        <td>
            ${member.nickname} (${member.kakaoEmail})
                <br>
                ${member.petType} (${member.petName})
        </td>
        <td>쪽지</td>
    </tr>
</table>
<br>
<table>
    <tr>
        <td style="width : 120px; text-align: center;" ><a href='<c:url value="/"/>'>예약 내역</a></td>
        <td style="width : 120px; text-align: center;">나의 리뷰</td>
    </tr>
    <tr>
        <td colspan="2"><a href='<c:url value="/mypage/${memberId}/edit"/>'>회원 정보 수정</a></td>
    </tr>
    <tr>
        <td colspan="2">내가 쓴 글</td>
    </tr>
    <tr>
        <td colspan="2">내가 쓴 댓글</td>
    </tr>
    <tr>
        <td colspan="2">북마크</td>
    </tr>
</table>
<a href='<c:url value="/mypage/${memberId}/withdraw"/>'>탈퇴하기</a>
</body>
</html>