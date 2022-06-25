<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원 정보 수정</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/memberForm.css" />
</head>
<body>
	<main class="form-member w-100 m-auto">
		<form name="memberModifyForm" action="memberModifyAction.my" method="post" enctype="multipart/form-data">
			<a href="<%=request.getContextPath()%>/index.jsp">
				<img class="mb-4" src="<%=request.getContextPath()%>/resources/img/connecting/logo.png" 
					alt="Connecting" width="280" height="101">
			</a>
			<h1 class="h3 mb-3 fw-normal">회원 정보 수정</h1>
			
			<!-- 프로필 이미지 변경 -->
			<div class="mb-4">
				<c:set var="resourcesPath" value='${pageContext.request.contextPath}${"/resources/"}'/>
				<c:if test='${empty memberInfo.profile_img}'>
					<c:set var='imgPath' value='${resourcesPath}img/profile.png' />
				</c:if>
				<c:if test='${!empty memberInfo.profile_img}'>
					<c:set var='imgPath' value='${resourcesPath}${"profile_upload/"}${memberInfo.profile_img}'/>
				</c:if>
				<div id="showImage">
					<img id="profileImg" src="${imgPath}" alt="profile">
					<span id="originalImg" style="display:none">${memberInfo.profile_img}</span> <!-- 원본 이미지 파일명 확인을 위한 span -->
				</div>
				<span id="resetBtn">
					<img id="xBtn" src="./resources/img/x-circle.png">
				</span>
				
				<%-- accept 속성은 업로드할 파일 타입을 설정함
					예) <input type="file" accept="파일확장자|audio/*|video/*|image/*">
				   	- audio/* : 모든 타입의 오디오 파일
				   	- image/* : 모든 타입의 이미지 파일										--%>
				<input type="file" id="profile" name="profile_img" accept="image/*">
			</div>
			
			<div class="form-floating mb-2">
				<input type="text" class="form-control" id="modifyId" name="id" value="${memberInfo.id}" 
					placeholder="Id" maxLength="10" readOnly>
				<label for="modifyId">아이디</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="pass" value="${memberInfo.password}" 
					placeholder="Password" maxLength="20">
				<label for="floatingPassword">비밀번호</label>
			</div>
			<div class="form-floating mb-2">
				<input type="password" class="form-control" id="floatingPassword-2" name="pass2" value="${memberInfo.password}" 
					placeholder="Password" maxLength="20">
				<label for="floatingPassword-2">비밀번호 확인</label>
				<span class="pass-message"></span>
			</div>
			<div class="form-floating mb-2">
				<input type="text" class="form-control" id="floatingId" name="name" value="${memberInfo.name}" 
					placeholder="Name" maxLength="5">
				<label for="floatingId">이름</label>
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingEmail" name="email" value="${memberInfo.email}" placeholder="Email" maxLength="20">
				<label for="floatingEmail">이메일</label>
				<span class="email-message"></span>
			</div>
			<div class="mb-3">
				<button type="submit" class="w-100 btn btn-lg btn-primary">확인</button>
			</div>
		</form>
	</main>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/join.js"></script>
</body>
</html>