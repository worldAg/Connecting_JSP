<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Board List</title>	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
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
		
		select {
			text-align-last: center;
			text-align: center;
			-ms-text-align-last: center;
			-moz-text-align-last: center;
		}
		
		.alert {
		
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
	  		<label class="btn btn-outline-primary radioBtn" for="radioBtn-notice">공지사항</label>
		</div>
			
		<c:if test="${!empty sessionScope.id}">
		</c:if>			 
			<button type="button" class="btn btn-info" id="writeBtn">글쓰기</button>
	</div>
	
	
	
	<div class="container" id="board-container">
		<c:if test="${listcount >= 1}">
			<div class="rows" style="text-align: right">
				<select class="form-control" id="orderby" style="width:auto; margin-bottom: 2em; display: inline-block;">
					<option value="0">작성순 &#8595;</option>
					<option value="1">관심순 &#8595;</option>
					<option value="2">시작 임박순 &#8595;</option>
					<option value="3">마감 임박순 &#8595;</option>
				</select>
			</div>			
			
			<div class="row">
				<div class="col-md-3">
					<div class="alert alert-info" style="padding: 0px; font-size: 1.5rem; text-align: center;">
						<strong>전체 게시글: </strong><span id="num-list" style="font-weight: normal;">${ listcount }개</span>
					</div>
				</div>
				<div class="offset-md-9"></div>
			</div>
			<div>
				<table class="table table-hover table-striped" id="board-table">
					<thead>
			            <tr>
			                <th scope="col">번호</th>
			                <th scope="col">제목</th>
			                <th scope="col">작성자</th>
			                <th scope="col">작성일</th>		                
			                <th scope="col">시작일</th>
			                <th scope="col">종료일</th>		                
			               	<th scope="col">관심수</th>
			            </tr>
		        	</thead>
		        	
		        	<tbody>
		        		<c:set var="num" value="${listcount - (page - 1) * limit}" />
		        		<c:forEach var="b" items="${boardlist }">
		        			<tr>
		        				<td> <%--글 번호 --%>
		        					<c:out value="${num}" />
									<c:set var="num" value="${num - 1}" />
		        				</td>
		        				<td> <%--글 제목 --%>
		        					<c:if test="${b.category == 0}">
										[전시회]
									</c:if>
									<c:if test="${b.category == 1}">
										[박람회]
									</c:if>
									<c:if test="${b.category == 2}">
										[버스킹]
									</c:if>
									<c:if test="${b.category == 3}">
										[연극/공연]
									</c:if>	        				
		        					<a href="BoardDetailAction.bo?num=${ b.board_id }"><c:out value="${ b.title }" /></a>
		        				</td>
		        				<td>
		        					<c:out value="${ b.id }" />
		        				</td>
		        				<td>
		        					<c:out value="${ b.write_date }" />
		        				</td>	        				
		        				<td>
		        					<c:out value="${ b.start_date }" />
		        				</td>	        				
		        				<td>
		        					<c:out value="${ b.end_date }" />
		        				</td>	        				
		        				<td style="text-align:right;">
		        					<c:out value="${ b.heart_num }" />
		        				</td>
		        			</tr>
		        		</c:forEach>
		        	</tbody>
				</table>
			</div>
			
			
			
			
			
			
			<div class="center-block">
				<ul class="pagination justify-content-center" id="board-pagination">
					<c:if test="${page <=1 }">
						<li class="page-item">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>					
					<c:if test="${page > 1 }">
						<li class="page-item">
							<a href="BoardList.bo?page=${page - 1 }&orderby=${orderby }" class="page-link">이전&nbsp;</a>
						</li>
					</c:if>
					
					<c:forEach var="a" begin="${startpage }" end="${endpage }">
						<c:if test="${a == page }">
							<li class="page-item active">
								<a class="page-link">${a }</a>
							</li>
						</c:if>
						<c:if test="${a != page }">
							<li class="page-item">
								<a href="BoardList.bo?page=${a }&orderby=${orderby }" class="page-link">${a }</a>
							</li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page >= maxpage }">
						<li class="page-item">
							<a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item">
							<a href="BoardList.bo?page=${page + 1 }&orderby=${orderby }" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>			
		</c:if>	
		
		<c:if test="${listcount == 0 }">
			<h3>조회된 결과가 없습니다.</h3>
		</c:if>
	</div>
	
	<div class="container hide-container" id="notice-container" style="margin-top: 50px;">
		<div style="width: 100%; height: 500px; overflow: auto;">
			<table class="table table-hover">
				<thead>
		            <tr>
		                <th scope="col">번호</th>
		                <th scope="col">제목</th>
		                <th scope="col">작성일</th>
		            </tr>
        		</thead>
		        <tbody>		            
		        </tbody>
			</table>
		</div>
	</div>
	
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
	<script src="<%=request.getContextPath()%>/resources/js/board_list.js"></script>	
</body>
</html>