<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Connecting</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
<style>
* {
	font-family: 'Gaegu', cursive !important;
}

#join_form {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 3px solid;
	width: 900px; /* Could be more or less, depending on screen size */
	padding: 16px;
	text-align: center;
	font-size: 30px;
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


.form-control {
	width: 70%;
	margin: 22px 0 5px 0;
	display: inline-block;
	font-size: 23px;
}

span {
	font-size: 18px;
}

#logo {
	text-align:center;
	font-size: 4rem; 
	margin-top: 100px;
}
</style>


</head>
<body>

	<div class="container" style="margin-bottom: 44px;">
		<div class="row align-items-center">
			<div class="col-lg-5 col-md-12">
				<a href="../main.jsp">
					<h1 id="logo">Connecting</h1>
				</a>
			</div>
		</div>
	</div>


	<form class="border-info" action="joinProcess.net" method="post"
		id="join_form">
		<h1>회원가입</h1>
		<hr>
		<div id="input_cont">
			<b>아이디</b>
			<div>
				<input type="text" class="form-control" name="id"
					placeholder="아이디를 입력하세요" ><br>
					<span id="message"></span>
			</div>


			<b>비밀번호</b>
			<div>
				<input type="password" class="form-control pass_ch" name="pass"
					placeholder="비밀번호를 입력하세요" >
				<input type="password" class="form-control pass_ch" name="pass1"
					placeholder="비밀번호를 다시 입력하세요" >
				<br>
				<span id="password_message"></span>
			</div>

			<b>이름</b>
			<div>
				<input type="text" class="form-control" name="name"
					placeholder="이름을 입력하세요" maxLength="5"
					required> <br>
				<span id="name_message"></span>
			</div>

			<b>이메일 주소</b>
			<div>
				<input class="form-control" type="text" name="email"
					placeholder="이메일을 입력하세요" 
					maxLength="30" required><br>
				<span id="email_message"></span>
			</div>
		</div>



		<div class="clearfix">
			<button type="submit" class="submitbtn">확인</button>
		</div>


	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>

	<script>
	$(function() {
		var checkid=false;
		var checkemail=false;
		var checkpass=false;
		$("input:eq(0)").on('keyup',
				function() {
				
			$("#message").empty();//처음에 pattern에 적합하지 않은 경우 메시지 출력 후 적합한 데이터를 입력해도 계속 같은 데이터
			//[A-Za-z0-9_]의 의미가 \w
			var pattern = /^\w{5,12}$/;
			var id = $("input:eq(0)").val();
			if (!pattern.test(id)) {
				$("#message").css('color','red')
							.html ("영문자 숫자 _로 5~12자 가능합니다.");
				checkid=false;
				return;
			}
			
			$.ajax({
				url: "idcheck.net",
				data : {"id" : id},
				success : function(resp) {
					if (resp == -1) {//db에 해당 id가 없는 경우
						$("#message").css('color', 'green').html("사용 가능한 아이디 입니다.");
						checkid=true;
					} else {//db에 해당 id가 있는 경우 (0)
						$("#message").css('color', 'blue').html("사용중인 아이디 입니다.");
						checkid=false;
					}
			}
		});//ajax end
	})//id keyup end
	
	$("input[name='pass1']").on('keyup',
			function() {
		var pass = $("input[name='pass']").val()
		var pass1 = $("input[name='pass1']").val()
		
		if(pass != pass1){
			$("#password_message").css('color', 'red')
			   .html("비밀번호가 일치하지 않습니다.");
		    checkpass=false;
		}else{
		   $("#password_message").css('color', 'green')
					   .html("비밀번호가 일치합니다.");
		    checkpass=true;
		 }
	})
	
	$("input:eq(4)").on('keyup',
			function() {
		$("#email_message").empty();
		//[A-Za-z0-9_]와 동일한 것이 \w 입니다.
		//+는 1회 이상 반복을 의미하고 {1,}와 동일합니다.
		//\w+는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
		var pattern =/^\w+@\w+[.]\w{3}$/;
		var email = $("input:eq(4)").val();
		if (!pattern.test(email)) {
			$("#email_message").css('color', 'red')
							   .html("이메일 형식이 맞지 않습니다.");
			checkemail=false;
		}else{
			$("#email_message").css('color', 'green')
							   .html("이메일 형식에 맞습니다.");
			checkemail=true;
		}
	});//email keyup 이벤트 처리 끝
	
	$('form').submit(function() {
		
		if(!checkid){
			alert("사용가능한 id로 입력하세요.");
			$("input:eq(0)").val('').focus();
			return false;
		}
		
		if(!checkpass){
			alert("비밀번호를 정확히 입력하세요.");
			$("input[name='pass1']").val('').focus();
			return false;
		}
		
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(4)").focus();
			return false;
		}
	});
	})//ready end

	</script>

</body>
</html>
