<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>공지사항 상세 페이지</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
	</script>
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
    <style>
		 * {
	         font-family: 'Gaegu', cursive!important;
	      }
	     h2 {
     	   text-align:center;
	     }
	</style>
</head>
<body>
	 <jsp:include page="../header.jsp" />
	 <div class="container justify-content-center">
	 
		<h2 style="margin-top: 50px;"> 공지사항 </h2>
	
	 	<div style="font-size:25px">제목</div>
	 	&nbsp;&nbsp;&nbsp; <c:out value="${noticedata.title}" />
	
		<div style="font-size:25px">내용</div>
		<textarea class="form-control" rows="10" cols="100" style="font-size:23px;margin-bottom: 30px;" readOnly>${noticedata.content}</textarea>
		
				
		<c:if test="${id == 'admin'}"> 
			<div id="ok" align="right">
	  			<button type="submit" class="btn btn-outline-info btn-lg" 
	  				onclick="location.href='noticeModifyView.mgr?notice_id=${noticedata.notice_id}'"
	  				style="margin-top: 40px;margin-bottom: 40px;">수정</button>
	 		</div>
		</c:if>
	 </div>
</body>
