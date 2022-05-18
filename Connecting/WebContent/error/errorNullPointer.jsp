<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>NullPointerException</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/error.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container error">
		<img src="<%=request.getContextPath()%>/resources/img/sorry.png" width="500px"><br>
		<h3>죄송합니다.	${message}</h3>
		<p>
			해당 게시글이 존재하지 않습니다. <br />
			<%=request.getRequestURL()%><%=request.getQueryString()%>
		</p>	
		<button type="button" class="btn btn-primary btn-lg" onClick="location.href='BoardList.bo'">게시글 목록 페이지로 이동 &#62;</button>
	</div>
</body>
</html>