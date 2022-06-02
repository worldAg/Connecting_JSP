<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Connecting</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico" />
	<style>
		.recent-board {
			width: 1000px;
			margin: auto;
		}
		
		.go-btn {
			margin-top: 20px;
			margin-bottom: 10px;
		}
	</style>
</head>

<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div class="recent-board">

			<div class="go-btn">
				<button type="button" class="btn btn-primary btn-lg go-list">새로운 소식을 확인하세요 ></button>
			</div>
			
			<div class="row row-cols-1 row-cols-md-3 g-4" id="mainBoard">
		
			</div>
			
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>
