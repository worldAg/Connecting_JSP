<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>404 error</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/error.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container error">
		<img src="<%=request.getContextPath()%>/resources/img/sorry.png" width="500px"><br>
		<h3>죄송합니다.	${message}</h3>
		<p>
			요청하신 페이지를 찾을 수 없습니다. <br />
			<%=request.getRequestURL()%> <br />
		</p>	
	</div>
</body>
</html>