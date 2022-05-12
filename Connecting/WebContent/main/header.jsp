<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css" />
    <title>Header</title>
</head>

<body>
	<!-- 로그인 nav -->
	<nav>
		<!-- 로그인하지 않은 사용자일 경우 -->
		<c:if test="${empty id}">
		    <div class="d-flex flex-row-reverse bd-highlight">
		        <div class="p-2 bd-highlight"><a href="register.net">회원가입</a></div>
		        <div class="p-2 bd-highlight"><a href="login.net">로그인 |</a></div>
		    </div>
	    </c:if>
	    <!-- 로그인한 사용자일 경우 -->
	    <c:if test="${!empty id}">
			<div class="d-flex flex-row-reverse bd-highlight">
				<div class="p-2 bd-highlight">
		 			<a class="nav-link">고객센터</a> 
		 		</div>
		 		<c:if test='${ id !="admin" && empty email}'>
		 			<div class="p-2 bd-highlight">
		 				<a class="nav-link" href="memberInfo.my">마이페이지 |</a>
		 			</div>
		 		</c:if>
		 	    <c:if test='${id=="admin"}'>
		 	    	<div class="p-2 bd-highlight">
		 				<a class="nav-link" href="mgrMain.mgr">관리자페이지 |</a>
		 			</div>
				</c:if>
				<div class="p-2 bd-highlight">
		 			<a class="nav-link" href="logout.net">로그아웃 |</a>
		 		</div>
		 		<div class="p-2 bd-highlight">
		 			${id} 님 |
		 		</div>
		 	</div>
		</c:if>
    </nav>

    <!-- 로고 및 검색바 -->
    <div id="logoAndSerch">
    	<form action="BoardSearchBarListAction.bo" id="searchForm">
    	 	<a href="<%=request.getContextPath()%>/index.jsp">
		      	<img id="logo" src="<%=request.getContextPath()%>/resources/img/logo.png" alt="Connecting" height="100">
		    </a>
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
                    <li class="nav-item"><a class="nav-link" href="BoardCategoryList.bo?category=0">전시회</a></li>
                    <li class="nav-item"><a class="nav-link" href="BoardCategoryList.bo?category=1">박람회</a></li>
                    <li class="nav-item"><a class="nav-link" href="BoardCategoryList.bo?category=2">버스킹</a></li>
                    <li class="nav-item"><a class="nav-link" href="BoardCategoryList.bo?category=3">연극&공연</a></li>
                </ul>
                <h2 class="accordion-header" id="headingThree">
	                <button class="bg-dark accordion-button" type="button" data-bs-toggle="collapse"
	                    data-bs-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree"
	                    style="color: #fff; font-size: 22px; border-radius: 30%">
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
                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios1" value="all"> 전체
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
                    	<input type="radio" class="form-check-input" name="category" id="optionsRadios3" value="3"> 연극/공연
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
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios1" value="all"> 전체
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios2" value="0"> 서울
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="1"> 경기/인천
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="2"> 대전/충청/강원
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="3"> 부산/대구/경상
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                    	<input type="radio" class="form-check-input" name="loc" id="optionsRadios3" value="4"> 광주/전라/제주
                    </label>
                </div>
            </fieldset>
        </div>
        <div id="smartBtn-div">
          	<button type="button" class="btn btn-info" id="smartBtn">검색하기</button>
        </div>
    </div>
	<script src="/Connecting/resources/js/jquery-3.6.0.js"></script>
	<script>
		$(document).ready(function () {
			
			$("#smartBtn").click(function () {
				var category = $("input[name='category']:checked").val();
				var loc = $("input[name='loc']:checked").val();				
				console.log(category);
				console.log(loc);				
				location.href = "BoardSmartSearchListAction.bo?category=" + category + "&loc=" + loc;
			});
			
			$("#searchForm").submit(function () {
				var keyword = $("#searchBar").val();
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