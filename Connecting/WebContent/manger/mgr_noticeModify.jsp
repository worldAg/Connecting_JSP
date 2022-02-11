<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>관리자 - 공지사항 작성</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
	</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
    <style>
		 * {
	         font-family: 'Gaegu', cursive!important;
	      }
	     h2{
     	   text-align:center;
	     }
		.form-group {
			font-size:23px;
		}
		#ok {
	        text-align:center;
	        margin-top: 20px;
	    }
	</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<h2 style="margin-top: 50px;"> 공지사항 수정 </h2>
	<form name="noticeUpdate" action="noticeModifyAction.mgr" method="post">
  		<fieldset>
  			<div class="container justify-content-center">
				<div class="form-group">
					<input type="hidden" name="notice_id" value="${noticedata.notice_id}">
	      			<label for="title" class="form-label mt-4">제목</label>
	      			<input type="text" class="form-control" name="title" id="title" aria-describedby="notice_title" 
	      				placeholder="제목을 입력하세요." value="${noticedata.title}" size="100" style="font-size:23px">
				</div>
				<div class="form-group">
					<label for="content" class="form-label mt-4">본문</label>
	      			<textarea class="form-control" name="content" id="content" rows="10" cols="1000" 
	      				style="font-size:23px" placeholder="내용을 입력하세요.">${noticedata.content}</textarea>
	    		</div>
	    		<div id="ok">
	  				<button type="submit" class="btn btn-success btn-lg">작성 완료</button>
	  			</div>
  			</div>
  		</fieldset>
  	</form>	
</body>
<script>
		// submit 버튼을 클릭할 때
		$("form").submit(function() {
			if($.trim($("#title").val()) == "") {
				alert("제목을 입력하세요.");
				$("input").focus();
				return false;
			}
			
			if($.trim($("#content").val()) == "") {
				alert("내용을 입력하세요.");
				$("textarea").focus();
				return false;
			}
			if ($("#title").val().length > 25) {
				alert("제목은 25자 이내로 입력해주세요.");
				return false;
			}
			if ($("#content").val().length > 1000) {
				alert("본문은 1000자 이내로 입력해주세요.");
				return false;
			}
			
		});
</script>
</html>