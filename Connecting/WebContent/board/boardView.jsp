<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
	
	<style>
		* {
			font-family: 'Gaegu', cursive!important;
		}
	</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container" style="margin-top: 100px; margin-bottom: 100px;">
		<div class="row">
			<div class="col-md-4">
				<img src="images/icecream.png" style="width: 100%; height: 300px; border-radius: 10%" />
			</div>
			<div class="offset-md-1 col-md-7">
				<h2 style="font-weight: bold;">
					<c:if test="${boarddata.category == 0 }">
						[전시회]
					</c:if>
					<c:if test="${boarddata.category == 1 }">
						[박람회]
					</c:if>
					<c:if test="${boarddata.category == 2 }">
						[버스킹]
					</c:if>
					<c:if test="${boarddata.category == 3 }">
						[연극/공연]
					</c:if>
					<c:out value="${boarddata.title }" />
				</h2>
				<hr />
				<%-- 				
				<img src="images/icecream.png" style="width: 100px; height: 80px;" />
				<div style="display: inline-block">
					<h3>주최자: ${boarddata.host_name }</h3>
					<h3>장소: ${boarddata.address }</h3>
					<h3>날짜: ${boarddata.start_date } ~ ${boarddata.end_date }</h3>
					<h3>시간: ${boarddata.start_time } ~ ${boarddata.end_time }</h3>
				</div>
				--%>
				<div class="d-flex justify-content-center align-items-center">
					<img src="images/icecream.png" style="width: 150px; height: 150px; display: inline-block; border-radius: 50%;" />
					<div style="display: inline-block; padding-left: 10%">
					    <h3>주최자: ${boarddata.host_name }</h3>
						<h3>장소: ${boarddata.address }</h3>
						<h3>날짜: ${boarddata.start_date } ~ ${boarddata.end_date }</h3>
						<h3>시간: ${boarddata.start_time } ~ ${boarddata.end_time }</h3>
					</div>
				</div>
			</div>
		</div>
		
		<div class="mb-3" style="margin-top: 100px;">
			<label for="exampleFormControlTextarea1" class="form-label">본문</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" style="font-size: 1.5rem; font-weight: normal" readonly><c:out value="${boarddata.content }" /></textarea>
		</div>
		
		<div class="row">			
			<div class="offset-md-9 col-md-3 d-flex justify-content-end">
				<button type="button" class="btn btn-info">게시글 수정하기</button>
			</div>
		</div>

	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>
</html>