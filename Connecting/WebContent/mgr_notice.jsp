<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>관리자 - 공지사항 관리 페이지</title>
</head>
<body>
<h2> 공지사항 관리 </h2>
		<table class="table table-hover" style="font-size:20px">
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
				      <td>${n.title}</td>
				      <td>${n.write_date}</td>
				      <td>
				      	<button type="button" class="btn btn-info"
				      		onclick="location.href='noticeDetail.mgr?notice_id=${n.notice_id}'">상세페이지</button>
				      </td>
				      <td>
				      	<button type="button" class="btn btn-danger"
				      		onclick="location.href='noticeDelete.mgr?notice_id=${n.notice_id}'">삭제</button>
				      </td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>