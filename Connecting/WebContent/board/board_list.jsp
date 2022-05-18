<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Board List</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/boardList.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container" id="board-container">
		<div class="rows write-orderby">
			<button type="button" class="btn btn-info" id="writeBtn">
				글쓰기 <img src="<%=request.getContextPath()%>/resources/img/edit.png">
			</button>
			<c:if test="${listcount >= 1 && empty keyword}">
				<span>보기</span>
				<select class="form-control" id="viewcount">
					<option value="5">5</option>
					<option value="7">7</option>
					<option value="10" selected>10</option>
					<option value="15">15</option>
				</select>
				<span>정렬</span>
				<select class="form-control" id="orderby">
					<option value="0">등록순 &#8595;</option>
					<option value="1">관심순 &#8595;</option>
					<option value="2">시작 임박순 &#8595;</option>
					<option value="3">마감 임박순 &#8595;</option>
				</select>
			</c:if>
		</div>
		
		<!-- 게시글 개수 -->
		<div class="alert alert-info">
			<c:choose>
				<c:when test="${category == 'all' && loc == 'all'}">
					전체 게시글 : <span id="listCount">${ listcount }개</span>
				</c:when>
				<c:when test="${!empty keyword}">
					검색어 '${keyword}' 조회 결과 : <span class="list-count">${ listcount }개</span>&nbsp;&nbsp;
					<button type="button" class="btn btn-outline-secondary all-list">전체 목록</button>
				</c:when>
				<c:otherwise>
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
						${cate}&#44; ${local}<br />
						조회 결과 : ${listcount}개
					</span>&nbsp;&nbsp;
					<button type="button" class="btn btn-outline-secondary all-list">전체 목록</button>
				</c:otherwise>
			</c:choose>
		</div>
			
		<!-- 게시글 존재하는 경우 -->
		<c:if test="${listcount >= 1}">
			<table class="table table-hover table-striped" id="board-table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>		                
						<th scope="col">시작일</th>
						<th scope="col">종료일</th>		                
						<th scope="col">지역</th>
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
		        				<c:out value="${ b.start_date }" />
		        			</td>	        				
		        			<td>
		        				<c:out value="${ b.end_date }" />
		        			</td>	        				
		        			<td>
		        				<c:out value="${ b.address }" />
		        			</td>
		        			<td>
		        				<c:out value="${ b.heart_count }" />
		        			</td>
		        		</tr>
		        	</c:forEach>
		        </tbody>
			</table>
			
			<!-- 페이징 -->
			<div class="center-block">
				<ul class="pagination justify-content-center" id="board-pagination">
					<c:if test="${page <= 1}">
						<li class="page-item disabled">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>					
					<c:if test="${page > 1}">
						<li class="page-item">
							<a href="BoardList.bo?page=${page - 1}&orderby=${orderby}&category=${category}&loc=${loc}" class="page-link">이전&nbsp;</a>
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
								<a href="BoardList.bo?page=${a}&orderby=${orderby}&category=${category}&loc=${loc}" class="page-link">${a}</a>
							</li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page >= maxpage}">
						<li class="page-item disabled">
							<a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage}">
						<li class="page-item">
							<a href="BoardList.bo?page=${page + 1}&orderby=${orderby}&category=${category}&loc=${loc}" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>			
		</c:if>	
		
		<c:if test="${listcount == 0}">
			<h3>조회 결과가 없습니다.</h3>
		</c:if>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/board_list.js"></script>	
</body>
</html>