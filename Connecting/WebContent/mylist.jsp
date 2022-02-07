<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	.list_cont{
		margin-top:200px;
	}
	#title{
		margin-bottom:100px;
		text-align:center;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="container list_cont">
		<h1 id="title">관심글 리스트</h1>
		
		<span></span>
		<table class="table table-hover table-bordered" >
			<tr>
				<td>test</td>
			</tr>
			<tr>
				<td>test</td>
			</tr>
			<tr>
				<td>test</td>
			</tr>
			<c:if test="${listcount > 0}">
			
			</c:if>
			<c:if test="${listcount <= 0}">
				<tr>
					<th>
						<h3>관심 등록한 게시글이 없습니다.</h3>
					</th>
				</tr>
			</c:if>
		</table>
	</div>
	
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>
</body>
</html>