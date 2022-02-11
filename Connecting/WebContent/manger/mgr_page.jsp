<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>관리자 페이지</title>
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
		.sidenav {
		  height: 100%;
		  width: 260px;
		  z-index: 1;
		  top: 0;
		  left: 0;
		  position: fixed;
		  background-color: #323a42;
		  overflow-x: hidden;
		  padding-top: 20px;
		  border-radius: 2em;
		  margin-top: 320px;
		}
	
		#mgrmain {
			font-size: 27px
		}
		.sidenav a {
		  padding: 10px 0px 6px 32px;
		  text-decoration: none;
		  font-size: 24px;
		  color: #818181;
		  display: block;
		}
		
		.sidenav a:hover {
		  color: #f1f1f1;
		}
		
		@media screen and (min-width: 707px) and (max-width: 991px) {
		  .sidenav { width: 220px; margin-top: 400px;}
		  #mgrmain {font-size: 25px}
		  .sidenav a {font-size: 23px;}
		}
		
		@media screen and (min-width: 575px) and (max-width: 706px) {
		  .sidenav { width: 150px; margin-top: 400px;}
		  #mgrmain {font-size: 17px}
		  .sidenav a {font-size: 16px;}
		}
		
		@media screen and (max-width: 574px) {
			.sidenav { display:none; }
		}
	</style>
</head>
<body>

	<%
		String pagefile = (String)request.getAttribute("pagefile");
	%>
	<header>
		<jsp:include page="../header.jsp" /><br><br>
	</header>
	<div class="container" style="margin-top:10px">
		<div class="row">
			<div class="col-sm-3">
				<aside>
					<jsp:include page="mgr_left.jsp" />
				</aside>
			</div>
			<div class="col-sm-8" style="margin-bottom: 100px;">
				<section>
					<jsp:include page='<%=pagefile + ".jsp"%>' />
				</section>
			</div>
		</div>
	</div>
	
	<script>
		var pagefile='<%=pagefile%>';
		var filelist = ["mgr_main", "mgr_member", "mgr_notice", "mgr_write" ];

		for (var index = 0; index < filelist.length; index++) {
			if (pagefile == filelist[index]) {
				$('.nav-pills > .nav-item > .nav-link').eq(index).addClass('active');
			} else {
				$('.nav-pills > .nav-item > .nav-link').eq(index).removeClass('active');
			}
		}
	</script>
</body>
</html>