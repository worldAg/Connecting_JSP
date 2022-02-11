<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>관리자 - 회원 관리 페이지</title>
<script src="js/jquery-3.6.0.js"></script>
<style>
	#memberList {
		text-align:center;
		font-size:23px;
	}
	.memberInfo, .delete {
		font-size:19px;
	}
</style>
</head>
<body>
	<h2> 회원 관리 </h2>
	<div class="container">
		<form class="d-flex" action="memberList.mgr" method="post">
		      <input class="form-control me-sm-2" type="text" name="search_word" placeholder="회원 ID를 입력하세요" style="font-size:21px">
		      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
		</form>
		<c:if test="${listcount > 0}"> <%-- 회원 있는 경우 --%>
			<div align="right" ><font size=3>회원 수 : ${listcount}</font></div>
			<table class="table table-hover" id="memberList">
				<colgroup>
					<col width="30%" />
					<col width="30%" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<thead>
				    <tr>
				      <th scope="col">아이디</th>
				      <th scope="col">이름</th>
				      <th scope="col">상세 보기</th>
				      <th scope="col">탈퇴</th>
				    </tr>
			 	</thead>
		  		<tbody>
					<c:forEach var="m" items="${totallist}">
						<tr>
							<td>${m.id}</td>
							<td>${m.name}</td>
							<td>
								<button type="button" class="btn btn-info memberInfo" 
									onclick="open_info('memberInfo.mgr?id=${m.id}')">상세 정보</button>
							</td>
							<td>
								<button type="button" class="btn btn-danger delete" id="memberDelete">회원 탈퇴</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<ul class="pagination pagination justify-content-center">
					<c:if test="${page <= 1}">
						<li class="page-item disabled">
							<a class="page-link gray">이전</a>
						</li>
					</c:if>
					<c:if test="${page > 1}">
						<li class="page-item active">
							<a class="page-link" href="memberList.mgr?page=${page-1}&search_field=${search_field}&search_word=${search_word}">이전</a>
						</li>
					</c:if>
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page}">
							<li class="page-item active">
								<a class="page-link">${a}</a>
							</li>
						</c:if>
						<c:if test="${a != page}">
							<c:url var="go" value="memberList.mgr">
								<c:param name="search_word" value="${search_word}" />
								<c:param name="page" value="${a}" />
							</c:url>
							<li class="page-item">
								<a class="page-link" href="${go}">${a}</a>
							</li>
						</c:if>
					</c:forEach>
					<c:if test="${page >= maxpage}">
						<li class="page-item disabled">
							<a class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage}">
						<c:url var="next" value="memberList.mgr">
							<c:param name="search_word" value="${search_word}" />
							<c:param name="page" value="${page + 1}" />
						</c:url>
						<li class="page-item">
							<a href="memberList.mgr?page=${page + 1}" class="page-link">다음</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
	<c:if test="${listcount == 0 && empty search_word}">
		<h3>회원이 없습니다.</h3>
	</c:if>
	<c:if test="${listcount == 0 && !empty search_word}">
		<h3>검색 결과가 없습니다.</h3>	
	</c:if>
	
	<script>
		// 회원 탈퇴 버튼 클릭 시
		$(".delete").click(function(event){
			var answer = confirm("정말 해당 회원 정보를 삭제하겠습니까?");
			console.log(answer);
			if (answer) {
				var id = $(this).parent().parent().find('td').first().text();
				console.log(id);
				location.href='memberDelete.mgr?id='+id;
			}
		});
		
		// 회원 상세 보기 클릭 시 보이는 팝업창
		function open_info(url) {
		    newPage=window.open(url, "member_info", "width=335, height=305, left=500, top=300");
		}
	</script>
</body>
</html>