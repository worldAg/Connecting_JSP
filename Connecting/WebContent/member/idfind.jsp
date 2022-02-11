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
<script src="${pageContext.request.contextPath}js/jquery-3.6.0.js"></script>
<style>
* {
	font-family: 'Gaegu', cursive !important;
}

#idfind_form {
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
.border-info {
	text-align:center;
}
</style>


</head>
<body>
<jsp:include page="../header.jsp" />
	<form class="border-info" action="idfindProcess.net" method="post"
		id="idfind_form">
		<h1>아이디 찾기</h1>
		<hr>
		<div id="input_cont">
			<b></b>
			<div>
				<input type="text" class="form-control" name="name"
				maxLength="5"	placeholder="이름을 입력하세요" >
				<span id="name_message"></span>
			</div>
			
			<b></b>
			<div>
				<input class="form-control" type="text" name="email"
					placeholder="이메일을 입력하세요" 
					maxLength="30" required><br>
				<span id="email_message"></span>
			</div>
		</div>
		<hr>


		<div class="clearfix">
			<button type="submit" class="submitbtn">이메일로 아이디 전송</button>
		</div>

	</form>
</body>
</html>