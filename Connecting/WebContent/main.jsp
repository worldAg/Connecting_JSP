<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>main page</title>
<link rel="stylesheet" href="css/bootstrap.css" />

<script src="<%=request.getContextPath()%>/jQuery/jquery-3.6.0.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap"
	rel="stylesheet">

<style>
* {
	font-family: 'Gaegu', cursive !important;
}
</style>
</head>

<body>
	<jsp:include page="header.jsp" />

	<%--
	<button type="button" class="btn btn-primary btn-lg">새로운 소식을 확인하세요></button>
	
    <div id="boards">
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="./images/icecream.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the
                    card title and make up the bulk of the card's content.</p>
            </div>
        </div>
    </div>
     --%>

	<div class="container" style="margin-top: 20px;">
		<button type="button" class="btn btn-primary btn-lg">새로운 소식을
			확인하세요 ></button>
	</div>

	<div id="boards" class="container" style="margin-top: 20px;">

		<div class="card mb-3" style="width: 400px!important">
			<h3 class="card-header">Card header</h3>
			<img src="./images/icecream.png" width="100%" height="200" />			
			<div class="card-body">
				<h5 class="card-title">Special title treatment</h5>
				<h6 class="card-subtitle text-muted">Support card subtitle</h6>
			</div>
		</div>
		
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">		
	</script>
	
	<script>
		$(document).ready(function () {
			showRecentBoard();
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
						output += '<img src="./boardupload/' + item.board_img + '" width="200px" height="300px" />';
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

</body>

</html>
