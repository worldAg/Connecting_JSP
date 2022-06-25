<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Connecting</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" />
</head>

<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div class="recent-board">
			
			<div class="go-btn">
				<button type="button" class="btn btn-outline-primary btn-lg" id="goList">새로운 소식을 확인하세요 &#62;</button>
			</div>
			
			<div class="row row-cols-1 row-cols-md-3 g-4" id="mainBoard">
				<!-- 최신글 6개 카드 형식으로 보여 줌 -->
			</div>
			
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>