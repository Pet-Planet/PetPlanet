<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>

    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
        //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.

                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample4_postcode').value = data.zonecode;
                    document.getElementById("sample4_roadAddress").value = roadAddr;
                    document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

                    document.getElementById("sample4_engAddress").value = data.addressEnglish;

                    // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                    if(roadAddr !== ''){
                        document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                    } else {
                        document.getElementById("sample4_extraAddress").value = '';
                    }
                    var guideTextBox = document.getElementById("guide");
                    // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                    if(data.autoRoadAddress) {
                        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                        guideTextBox.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(예상 도로명 주소 : ' + expRoadAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else if(data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        guideTextBox.innerHTML = '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(예상 지번 주소 : ' + expJibunAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else {
                        guideTextBox.innerHTML = '';
                        guideTextBox.style.display = 'none';
                    }
                }
            }).open();
        }
    </script>

    <jsp:include page="header2.jsp" />
</head>
<body>
<form action="/mypage/${memberId}/edit" method="post">
    <br><br><br>
    <table align="center">
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" value="${member.nickname}" required></td>
        </tr>
        <tr>
            <td>주소</td>
            <td>
                <input type="text" name="address" id="sample4_roadAddress" value="${member.address}" required>
                <input class="button" type="button" onclick="sample4_execDaumPostcode()" value="주소검색">

                <input type="hidden" id="sample4_postcode" placeholder=" 우편번호">
                <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
                <input type="hidden" id="sample4_detailAddress" placeholder="나머지 주소">
                <input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
                <input type="hidden" id="sample4_engAddress" placeholder="영문주소">
            </td>
        </tr>
        <tr>
            <td>반려동물 종류</td>
            <td>
                <select name="petType">
                    <option value="강아지">강아지</option>
                    <option value="고양이">고양이</option>
                    <option value="햄스터">햄스터</option>
                    <option value="토끼">토끼</option>
                    <option value="새">새</option>
                </select>
        </tr>
        <tr>
            <td>반려동물 이름</td>
            <td><input type="text" name="petName" value="${member.petName}" required></td>
        </tr>
    </table>
    <br>
    <div align="center">
    <button type="submit">수정</button>
    </div>
</form>
</body>
</html>