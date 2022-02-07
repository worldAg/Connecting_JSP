<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="../css/bootstrap.css">
<meta charset="UTF-8">
<style>
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
</style>
<title>로그인 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 <script>
 	$(function(){
 		$(".join").click(function(){
 			location.href="join.net";
 		});
 	 	})
 </script>
 </head>
 <body>
 <form name="loginform" action="loginProcess.net" method="post"><h1 onclick="location.href='/Connecting/main.jsp'"> Connecting</h1>
 	<hr>
 	<div class="form-group">
  <fieldset>
    <label class="form-label" for="idInput"></label>
    <input class="form-control" id="idInput" type="text" placeholder="아이디를 입력하세요" >
  </fieldset>
</div>
 	 <div class="form-group">
      <label for="exampleInputPassword1" class="form-label mt-4"></label>
      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="비밀번호를 입력하세요">
 	 <hr>
 	 <div class="clearfix">
 	 <button type="button" class="btn btn-primary" onclick="location.href='../main.jsp'">로그인</button>
 	<button type="button" class="btn btn-primary" onclick="location.href='idfind.jsp'" style= "margin-top: 100px; ">아이디 찾기</button>
 	<button type="button" class="btn btn-primary" onclick="location.href='pwfind.jsp'" style= "margin-top: 100px; ">비밀번호 찾기</button>
 	<button type="button" class="btn btn-primary" onclick="location.href='register.jsp'" style= "margin-top: 100px; ">회원가입</button>
 	<jsp:include page="naverlogin.jsp" />
 	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
 	</script>
 	</div>
 	 </div>
 	 </form>
 	 </body>
 	 </html>