<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>boardSmartSearchList.jsp</title>
	
	<script src="<%=request.getContextPath() %>/jQuery/jquery-3.6.0.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
	
	<style>
		* {
			font-family: 'Gaegu', cursive!important;
		}
		
		a {
			text-decoration : none;
		}
	</style>
</head>

<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container" id="board-container">	
		<c:if test="${listcount >= 1 }">
			
			<%--
			<div class="rows" style="text-align: right; margin-top: 20px;">
				<select class="form-control" id="orderby" style="width:auto; margin-bottom: 2em; display: inline-block;">
					<option value="0">게시글 작성 순 &#8595;</option>
					<option value="1">관심 많은 순 &#8595;</option>
					<option value="2">시작 일자 임박 순 &#8595;</option>
					<option value="3">종료 일자 임박 순 &#8595;</option>
				</select>
			</div>			
			 --%>
			
			<div class="row" style="margin-top: 50px;">
				<div class="col-md-3">
					<div class="alert alert-info" style="padding: 0px; font-size: 1.5rem; text-align: center;">
						<c:if test="${category == 'all' }">
							<c:set var="cate" value="카테고리: 전체" />
						</c:if>
						<c:if test="${category == '0' }">
							<c:set var="cate" value="카테고리: 전시회" />
						</c:if>
						<c:if test="${category == '1' }">
							<c:set var="cate" value="카테고리: 박람회" />
						</c:if>	
						<c:if test="${category == '2' }">
							<c:set var="cate" value="카테고리: 버스킹" />
						</c:if>	
						<c:if test="${category == '3' }">
							<c:set var="cate" value="카테고리: 연극/공연" />
						</c:if>	
						
						<c:if test="${loc == 'all' }">
							<c:set var="loca" value="지역: 전체 " />
						</c:if>
						<c:if test="${loc == '0' }">
							<c:set var="loca" value="지역: 서울 " />
						</c:if>
						<c:if test="${loc == '1' }">
							<c:set var="loca" value="지역: 경기/인천 " />
						</c:if>	
						<c:if test="${loc == '2' }">
							<c:set var="loca" value="지역: 대전/충청/강원 " />
						</c:if>	
						<c:if test="${loc == '3' }">
							<c:set var="loca" value="지역: 부산/대구/경상 " />
						</c:if>
						<c:if test="${loc == '4' }">
							<c:set var="loca" value="지역: 광주/전라/제주 " />
						</c:if>	
						<span id="num-list" style="font-weight: normal;">${cate }<br />${loca }<br />조회 결과: ${listcount }개</span>
					</div>
				</div>
				<div class="offset-md-7 col-md-2" style="text-align: center;">
					<button type="button" class="btn btn-outline-info" id="all-list-btn" style="font-size: 0.7em; font-weight: bold;">전체 게시글 목록</button>
				</div>
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
		               	<th scope="col">관심수</th>
		            </tr>
	        	</thead>
	        	
	        	<tbody>
	        		<c:set var="num" value="${listcount - (page - 1) * limit }" />
	        		<c:forEach var="b" items="${boardlist }">
	        			<tr>
	        				<td> <%--글 번호 --%>
	        					<c:out value="${num }" />
								<c:set var="num" value="${num - 1 }" />
	        				</td>
	        				<td> <%--글 제목 --%>       				
	        					<a href="BoardDetailAction.bo?num=${b.board_id }"><c:out value="${b.title }" /></a>
	        				</td>
	        				<td> <%--작성자 아이디 --%>
	        					<c:out value="${b.id }" />
	        				</td>
	        				<td> <%--작성일자 --%>
	        					<c:out value="${b.write_date }" />
	        				</td>	        				
	        				<td> <%--시작일자 --%>
	        					<c:out value="${b.start_date }" />
	        				</td>	        				
	        				<td> <%--종료일자 --%>
	        					<c:out value="${b.end_date }" />
	        				</td>	        				
	        				<td style="text-align:right;"> <%--관심수 --%>
	        					<c:out value="${b.heart_num }" />
	        				</td>
	        			</tr>
	        		</c:forEach>
	        	</tbody>
			</table>
			
			<div class="center-block">
				<ul class="pagination justify-content-center" id="board-pagination">
					<c:if test="${page <=1 }">
						<li class="page-item">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>					
					<c:if test="${page > 1 }">
						<li class="page-item">
							<a href="BoardSmartSearchListAction.bo?page=${page - 1 }&category=${category }&loc=${loc }" class="page-link">이전&nbsp;</a>
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
								<a href="BoardSmartSearchListAction.bo?page=${a }&category=${category }&loc=${loc }" class="page-link">${a }</a>
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
							<a href="BoardSmartSearchListAction.bo?page=${page + 1 }&category=${category }&loc=${loc }" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>			
		</c:if>	
		
		<c:if test="${listcount == 0 }">
			<h3>조회된 결과가 없습니다.</h3>
		</c:if>
	</div>
</body>

</html>