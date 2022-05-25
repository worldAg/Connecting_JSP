<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Register</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/memberForm.css" />
</head>
<body>
	<main class="form-member w-100 m-auto">
		<form name="joinform" action="joinProcess.net" method="post">
			<a href="<%=request.getContextPath()%>/index.jsp">
				<img class="mb-4" src="<%=request.getContextPath()%>/resources/img/connecting/logo.png" 
					alt="Connecting" width="280" height="101">
			</a>
    		<h1 class="h3 mb-3 fw-normal">회원가입</h1>
    		
		    <div class="form-floating mb-2">
				<input type="text" class="form-control" id="floatingId" name="id" placeholder="Id" maxLength="10">
				<label for="floatingId">아이디</label>
				<span class="id-message"></span>
			</div>
			<div class="form-floating">
      			<input type="password" class="form-control" id="floatingPassword" name="pass" placeholder="Password" maxLength="20">
      			<label for="floatingPassword">비밀번호</label>
    		</div>
    		<div class="form-floating mb-2">
      			<input type="password" class="form-control" id="floatingPassword-2" name="pass2" placeholder="Password" maxLength="20">
      			<label for="floatingPassword-2">비밀번호 확인</label>
      			<span class="pass-message"></span>
    		</div>
			<div class="form-floating mb-2">
				<input type="text" class="form-control" id="floatingId" name="name" placeholder="Name" maxLength="5">
				<label for="floatingId">이름</label>
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingEmail" name="email" placeholder="Email" maxLength="20">
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
