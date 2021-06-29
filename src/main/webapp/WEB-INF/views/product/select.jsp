<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productSelect.css">
<title>${product.productName}</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="top-container">
		<div class="carousel-container">
			<div class="carousel-slide">
				<img src="/resources/upload/product/${product.files[product.files.size()-1].fileName}" class="carousel-images" id="last-clone"/>
				<c:forEach items="${product.files}" var="file">
				<img class="carousel-images" src="/resources/upload/product/${file.fileName}">
				</c:forEach>
				<img src="/resources/upload/product/${product.files[0].fileName}" class="carousel-images" id="first-clone"/>	
			</div>	
			<div class="carousel-btn">
				<i class="fas fa-chevron-left" id="prev-btn"></i>
				<c:forEach begin="0" end="${product.files.size()-1}" var="i">
					<div class="circle" id="circle_${i}"></div>
				</c:forEach>
				<i class="fas fa-chevron-right" id="next-btn"></i>
			</div>
		</div>
		<div class="top__info">
			<div class="top__productName">${product.productName}</div>
			<div class="priceNstatus">
				<div class="top__price">${product.productPrice}</div>
				<div class="top__status">${product.productStatus}</div>
			</div>
			<div class="top__smalls">
				<div class="top__hitNheart">
					<div class="top__small top__hit"><i class="far fa-eye"></i> ${product.productHit}</div>
					<div class="top__small top__heart"><i class="fas fa-heart"></i> ${product.productHeart}</div>
					<div class="top__small top__productDate">${product.productDate}</div>
				</div>
				<div class="top__small top__category"><i class="fas fa-tag"></i>${product.category.categoryName}</div>
				<div class="top__small top__location"><i class="fas fa-map-marker-alt"></i> ${product.location.locationName}</div>
				<div class="top__small top__nego">${product.productNego}</div>
			</div>
			<div class="top__btns">
				<c:if test="${principal.username eq product.username}">
					<div class="dropdown"> 
					<input type="hidden" id="productStatus" value="${product.productStatus}">
					<form action="/product/setStatus?productNum=${product.productNum}" method="post">
					<div class="form-group">
						  <label for="sel1">상태 변경</label>
						  <select class="form-control" id="status" name="productStatus" onchange="submit()">
						    <option>판매 중</option>
						    <option>예약 중</option>
						    <option>판매완료</option>
						  </select>
						</div>
						</form>
					</div>
					
					<a class="top-btn btn-edit" href="../update/${product.productNum}">수정하기</a>
					<a class="top-btn btn-contact" href="/product/rewrite?productNum=${product.productNum}">끌올하기</a>

					<a class="top-btn btn-del" href="../delete/${product.productNum}" onclick="if(confirm('삭제하시겠습니까?')==false){return false;}">삭제하기</a>
				</c:if>
				<c:if test="${principal.username != product.username}">
					<c:if test="${chat ne 0}">
						<div class="top-btn btn-contact"><a type="button" href="/chat/chatList" onclick="if(confirm('연락하시겠습니까?')==false){return false;}">연락하기</a></div>
					</c:if>
					<c:if test="${chat eq 0}">
						<div class="top-btn btn-contact"><a class="msg_send_btn" type="button" href="/chat/chatSendInList?room=0&otherUser=${seller.username}&content=※${principal.username}님이 ${product.productName}을 구매하고 싶어해요!" onclick="if(confirm('연락하시겠습니까?')==false){return false;}">연락하기</a></div>
					</c:if>
					<div class="top-btn btn-nego"><a href="/notification/nego?productNum=${product.productNum}&notiRecvUser=${seller.username}">가격 제안하기</a></div>
					<div class="btn-heart"></div>
					<div class="btn-report" onclick="openReport('${product.productNum}')"><i class="fas fa-exclamation-triangle"></i> 신고</div>
				</c:if>
			</div>
			<c:if test="${principal.username eq product.username}">

			</c:if>
			<div class="hidden">
				<input type="hidden" class="heartValue" value="${heart}">
				<input type="hidden" class="productNum" value="${product.productNum}">
			</div>
		</div>
	</div>
		<div class="seller-container" onclick="goSellerPage(${seller.code})">
			<div class="seller-left">
				<div class="seller__photo"><img src="/resources/upload/member/${sellerFile.fileName}"></div>
				<div class="seller__name">${seller.name}</div>
			</div>
			<div class="seller_locNdate">
				<div class="seller__location">${sellerLocation.locationName}</div>
				<div class="seller__joinDate">${seller.joinDate}</div>
			</div>
			<div class="seller__rating">
				<div class="rating__content"><div class="rating__title">상품 상태</div> <div class="rate">${rating.avgState}</div> (${rating.countState})</div>
				<div class="rating__content"><div class="rating__title">거래 매너</div> <div class="rate">${rating.avgManner}</div> (${rating.countManner})</div>
				<div class="rating__content"><div class="rating__title">응답 속도</div> <div class="rate">${rating.avgSpeed}</div> (${rating.countSpeed})</div>
			</div>
		</div>
		<div class="product-content">
			${product.productContent}
		</div>

		
</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/productSelect.js"></script>
</body>
</html>