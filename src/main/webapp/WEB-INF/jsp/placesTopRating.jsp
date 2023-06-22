<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <style>
    .average-rating {
      position: relative;
      appearance: none;
      color: transparent;
      width: auto;
      display: inline-block;
      vertical-align: baseline;
    }

    .average-rating::before {
      --percent: calc(var(--rating) / 5 * 100%);
      content: '★★★★★';
      position: absolute;
      top: 0;
      left: 0;
      color: rgba(0, 0, 0, 0.2);
      background: linear-gradient(90deg, gold var(--percent), rgba(0, 0, 0, 0.2) var(--percent));
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }

    /*상세 설명*/
    .item-place {
      background-color: #ffffff;
      border-radius: 1rem;
      box-shadow: 0 0 0 rgba(0, 0, 0, 0.05), 0 2.1rem 3rem rgba(0, 0, 0, 0.0500000007), 0 33.3rem 13.3rem rgba(0, 0, 0, 0.0099999998), 0 52rem 14.6rem rgba(0, 0, 0, 0);
      height: auto;
      overflow: hidden;
      position: relative;
      width: 100%;
      text-align: left;
    }

    .btn {
      background-color: #fff;
      color: #98C0DC;
      transition: background-color 0.3s ease, color 0.3s ease;
    }

    .btn:hover {
      background-color: #98C0DC;
      color: #fff;
    }
  </style>
  <title></title>
</head>
<body class="text-center">
<br>
  <p style="color: #98C0DC; font-size: 40px; font-family: 'GmarketSansMedium'">Hot Petplace </p>
<br>
    <div class="container text-center">
      <div class="item-place">
        <div class="row">
          <c:forEach var="place" items="${placesTopRating}">
            <div class="col-md-6" onclick="goToPlaceDetail(${place.placeId})">
              <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                  <div class="col-md-4" style="position: relative;">
                    <img src="<c:url value='${place.imageUrl}'/>" width="100%" height="100%" style="position: absolute; top: 0; bottom: 0; left: 0; right: 0;">
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title">${place.placeTitle}</h5>
                      <p class="card-text">${place.address}</p>
                      <div class="text-end">
                        <p class="card-text" style="position: absolute; top: 8px; right: 25px;">
                          <c:choose>
                            <c:when test="${place.placeType eq 'restaurant'}">
                              <img src="/img/식당.png" width="50" height="50"/>
                            </c:when>
                            <c:when test="${place.placeType eq 'cafe'}">
                              <img src="/img/cafe.png" width="50" height="50"/>
                            </c:when>
                            <c:when test="${place.placeType eq 'hotel'}">
                              <img src="/img/hotel.png" width="40" height="40"/>
                            </c:when>
                            <c:otherwise>
                              기타
                            </c:otherwise>
                          </c:choose>
                        </p>
                      </div>
                      <p class="card-text">
                        <meter class="average-rating" min="0" max="5" value="${place.avgRating}"
                               title="${place.avgRating} out of 5 stars"
                               style="--rating: ${place.avgRating}"> ${place.avgRating} out of 5
                        </meter>
                          <fmt:formatNumber value="${place.avgRating}" pattern="0.0"/> (${place.reviewCnt})
                      </p>
                      <div class="text-end">
                        <button type="button" class="btn btn-outline-info"
                                onclick="goToPlaceDetail(${place.placeId})">상세 보기
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
        <c:if test="${totalPages > 1}">
          <ul class="pagination justify-content-center mt-4">
            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
              <a class="page-link" href="/places/${memberId}?page=${currentPage - 1}">&laquo;</a>
            </li>
            <c:forEach begin="1" end="${totalPages}" varStatus="loop">
              <li class="page-item ${currentPage == loop.index ? 'active' : ''}">
                <a class="page-link" href="/places/${memberId}?page=${loop.index}">${loop.index}</a>
              </li>
            </c:forEach>
            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
              <a class="page-link" href="/places/${memberId}?page=${currentPage + 1}">&raquo;</a>
            </li>
          </ul>
        </c:if>
      </div>
    </div>
<script>
  function goToPlaceDetail(placeId) {
    window.location.href = '/places/${memberId}/placeDetail/' + placeId;
  }
  function search() {
    filterForm.action="/places/filter/${memberId}/" ;
    filterForm.submit();
  };
</script>
<!-- 부트스트랩 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
