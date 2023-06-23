<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>error</title>
    <jsp:include page="header2.jsp" />
<head/>
<body>

    <script>
        /* 리뷰 및 예약관련 에러처리 */
        alert("${errorMessage}");
        window.location.href = "/places/${memberId}/placeDetail/${placeId}";
    </script>
</body>
<html/>
