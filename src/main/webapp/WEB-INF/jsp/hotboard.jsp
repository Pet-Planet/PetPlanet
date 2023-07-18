<c:forEach var="row" items="${hotboard.content}">
    <tr>
        <td>HOT</td>
        <td>${row.category}</td>
        <td id="board-title"><a href="/board/${memberId}/post/${row.postId}" >${row.title}</a></td>
        <td>${row.writer}</td>
        <td>
            <fmt:parseDate value="${ row.createdDate }" pattern="yyyy-MM-dd'T'HH:mm" var="createdTime" type="both" />
            <fmt:formatDate value="${ createdTime }" pattern="yyyy-MM-dd" var="time" />
            <c:choose>
                <c:when test="${time < today}">
                    ${time}
                </c:when>
                <c:otherwise>
                    <fmt:formatDate value="${ createdTime }" pattern="HH:mm" var="time2" />
                    ${time2}
                </c:otherwise>

            </c:choose>
        </td>
        <td>${row.countView}</td>
    </tr>
</c:forEach>