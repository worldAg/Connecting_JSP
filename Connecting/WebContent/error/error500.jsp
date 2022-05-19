<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>500 error</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/error.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container error">
		<img src="<%=request.getContextPath()%>/resources/img/sorry.png" width="500px"><br>
		<h3>죄송합니다.	${message}</h3>
		<p>
			요청한 페이지의 서버 내부 에러가 발생했습니다. <br />
			서비스 이용에 불편을 드려 죄송합니다.
		</p>
		<button type="button" class="btn btn-primary btn-lg" onClick="location.href='boardList.bo'">게시글 목록 페이지로 이동 &#62;</button>
	</div>
</body>
</html>