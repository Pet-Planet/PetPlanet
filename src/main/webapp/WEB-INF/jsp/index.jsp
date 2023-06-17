<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Javascript로 Ajax 요청보내기</title>
</head>
<body>
<h1>POST방식의 요청</h1>
<button type="button" onclick="sendRequest()">POST방식으로 요청 보내기!</button>
<p id="text"></p>
</body>
<script>
    function sendRequest(){
        var httpRequest = new XMLHttpRequest();
        //POST방식의 Ajax통신
        httpRequest.open("GET", "memberinfo", true);
        httpRequest.setRequestHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3amkxNzgwQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi6rmA64-E7Z2sIiwiaWQiOjEsImV4cCI6MTY4NzE4MDQ4Mn0.Tm-eEJ2UkcRRACeRxd-S58oJ26edQrCNAmO1F20C0avMxnZUZeMwWtvV7PlSuW2O2xQYTc5CMSMCUOgC8WSioQ");

        httpRequest.onreadystatechange = function(){
            if(httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200){
                document.getElementById("text").innerHTML = httpRequest.responseText;
            }
        }
    }
</script>
</html>