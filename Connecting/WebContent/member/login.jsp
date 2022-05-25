<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/memberForm.css" />
</head>

<body>
	<main class="form-member w-100 m-auto">
		<form name="loginform" action="loginProcess.net" method="post">
			<a href="<%=request.getContextPath()%>/index.jsp">
				<img class="mb-4" src="<%=request.getContextPath()%>/resources/img/connecting/logo.png" 
					alt="Connecting" width="280" height="101">
			</a>
    		<h1 class="h3 mb-3 fw-normal">로그인</h1>
    		
		    <div class="form-floating">
				<input type="text" class="form-control" id="floatingId" name="id" placeholder="Id">
				<label for="floatingId">아이디</label>
			</div>
			<div class="form-floating">
      			<input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
      			<label for="floatingPassword">비밀번호</label>
    		</div>
			<div class="checkbox mb-3">
      			<label>
        			<input type="checkbox" id="remember" name="remember-me" value="store">
        			&nbsp;&nbsp;&nbsp;아이디 기억하기
      			</label>
    		</div>
    		<div class="mb-3">
    			<button type="submit" class="w-100 btn btn-lg btn-primary">로그인</button>
    		</div>
    		<div>
    			<button type="button" class="w-33 btn btn-outline-secondary id-find" onclick="location.href='idfind.net'">
    				아이디 찾기
    			</button>
				<button type="button" class="w-33 btn btn-outline-secondary pw-find" onclick="location.href='pwfind.net'">
					비밀번호 찾기
				</button>
				<button type="button" class="w-33 btn btn-outline-success" onclick="location.href='register.net'">회원가입</button>
    		</div>
		</form>
	</main>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>
	<script>
		$(function() {
			const id = '${id}';
			if (id) { // 쿠키에 값이 저장된 경우
				$("#floatingId").val(id);
				$("#remember").prop('checked', true);
			}
		});
	</script>
</body>
</html>