<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Header</title>
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin /> 
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css" />
</head>
<body>
	<header>
		<!-- 로그인 nav -->
		<nav>
			<!-- 로그인하지 않은 사용자일 경우 -->
			<c:if test="${empty id}">
			    <div class="d-flex flex-row-reverse bd-highlight">
			        <div class="p-2 bd-highlight"><a href="register.net">회원가입 &nbsp;</a></div>
			        <div class="p-2 bd-highlight"><a href="login.net">로그인 &#124;</a></div>
			    </div>
		    </c:if>
		    <!-- 로그인한 사용자일 경우 -->
		    <c:if test="${!empty id}">
				<div class="d-flex flex-row-reverse bd-highlight">
					<div class="p-2 bd-highlight">
			 			<a class="nav-link">고객센터 &nbsp;</a> 
			 		</div>
			 		<c:if test='${empty email}'>
			 			<div class="p-2 bd-highlight">
			 				<a class="nav-link" href="mypage.my">마이페이지 &#124;</a>
			 			</div>
			 		</c:if>
					<div class="p-2 bd-highlight">
			 			<a class="nav-link" href="logout.net">로그아웃 &#124;</a>
			 		</div>
			 		<div class="p-2 bd-highlight">
			 			${id} 님 &#124;
			 		</div>
			 	</div>
			</c:if>
	    </nav>
	
	    <!-- 로고 및 검색바 -->
	    <div id="logoAndSerch">
	    	<form action="boardSearchBarListAction.bo" id="searchForm">
	    		<img id="logo" src="<%=request.getContextPath()%>/resources/img/connecting/logo.png" alt="Connecting" height="100">
	        	<input type="text" placeholder=" 검색어를 입력해보세요!" name="keyword" id="searchBar">
	        	<button type="submit">
	        		<img src="<%=request.getContextPath()%>/resources/img/search1.png">
	        	</button>
	      	</form>	
		</div>
	
		<!-- 카테고리 nav -->
	    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	        <div class="container-fluid">
	            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02"
	                aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	
	            <div class="collapse navbar-collapse" id="navbarColor02">
	                <ul class="navbar-nav me-auto">
	                    <li class="nav-item"><a class="nav-link" href="boardList.bo?category=0">전시회</a></li>
	                    <li class="nav-item"><a class="nav-link" href="boardList.bo?category=1">박람회</a></li>
	                    <li class="nav-item"><a class="nav-link" href="boardList.bo?category=2">버스킹</a></li>
	                    <li class="nav-item"><a class="nav-link" href="boardList.bo?category=3">연극&#38;공연</a></li>
	                </ul>
	                <h2 class="accordion-header nav-item" id="headingThree">
		                <button class="bg-dark accordion-button" type="button" data-bs-toggle="collapse"
		                    data-bs-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree"
		                    style="color: #fff; font-size: 23px; border-radius: 30%">
		                    조건 검색&nbsp;<img src="<%=request.getContextPath()%>/resources/img/search2.png">  
		                </button>
	            	</h2>
	            </div>
	        </div>
	    </nav>
	
		<!-- 스마트서치 -->
	    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
	        data-bs-parent="#accordionExample" style="">
	        <div class="accordion-body">
	            <fieldset id="radio1" class="form-group">
	                <legend class="mt-4">카테고리</legend>
	                <div class="form-check">
	                    <label class="form-check-label"> 
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios1" value=""> 전체
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios1" value="0"> 전시회
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label"> 
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios2" value="1"> 박람회
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios3" value="2"> 버스킹
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label"> 
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios3" value="3"> 연극&#47;공연
	                    </label>
	                </div>
	                <div style="visibility:hidden" class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios3" value="-1"> 
	                    </label>
	                </div>
	            </fieldset>
	            <fieldset class="form-group">
	                <legend class="mt-4">지역</legend>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios1" value=""> 전체
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios2" value="0"> 서울
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="1"> 경기&#47;인천
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="2"> 대전&#47;충청&#47;강원
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="3"> 부산&#47;대구&#47;경상
	                    </label>
	                </div>
	                <div class="form-check">
	                    <label class="form-check-label">
	                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="4"> 광주&#47;전라&#47;제주
	                    </label>
	                </div>
	            </fieldset>
	        </div>
			<div id="smartBtn-div">
				<button type="button" class="btn btn-info" id="smartBtn">검색하기</button>
			</div>
		</div>
	</header>
    
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
	</script>
	<script>
		$(document).ready(function () {
			
			$("#logo").click(function () {
				location.href="<%=request.getContextPath()%>/index.jsp";
			});
			
			$("#smartBtn").click(function () {
				const category = $("input[name='category']:checked").val();
				const loc = $("input[name='loc']:checked").val();
				if (category === undefined) category = "all";
				if (loc === undefined) loc = "all";
				console.log(category);
				console.log(loc);
				location.href = "boardList.bo?category=" + category + "&loc=" + loc;
			});
			
			$("#searchForm").submit(function () {
				const keyword = $("#searchBar").val();
				if (keyword == "") {
					alert("검색어를 입력해주세요.");
					$("#searchBar").focus();
					return false;
				}
			});
			
		})
	</script>
</body>

</html>