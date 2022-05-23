<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<style>
* {
	font-family: 'Gaegu', cursive!important;
}

#update_form {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 3px solid;
	width: 900px; /* Could be more or less, depending on screen size */
	padding: 16px;
	text-align:center;
	font-size:30px;
	
}

hr {
	border: 2px solid #17a2b8;
	margin-bottom: 25px;
}

b+div {
	width: 100%;
	padding: 10px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #d7f8fd;
	
}

b {
	width: 100%;
	display: block
}

.form-control{
	width: 70%;
	margin: 22px 0 5px 0;
	display: inline-block;
	font-size:23px;
}

span{
	font-size:18px;
}
</style>


</head>
<body>



	<form class="border-info" action="updateProcess.my" method="post" id="update_form">
		<h1>회원 정보 수정</h1>
		<hr>
		<div id="input_cont">
			<b>아이디</b>
			<div>
				<input type="text" class="form-control" name="id" placeholder="Enter id" value="${memberInfo.id}" readOnly>
			</div>
		
		
			<b>비밀번호</b>
			<div>
				<input type="password"  class="form-control pass_ch" name="pass" placeholder="Enter password" 
					value="${memberInfo.password}">
				<input type="password" class="form-control pass_ch" placeholder="Enter password again"
					value="${memberInfo.password}">
				<br><span id="password_message"></span>
			</div>
							
			<b>이름</b>
			<div>
				<input
				type="text" class="form-control" name="name" placeholder="Enter name" value="${memberInfo.name}" maxLength="5" required>
				<br><span id="name_message"></span>
			</div>
	
			<b>이메일 주소</b>
			<div>
				<input  class="form-control email_ch" type="text" name="email" placeholder="Enter email" value="${memberInfo.email}" maxLength="30" required>
				<input  class="form-control email_ch" type="text" name="email" placeholder="Enter email again" value="${memberInfo.email}" maxLength="30" required>
				<br><span id="email_message"></span>
			</div>
		</div>
		
		

		<div class="clearfix">
			<button type="submit" class="submitbtn">정보수정</button>
			<button type="reset" class="cancelbtn">다시작성</button>
		</div>
		
		
	</form>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
  </script>
  
  <script>
	var checkemail = true;
	var checkpass = true;
	$(".email_ch").on('keyup',
			function() {
				$("#email_message").empty();
				//[A-Za-z0-9_]와 동일한 것이 \w
				//+는 1회 이상 반복을 의미합니다. {1, }와 동일합니다.
				//\w+ 는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
				var pattern = /^\w+@\w+[.]\w{3}$/;
				var email = $("input:eq(4)").val();
				
				if(!pattern.test(email)) {
					$("#email_message").css('color','red').html("이메일 형식이 맞지 않습니다.")
					checkemail=false;
					
				}else{
					if(email == $("input:eq(5)").val()){
						$("#email_message").css('color','green').html("이메일이 일치 합니다.");
						checkemail=true;
					}else{
						$("#email_message").css('color','orange').html("이메일이 일치 하지 않습니다.");
						checkemail=false;
					}
				}
	});//email keyup 이벤트 처리 끝
	
	$("input:eq(3)").on('keyup',
		function() {
			$("#name_message").empty();
				
			if($("input:eq(3)").val()==""){
				$("#name_message").css('color','red').html("이름을 입력하세요.");

			}
				
	});
	
	$(".pass_ch").on('keyup',
			function() {
				$("#password_message").empty();
				
				var pass = $("input:eq(1)").val();
				
				
				
				if(pass == $("input:eq(2)").val()){
					$("#password_message").css('color','green').html("비밀번호가 일치 합니다.");
					checkpass=true;
				}else{
					$("#password_message").css('color','orange').html("비밀번호가 일치 하지 않습니다.");
					checkpass=false;
				}
				
				if(pass == ""){
					$("#password_message").css('color','red').html("비밀번호를 입력하세요.");
				}
				
	});

		
	

	
	$('form').submit(function() {
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(4)").focus();
			return false;
		}
		
		if(!checkpass){
			alert("비밀번호를 확인하세요");
			$("input:eq(1)").focus();
			return false;
		}
		
	})
  </script>
	
</body>
</html>