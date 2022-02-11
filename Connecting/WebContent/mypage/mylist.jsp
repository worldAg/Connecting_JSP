<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		margin-bottom:50px;
	}
	#title{
		margin-bottom:100px;
		text-align:center;
	}
	.gray{color:gray}
	
	.page-list{
		margin-bottom:100px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container list_cont">
		<h1 id="title">관심글 리스트</h1>
		
		<span></span>
		<table class="table table-hover table-bordered" >
			<c:forEach var="h" items="${heartboards}">
				<tr class="table-info">
					<td><a href="BoardDetailAction.bo?num=${h.board_id }" class="list-group-item">
							<p>${h.title}</p> 
							<small id="emailHelp" class="form-text text-muted"> 								
								<c:if test="${h.content.length() >= 30}">
									<c:set var='content' value='...' />
								</c:if>
								<c:if test="${h.content.length() < 30}">
									<c:set var='content' value='' />
								</c:if>
								${fn:substring(h.content,0,30)}${content}
							</small>
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="center-block page-list">
		<ul class="pagination justify-content-center">
			<c:if test="${page <= 1 }">
				<li class="page-item">
					<a class="page-link gray">이전&nbsp;</a>
				</li>
			</c:if>
			<c:if test="${page > 1}">
				<li class="page-item">
					<a href="myHeartList.my?page=${page-1}"
					   class="page-link">이전&nbsp;</a>
				</li>
			</c:if>
			
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page}">
					<li class="page-item active">
						<a class="page-link">${a}</a>
					</li>
				</c:if>
				<c:if test="${a != page }">
					<li class="page-item">
						<a href="myHeartList.my?page=${a}" class="page-link">${a}</a>
					</li>
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxpage}">
				<li class="page-item">
					<a class="page-link gray">&nbsp;다음</a>
				</li>
			</c:if>
			<c:if test="${page < maxpage}">
				<li class="page-item">
					<a href="myHeartList.my?page=${page+1}"
						class="page-link">&nbsp;다음</a>
				</li>
			</c:if>
		</ul>
	</div>
	
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>
</body>
</html>