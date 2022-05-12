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
			width: 350px;
		}
		
		#listCount {
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
		
		<!-- 게시글 목록 -->
		<c:if test="${listcount >= 1}">
			<div class="alert alert-info">
				<c:if test="${category == '0' }">
					<strong>전시회 : </strong>
				</c:if>
				<c:if test="${category == '1' }">
					<strong>박람회 : </strong>
				</c:if>	
				<c:if test="${category == '2' }">
					<strong>버스킹 : </strong>
				</c:if>	
				<c:if test="${category == '3' }">
					<strong>연극/공연 : </strong>
				</c:if>		
				<span id="listCount">${ listcount }개</span>
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
	        					<a href="BoardDetailAction.bo?num=${b.board_id }"><c:out value="${b.title}" /></a>
	        				</td>
	        				<td> <%--작성자 아이디 --%>
	        					<c:out value="${b.id}" />
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
							<a href="BoardCategoryList.bo?page=${page - 1 }&orderby=${orderby }&category=${category }" class="page-link">이전&nbsp;</a>
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
								<a href="BoardCategoryList.bo?page=${a }&orderby=${orderby }&category=${category }" class="page-link">${a }</a>
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
							<a href="BoardCategoryList.bo?page=${page + 1 }&orderby=${orderby }&category=${category }" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>			
		</c:if>	
		
		<c:if test="${listcount == 0 }">
			<h3>조회된 결과가 없습니다.</h3>
		</c:if>
	</div>
	
	<script>
		$(document).ready(function () {
			$("#all-list-btn").click(function () {
				location.href = "BoardList.bo";
			});
			
			$("#orderby").change(function () {
				var category = '<%=request.getAttribute("category") %>';
				console.log("typeof category: " + typeof category);
				goBoard(1, $("#orderby option:selected").val(), category);
			});
		});
		
		function goBoard(page, orderby, category) {
			if (orderby === undefined) {
				orderby = $("#orderby option:selected").val();
			}
			if (category === undefined) {
				category = '<%=request.getAttribute("category") %>';
			}
			var sendData = "page=" + page + "&state=ajax&orderby=" + orderby + "&category=" + category;
			ajaxBoard(sendData);
		} // function goBoard ends
		
		function ajaxBoard(sendData) {
			console.log("sendData: " + sendData);
			$.ajax({
				type: "post",
				data: sendData,
				url: "BoardCategoryList.bo",
				dataType: "json",
				cache: "false",
				
				success: function (data) {
					console.log("AJAX success!");
					if (data.listcount > 0) {
						$("#board-table tbody").remove();
						var num = data.listcount - (data.page - 1) * data.limit;
						
						var output = "<tbody>";
						var category = "";
						
						$(data.boardlist).each(function (index, item) {							
							output += "<tr>";
							output += "		<td>" + (num--) + "</td>";
							output += "		<td><a href='BoardDetailAction.bo?num=" + item.board_id + "'>" + category + textLengthOverCut(item.title) + "</a></td>";
							output += "		<td>" + item.id + "</td>";
							output += "		<td>" + item.write_date + "</td>";
							output += "		<td>" + item.start_date + "</td>";
							output += "		<td>" + item.end_date + "</td>";
							output += "		<td style='text-align:right;'>" + item.heart_num + "</td>";
							output += "</tr>";
						});
						
						output += "</tbody>";
						$("#board-table").append(output);						
						
						// 페이징 처리 부분
						$("ul.pagination").empty();
						output = "";
						var digit = "이전&nbsp;";
						var href = "";
						
						if (data.page > 1) {
							href = "href=javascript:goBoard(" + (data.page - 1) + ")";
						} 
						
						output += setPagingBoard(href, digit);
						
						for (var i = data.startpage; i <= data.endpage; i++) {
							digit = i;
							href = "";
							if (i != data.page) {
								href = "href=javascript:goBoard(" + i + ")";
							}
							output += setPagingBoard(href, digit);
						}
						
						digit = "&nbsp;다음&nbsp;";
						href = "";
						if (data.page < data.maxpage) {
							href = "href=javascript:goBoard(" + (data.page + 1) + ")";
						}
						output += setPagingBoard(href, digit);
						
						$("ul.pagination").append(output);
					} // if data.listcount > 0 ends
				} // success function ends
			}); // $.ajax ends
		} // function ajax ends
		
		function setPagingBoard(href, digit) {
			// 이전/다음을 클릭할 수 없으면 <a>에 class gray
			// 현재 페이지라면 <li>에 class active
			// 모든 <li>에 page-item
			// 모든 <a>에 page-link
			var active = "";
			var gray = "";
			
			if (href == "") {
				if (isNaN(digit)) {
					gray = " gray";
				} else {
					active = " active";
				}
			}
			
			var output = "<li class='page-item" + active + "'>";
			var anchor = "<a class='page-link" + gray + "'" + href + ">" + digit + "</a></li>";
			
			output += anchor;
			return output;
		}
		
		function textLengthOverCut(txt, len, lastTxt) {
	        if (len == "" || len == null) { // 기본값
	            len = 12;
	        }
	        if (lastTxt == "" || lastTxt == null) { // 기본값
	            lastTxt = "...";
	        }
	        if (txt.length > len) {
	            txt = txt.substr(0, len) + lastTxt;
	        }
	        return txt;
	    }
	</script>
</body>
</html>