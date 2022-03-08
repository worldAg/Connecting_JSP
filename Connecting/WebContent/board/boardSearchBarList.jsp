<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>boardSearchBarList.jsp</title>
	
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
			<div class="row" style="margin-top: 50px;">
				<div class="col-md-3">
					<div class="alert alert-info" style="padding: 0px; font-size: 1.5rem; text-align: center;">						
						<span id="num-list" style="font-weight: normal;">검색어 '${keyword }'<br />조회 결과: ${listcount }개</span>
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
					<c:if test="${page <= 1 }">
						<li class="page-item">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>					
					<c:if test="${page > 1 }">
						<li class="page-item">
							<a href="BoardSearchBarListAction.bo?page=${page - 1 }&keyword=${keyword }" class="page-link">이전&nbsp;</a>
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
								<a href="BoardSearchBarListAction.bo?page=${a }&keyword=${keyword }" class="page-link">${a }</a>
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
							<a href="BoardSearchBarListAction.bo?page=${page + 1 }&keyword=${keyword }" class="page-link">&nbsp;다음</a>
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