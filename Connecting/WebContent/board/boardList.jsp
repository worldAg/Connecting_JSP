<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>boardList.jsp</title>	
	
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
		
		div.container > div:nth-child(1) {
			margin-right: 20px;
		}
		
		a {
			text-decoration-line: none!important;
		}
		
		.hide-container {
			display: none!important;
		}
	</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container">
			<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
				<input type="radio" class="btn-check" name="select_what" id="board" value="board" autocomplete="off" checked />
	  			<label class="btn btn-outline-primary" for="board" checked>게시글</label>
	  			
	  			<input type="radio" class="btn-check" name="select_what" id="notice" value="notice" autocomplete="off" />
	  			<label class="btn btn-outline-primary" for="notice">공지사항</label>
			</div>
			
			<c:if test="${!empty sessionScope.id }">
				<button type="button" class="btn btn-info" id="writebtn">글쓰기</button>
			</c:if>			 
	</div> <%--div.container ends --%>	
	
	<div class="container" id="board-container">	
		<c:if test="${listcount >= 1 }">
			
			<div class="rows" style="text-align: right">
				<select class="form-control" id="orderby" style="width:auto; margin-bottom: 2em; display: inline-block;">
					<option value="0">게시글 작성 순 &#8595;</option>
					<option value="1">관심 많은 순 &#8595;</option>
					<option value="2">시작 일자 임박 순 &#8595;</option>
					<option value="3">종료 일자 임박 순 &#8595;</option>
				</select>
			</div>			
			
			<div class="row">
				<div class="col-md-3">
					<div class="alert alert-info" style="padding: 0px; font-size: 1.5rem; text-align: center;">
						<strong>전체 게시글: </strong><span id="num-list" style="font-weight: normal;">${listcount }개</span>
					</div>
				</div>
				<div class="offset-md-9"></div>
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
	        					<c:if test="${b.category == 0 }">
									[전시회]
								</c:if>
								<c:if test="${b.category == 1 }">
									[박람회]
								</c:if>
								<c:if test="${b.category == 2 }">
									[버스킹]
								</c:if>
								<c:if test="${b.category == 3 }">
									[연극/공연]
								</c:if>	        				
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
	
	<script>
		$(document).ready(function () {	
			
			// 글쓰기 버튼 클릭 시 글쓰는 페이지로 이동
			$("#writebtn").click(function () {
				location.href = "boardWrite.bo";
			})
			
			
			
			$("#orderby").change(function () {
				goBoard(1);
			});			
			
			$("input[type='radio'][name='select_what']").change(function() {
			    if (this.value == "board") {
			        goBoard(1, 0);
			        $("#orderby").val("0").prop("selected", true);
			        $("#writebtn").removeClass("hide-container");
			        $("#board-container").removeClass("hide-container");			        
			        $("#notice-container").addClass("hide-container");			       
			    } else if (this.value == "notice") {
			    	showNotice();
			    	$("#writebtn").addClass("hide-container");
			    	$("#board-container").addClass("hide-container");	
			    	$("#notice-container").removeClass("hide-container");		
			    }
			}); // 게시글 혹은 공지사항 라디오 버튼을 눌렀을 경우 처리.
		}); // document.ready ends
		
		function showNotice() {
			$.ajax({
				url: "NoticeList.bo",
				dataType: "json",
				cache: "false",
				
				success: function (data) {
					if (data.listcount > 0) {
						console.log("notice list success!");
						$("#notice-container table tbody").empty();
						var num = data.listcount;
						var output = "";
						$(data.noticelist).each(function (index, item) {
							output += "<tr>";
							output += "		<td>" + (num--) + "</td>";
							output += "		<td><a href='noticeDetail.mgr?notice_id=" + item.notice_id + "'>" + textLengthOverCut(item.title) + "</a></td>";
							output += "		<td>" + (item.write_date) + "</td>";
							output += "</tr>";
						}); // each ends
						$("#notice-container table tbody").append(output);
					} // if ends
				} // success ends
			});
		}
		
		function goBoard(page, orderby) {
			if (orderby === undefined) {
				orderby = $("#orderby option:selected").val();
			}
			var sendData = "page=" + page + "&state=ajax&orderby=" + orderby;
			ajaxBoard(sendData);
		} // function goBoard ends
		
		function ajaxBoard(sendData) {
			console.log("sendData: " + sendData);
			$.ajax({
				type: "post",
				data: sendData,
				url: "BoardList.bo",
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
							// 게시글의 카테고리를 구분해주는 부분
							if (item.category == 0) {
								category = "[전시회] ";
							} else if (item.category == 1) {
								category = "[박람회] ";
							} else if (item.category == 2) {
								category = "[버스킹] ";
							} else if (item.category == 3) {
								category = "[연극/공연] ";
							}
							
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