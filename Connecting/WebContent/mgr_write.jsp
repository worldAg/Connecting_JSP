<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>관리자 - 공지사항 작성</title>
<style>
	.form-group {
		font-size:23px;
	}
</style>
</head>
<body>
	<h2> 공지사항 작성 </h2>
	<form name="noticeWrite" action="noticeAddAction.mgr" method="post">
  		<fieldset>
			<div class="form-group">
      			<label for="title" class="form-label mt-4">제목</label>
      			<input type="text" class="form-control" name="title" id="title" aria-describedby="notice_title" placeholder="제목을 입력하세요." size="100" style="font-size:23px">
			</div>
			<div class="form-group">
				<label for="content" class="form-label mt-4">본문</label>
      			<textarea class="form-control" name="content" id="content" rows="10" cols="1000" style="font-size:23px" placeholder="내용을 입력하세요."></textarea>
    		</div>
  			<button type="submit" class="btn btn-primary">작성 완료</button>
  		</fieldset>
  	</form>	
</body>
</html>