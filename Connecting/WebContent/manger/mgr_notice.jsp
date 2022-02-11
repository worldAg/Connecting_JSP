<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>관리자 - 공지사항 관리 페이지</title>
<script src="js/jquery-3.6.0.js"></script>
</head>
<style>
	.noticeDetail, .delete {
		font-size:19px;
	}

</style>
<body>
<h2> 공지사항 관리 </h2>
		<table class="table table-hover" style="font-size:23px">
			<thead>
			    <tr>
			      <th scope="col">제목</th>
			      <th scope="col">날짜</th>
			      <th scope="col">상세 보기</th>
			      <th scope="col">삭제</th>
			    </tr>
		 	</thead>
		  	<tbody>
		  		<c:forEach var="n" items="${noticeList}">
				    <tr>
			  		  <input type="hidden" class="noticeId" value="${n.notice_id}">
				      <td>${n.title}</td>
				      <td>${n.write_date}</td>
				      <td>
				      	<button type="button" class="btn btn-info noticeDetail"
				      		onclick="location.href='noticeDetail.mgr?notice_id=${n.notice_id}'">상세페이지</button>
				      </td>
				      <td>
				      	<button type="button" class="btn btn-danger delete">삭제</button>
				      </td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	<script>
			// 삭제 버튼 클릭할 시
			$(".delete").click(function(event){
				var answer = confirm("정말 해당 공지사항을 삭제하겠습니까?");
					console.log(answer);
				if (answer) {
					var notice_id = $(this).parent().parent().find('.noticeId').val();
					console.log(notice_id);
					location.href='noticeDelete.mgr?notice_id='+notice_id;
				}
			});
	</script>
</body>
</html>