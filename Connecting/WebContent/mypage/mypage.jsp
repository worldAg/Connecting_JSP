<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Mypage</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container menu-title">
		<h1>마이페이지</h1>
		<c:if test="${sessionScope.id == 'admin'}">
			<a type="button" class="btn btn-primary" id="goMgr" href="mgrMain.mgr">관리자 메뉴 <img src="<%=request.getContextPath()%>/resources/img/settings.png"></a>
		</c:if>
	</div>
	<div class="container">
		<div class="row">
		
			<!-- 프로필 이미지 등 회원정보 -->
			<div class="col-md-5">
				<div id="showImage">
					<c:if test='${empty memberInfo.profile_img}'>
						<img id="profileImg" src="<%=request.getContextPath()%>/resources/img/profile.png">
					</c:if>  
					<c:if test='${!empty memberInfo.profile_img}'>
						<img id="profileImg" src="<%=request.getContextPath()%>/resources/profile_upload/${memberInfo.profile_img}">
					</c:if>
				</div>
				<div>
					<div class="info">
						<span id="info-id">${memberInfo.id}</span>
						<span id="info-name">&#47;${memberInfo.name}</span>
					</div>
					<div class="info">
						${memberInfo.email}
					</div>
					<div class="info">
						${memberInfo.reg_date} 가입
					</div>
				</div>
				<div id="goModify">
					<a type="button" href="memberModify.my" class="btn rounded-pill bg-success">프로필 및 정보 변경</a>
				</div>
			</div>
			
			<!-- 작성글 리스트 및 관심글 리스트 -->
			<div class="col-md-7">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link active" data-bs-toggle="tab" href="#myList">나의 게시글</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-bs-toggle="tab" href="#myHeart" id="heartList">나의 관심글</a>
					</li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade active show" id="myList"></div>
					<div class="tab-pane fade" id="myHeart"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/resources/js/mypage.js"></script>
</body>
</html>