<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Planet</title>
    <style>
        table {
            background-color: #98C0DC;
            border: none;
            border-spacing: 3px;
            font-family: 'GmarketSansMedium';
        }
        tr {
            background-color: white;
        }
        tr td {
            padding: 20px;
        }
        a {
            text-decoration-line: none; /* 링크의 밑줄 제거 */
            color: inherit; /* 링크의 색상 제거 */
        }
        h1 {
            margin-top: 0;
            margin-bottom: 15px; /* 줄간격을 조절합니다. */
            color: #98C0DC;
            display: flex;
        }
        p {
            margin-top: 0;
            margin-bottom: 5px; /* 줄간격을 조절합니다. */
        }
        h4 {
            font-family: 'GmarketSansMedium';
            color: lightgrey;
        }
        @font-face {
            font-family: 'GmarketSansMedium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
    </style>
    <jsp:include page="header2.jsp" />
    <script>
        function withdrawn(){
            if (window.confirm('정말 탈퇴하시겠습니까?'))
            {
                location.href='<c:url value="/mypage/${memberId}/withdraw"/>'
            }
            else
            {
                // They clicked no
            }
        }
    </script>
</head>
<br><br><br><br><br><br>
<body>
<table align="center">
    <tr>
        <td style="width:100px;"><img src="${member.kakaoProfileImg}" width="100" height="100"></td>
        <td style="width:300px;">
            <h1 style="font-size: 30px">
                <b>${member.nickname}</b> &nbsp;
                <a href='<c:url value="/mypage/${memberId}/messages"/>'>
                    <img src="/img/myMes.jpg" width="28" height="21">
                </a>
            </h1>
            <p>[ ${member.petType} ]&nbsp;&nbsp;${member.petName}</p>
            ${member.kakaoEmail}
        </td>
    </tr>
</table>
<br><br>
<table align="center">
    <tr style="height:100px;">
        <td  colspan="2" style="width:200px; text-align: center;">
            <a href='<c:url value="/mypage/${memberId}/reservations"/>'>
            <img src="/img/myRes.jpg" width="55" height="50"><br>
            예약 내역</a>
        </td>
        <td  colspan="2" style="width:200px; text-align: center;">
            <a href='<c:url value="/mypage/${memberId}/reviews"/>'>
                <img src="/img/myRev.jpg" width="50" height="50"><br>
            나의 리뷰</a>
        </td>
    </tr>
    <tr style="height:50px;">
        <td style="width:80px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/friends/friendList"/>'>친구 목록</a></td>
        <td style="width:80px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/friends"/>'>친구 검색</a></td>
        <td style="width:80px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/friends/friend-sent"/>'>보낸 신청</a></td>
        <td style="width:80px; text-align: center;"><a href='<c:url value="/mypage/${memberId}/friends/friend-received"/>'>받은 신청</a></td>
    </tr>
    <tr style="height:70px;">
        <td colspan="4"><a href='<c:url value="/mypage/${memberId}/edit"/>'>회원 정보 수정</a></td>
    </tr>
    <tr style="height:70px;">
        <td colspan="4"><a href='<c:url value="/mypage/${memberId}/posts"/>'>내가 쓴 글</a></td>
    </tr>
    <tr style="height:70px;">
        <td colspan="4"><a href='<c:url value="/mypage/${memberId}/comments"/>'>내가 쓴 댓글</a></td>
    </tr>
    <tr style="height:70px;">
        <td colspan="4"><a href='<c:url value="/mypage/${memberId}/bookmarks"/>'>북마크</a></td>
    </tr>
</table>
<h4 align="center"><a onclick="withdrawn()">탈퇴하기</a></h4>
<br><br><br>
</body>
</html>