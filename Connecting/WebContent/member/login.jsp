<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap"
	rel="stylesheet">
<style>
* {
	font-family: 'Gaegu', cursive !important;
}
#login_form {
	background-color: black;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 3px solid;
	width: 900px; /* Could be more or less, depending on screen size */
	padding: 16px;
	text-align: center;
	font-size: 30px;
}
h1 {
    text-align:center;
    margin-top: 100px;
}

.clearfix {
  text-align:center;
}
page {
    margin-top: 50px;
}

button {
	margin-bottom : 30px;
	text-align:center;
}
.container {
	width : 40%!important;
}
.front {
	text-align:center;
}
</style>
<title>로그인 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 <script>
 	$(function(){
 		$(".join").click(function(){
 			location.href="register.net";
 		});
 	 	})
 </script>
 </head>
 <body>
 <div class="container">
 <h1 onclick="location.href='main.jsp'"> Connecting</h1>
 
 <hr>
 <form name="loginform" action="loginProcess.net" method="post">
 	<div class="form-group">
  <fieldset>
    <label class="form-label" for="idInput"></label>
    <input class="form-control" id="idInput" type="text" name="id" placeholder="아이디를 입력하세요" >
  </fieldset>
</div>
 	 <div class="form-group">
      <label for="exampleInputPassword1" class="form-label mt-4"></label>
      <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="비밀번호를 입력하세요">
 	 <hr>
 	 <div class="clearfix">
 	 <div class="front">
 	 <button type="submit" class="btn btn-primary">로그인</button>
 	 </div>
 	<button type="button" class="btn btn-primary" onclick="location.href='idfind.net'" >아이디 찾기</button>
 	<button type="button" class="btn btn-primary" onclick="location.href='pwfind.net'" >비밀번호 찾기</button>
 	<button type="button" class="btn btn-primary join" >회원가입</button>
 	<jsp:include page="naverlogin.jsp" />
 	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
 	</script>
 
 	</div>
 	 </div>
 	 </form>
 	 </div>
 	 </body>
 	 </html>