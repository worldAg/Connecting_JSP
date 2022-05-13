<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Board List</title>	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico">
	<style>
		* {
			font-family: 'Gaegu', cursive !important;
		}
		
		a {
			text-decoration-line: none !important;
		}
		
		#board-container {
			margin-top: 30px;
			margin-bottom: 50px;
		}
		
		.write-orderby {
			margin-top: 10px;
			margin-left: 10px;
			text-align: right;
		}
		
		#writeBtn {
			font-weight: bold;
		}
		
		#orderby {
			text-align-last: center;
			text-align: center;
			-ms-text-align-last: center;
			-moz-text-align-last: center;
			width: auto; 
			display: inline-block;
			
		}
		
		.alert {
			padding: 0px; 
			font-size: 18px;
			text-align: center;
			width: 400px;
		}
		
		.list-count {
			font-weight: normal;
		}
		
		tr, td {
			font-size: 20px;
		}
		
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container" id="board-container">
		<div class="rows write-orderby">
			<c:if test="${!empty sessionScope.id}">
				<button type="button" class="btn btn-info" id="writeBtn">
					글쓰기 <img src="<%=request.getContextPath()%>/resources/img/edit.png">
				</button>
			</c:if>			 
			<c:if test="${listcount >= 1}">
				<select class="form-control" id="orderby">
					<option value="0">작성순 &#8595;</option>
					<option value="1">관심순 &#8595;</option>
					<option value="2">시작 임박순 &#8595;</option>
					<option value="3">마감 임박순 &#8595;</option>
				</select>
			</c:if>
		</div>
		
		<!-- 게시글 존재하는 경우 -->
		<c:if test="${listcount >= 1}">
			<!-- 게시글 개수 -->
			<div class="alert alert-info">
				<c:choose>
					<c:when test="${!empty category}">
						<c:if test="${category == 'all'}">
							<c:set var="cate" value="카테고리&#45;전체" />
						</c:if>
						<c:if test="${category == '0'}">
							<c:set var="cate" value="카테고리&#45;전시회" />
						</c:if>
						<c:if test="${category == '1'}">
							<c:set var="cate" value="카테고리&#45;박람회" />
						</c:if>	
						<c:if test="${category == '2'}">
							<c:set var="cate" value="카테고리&#45;버스킹" />
						</c:if>	
						<c:if test="${category == '3'}">
							<c:set var="cate" value="카테고리&#45;연극/공연" />
						</c:if>	
						<c:if test="${!empty loc}">
							<c:if test="${loc == 'all'}">
								<c:set var="local" value="지역&#45;전체" />
							</c:if>
							<c:if test="${loc == '0'}">
								<c:set var="local" value="지역&#45;서울" />
							</c:if>
							<c:if test="${loc == '1'}">
								<c:set var="local" value="지역&#45;경기&#47;인천" />
							</c:if>	
							<c:if test="${loc == '2'}">
								<c:set var="local" value="지역&#45;대전&#47;충청&#47;강원" />
							</c:if>	
							<c:if test="${loc == '3'}">
								<c:set var="local" value="지역&#45;부산&#47;대구&#47;경상" />
							</c:if>
							<c:if test="${loc == '4'}">
								<c:set var="local" value="지역&#45;광주&#47;전라&#47;제주" />
							</c:if>	
						</c:if>
						<span id="listCount">
							${cate}
							<c:if test="${!empty loc}">&#44; ${local}<br /></c:if>
							조회 결과 : ${listcount}개
						</span>&nbsp;&nbsp;
						<button type="button" class="btn btn-outline-secondary all-list">전체 목록</button>
					</c:when>
					<c:when test="${!empty keyword}">
						검색어 '${keyword}' 조회 결과 : <span class="list-count">${ listcount }개</span>&nbsp;&nbsp;
						<button type="button" class="btn btn-outline-secondary all-list">전체 목록</button>
					</c:when>
					<c:otherwise>
						전체 게시글 : <span id="listCount">${ listcount }개</span>
					</c:otherwise>
				</c:choose>
			</div>
			
			<table class="table table-hover table-striped" id="board-table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>		                
						<th scope="col">시작일</th>
						<th scope="col">종료일</th>		                
						<th scope="col">관심</th>
					</tr>
		        </thead>
		        <tbody>
		        	<c:set var="num" value="${listcount - (page - 1) * limit}" />
		        	<c:forEach var="b" items="${boardlist}">
		        		<tr>
		        			<td> <%--글 번호 --%>
		        				<c:out value="${num}" />
								<c:set var="num" value="${num - 1}" />
		        			</td>
		        			<td> <%--글 제목 --%>
		        				<c:if test="${b.category == 0}">&#91;전시회&#93;</c:if>
								<c:if test="${b.category == 1}">&#91;박람회&#93;</c:if>
								<c:if test="${b.category == 2}">&#91;버스킹&#93;</c:if>
								<c:if test="${b.category == 3}">&#91;연극/공연&#93;</c:if>	        				
		        				<a href="BoardDetailAction.bo?num=${ b.board_id }">
		        					<c:out value="${ b.title }" />
		        				</a>
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
		        			<td>
		        				<c:out value="${ b.heart_num }" />
		        			</td>
		        		</tr>
		        	</c:forEach>
		        </tbody>
			</table>
			
			<!-- 페이징 -->
			<div class="center-block">
				<ul class="pagination justify-content-center" id="board-pagination">
					<c:if test="${page <= 1}">
						<li class="page-item">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>					
					<c:if test="${page > 1}">
						<li class="page-item">
							<a href="BoardList.bo?page=${page - 1}&orderby=${orderby}" class="page-link">이전&nbsp;</a>
						</li>
					</c:if>
					
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page}">
							<li class="page-item active">
								<a class="page-link">${a}</a>
							</li>
						</c:if>
						<c:if test="${a != page}">
							<li class="page-item">
								<a href="BoardList.bo?page=${a}&orderby=${orderby}" class="page-link">${a}</a>
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
							<a href="BoardList.bo?page=${page + 1}&orderby=${orderby}" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>			
		</c:if>	
		
		<c:if test="${listcount == 0}">
			<h3>조회된 결과가 없습니다.</h3>
		</c:if>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
	<script src="<%=request.getContextPath()%>/resources/js/board_list.js"></script>	
</body>
</html>