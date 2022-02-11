<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>관리자 메인 페이지</title>
</head>
<body>
	<h2> 관리자 메인 </h2>
	<div class="d-grid gap-2">
		<button type="button" class="btn btn-outline-secondary btn-lg" style="height:80px; font-size:25px"
			onclick = "location.href = 'memberList.mgr'">회원 관리</button>
		<button type="button" class="btn btn-outline-secondary btn-lg" style="height:80px; font-size:25px"
			onclick = "location.href = 'noticeList.mgr'">공지사항 관리</button>
		<button type="button" class="btn btn-outline-secondary btn-lg" style="height:80px; font-size:25px"
			onclick = "location.href = 'noticeWrite.mgr'">공지사항 작성</button>
	</div>
</body>
</html>