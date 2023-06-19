function bookmark() {
    $.ajax({
        url: "/board/${memberId}/post/${postId}/bookmark",
        type: "POST",
        contentType: 'application/json',
        success : function (data) {
            alert("성공");
        }
    })
}
