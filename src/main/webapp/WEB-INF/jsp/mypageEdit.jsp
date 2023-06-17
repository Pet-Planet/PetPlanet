<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 수정</title>
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
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var roadAddr = data.roadAddress;
                    document.getElementById("sample4_roadAddress").value = roadAddr;
                }
            }).open();
        }
    </script>
</head>
<body>
<h1>회원 수정</h1>
<form action="/mypage/edit" method="post">
    <table>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" value="${member.nickname}" required></td>
        </tr>
        <tr>
            <td>주소</td>
            <td>
                <input type="text" name="address" value="${member.address}" required>
                <input class="button" type="button"  onclick="sample4_execDaumPostcode()" value="주소검색">
            </td>
        </tr>
        <tr>
            <td>반려동물 종류</td>
            <td>
                <select name="petType">
                    <option value="강아지">강아지</option>
                    <option value="고양이">고양이</option>
                    <option value="새">새</option>
                </select>
        </tr>
        <tr>
            <td>반려동물 이름</td>
            <td><input type="text" name="petName" value="${member.petName}" required></td>
        </tr>
    </table>
    <br>
    <button type="submit">수정</button>
</form>
</body>
</html>