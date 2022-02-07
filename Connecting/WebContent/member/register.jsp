<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="../css/bootstrap.css">
<meta charset="UTF-8">
<style>
.h1 {
	text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	
</script>
<title>회원가입 페이지 입니다</title>
</head>
<body>
	<div class="container" style="margin-bottom: 44px;">
		<div class="row align-items-center">
			<div class="col-lg-5 col-md-12">
				<a href="../main.jsp">
					<h1 id="logo" style="font-size: 4rem; margin-top: 100px;" >Connecting</h1>
				</a>
			</div>
		</div>
	</div>
	<div class="form-group">
		<fieldset>
			<label class="form-label" for="idInput"></label> <input
				class="form-control" id="idInput" type="text"
				placeholder="아이디를 입력하세요">
		</fieldset>
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1" class="form-label mt-4"></label> <input
			type="password" class="form-control" id="exampleInputPassword1"
			placeholder="비밀번호를 입력하세요">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword2" class="form-label mt-4"></label> <input
			type="password" class="form-control" id="exampleInputPassword1"
			placeholder="비밀번호를 다시 입력하세요">
		</div>
	<div class="form-group">
		<label for="nameInput" class="form-label mt-4"></label> <input
			type="name" class="form-control" id="nameInput"
			placeholder="이름을 입력하세요">
	</div>
	<div class="form-group">
		<label for="emailInput" class="form-label mt-4"></label> <input
			type="eamil" class="form-control" id="eamilInput"
			placeholder="이메일을 입력하세요">
		</div>
		
		<button type="button" class="btn btn-primary btn-lg" onclick ="location.href='login.jsp'">확인</button>
</body>
</html>