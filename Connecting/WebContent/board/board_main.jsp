<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Board List</title>	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico">
	<style>
		* {
			font-family: 'Gaegu', cursive !important;
		}
		
		a {
			text-decoration-line: none !important;
		}
		
		#radioBtn-div {
			margin-top: 50px;
		}
		
		.radioBtn {
			width: 100px;
			font-size: 18px;
		}
		
		#board-container {
			border: 2px solid;
			border-radius: 20px;
		}
		
		.hide-container {
			display: none !important;
		}
		
		.write-sort {
			margin-top: 10px;
			margin-left: 10px;
			text-align: right;
		}
		
		#goWrite {
			font-weight: bold;
		}
		
		#sort {
			text-align-last: center;
			text-align: center;
			-ms-text-align-last: center;
			-moz-text-align-last: center;
			width: auto; 
			display: inline-block;
			
		}
		
		.alert {
			padding: 0px; 
			font-size: 18px;
			text-align: center;
			width: 350px;
		}
		
		th, td {
			font-size: 20px;
			text-aling: center;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container" id="radioBtn-div">
		<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
			<input type="radio" class="btn-check" name="select_what" id="radioBtn-board"  value="board" checked />
	  		<label class="btn btn-outline-primary radioBtn" for="radioBtn-board" checked>게시글</label>
	  		<input type="radio" class="btn-check" name="select_what" id="radioBtn-notice" value="notice" />
	  		<label class="btn btn-outline-primary radioBtn" for="radioBtn-notice">공지/문의</label>
		</div>
	</div>
</body>
</html>