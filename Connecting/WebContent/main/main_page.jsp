<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Main Page</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap">
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/favicon.ico">
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>
	<style>
		* {
			font-family: 'Gaegu', cursive !important;
		}
		a {
			text-decoration-line : none !important;
			text-decoration : none !important;
		}
	</style>
</head>

<body>
	<jsp:include page="header.jsp" />

	<div class="container" style="margin-top: 20px;">
		<button type="button" class="btn btn-primary btn-lg" id="board-list">새로운 소식을 확인하세요 ></button>
	</div>

	<div id="boards" class="container" style="margin-top: 20px;">
	</div>
	
	
	<script>
		$(document).ready(function () {
			showRecentBoard();
			
			$("#board-list").click(function () {
				location.href="BoardList.bo";
			})
		});	
		
		function showRecentBoard() {
			
			$.ajax({
				url: "RecentBoard.bo",				
				dataType: "json",
				cache: "false",
				type: "post",
				success: function (data) {
					console.log("success");
					var output = "";
					$(data.boardlist).each(function (index, item) {
						$("#boards").empty();
						
						output += '<div class="card mb-3">';
						output += '<h3 class="card-header"><a href="BoardDetailAction.bo?num=' + item.board_id + '">' + item.title + '</a></h3>';
						output += '<a href="BoardDetailAction.bo?num=' + item.board_id + '"><img src="./boardupload/' + item.board_img + '" width="200px" height="300px" /></a>';
						output += '<div class="card-body">';
						output += '<h5 class="card-title">' + item.address + '</h5>';
						output += '<h6 class="card-subtitle text-muted">' + item.start_date + ' ~ ' + item.end_date + '</h6>';
						output += "</div>";
						output += "</div>";
					});
					$("#boards").append(output);			
				}
			});
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">		
	</script>
</body>

</html>
