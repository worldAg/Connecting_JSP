<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    
    <style>
        body {
            font-size: 30px;
        }

        .nav-item {
            display: inline-block;
            float: left !important;
            margin-left: 20px;
        }

        #headingThree {
            display: inline-block;
            float: right !important;
        }

        #collapseThree {
            position: absolute;
            z-index: 99;
            background-color: white;
        }

        .card {
            display: inline-block;
            margin-left: 40px;
        }

        #boards {
            margin-top: 200px;
            text-align: center;
        }

        .accordion-body {
            height: 500px;
            width: 100vw;
        }

        .s-container {
            width: 500px;
            margin: 50px 50px;
        }

        #sarch {
            display: inline-block !important;
        }

        #logo {
            display: inline-block;
        }

        .form-group {
            display: inline-block;
        }

        h1 {
            font-size: 50px;
        }

        .cont {
            display: inline-block;
            float: right !important;
        }

        .accordion-body {
            height: 500px;
            width: 100vw;
            display: flex;
            justify-content: center;
        }

        #radio_1 {
            margin-right: 200px;
        }

        #smart_btn {
            float: right;
            margin-right: 200px;
            margin-bottom: 50px;
            padding: 0px;
            width: 80px;
            font-size: 20px;
        }
    </style>
</head>

<body>


	<c:if test="${!empty id}">
	 	 <div class="d-flex flex-row-reverse bd-highlight">
	 	    <c:if test='${id=="admin"}'>
	 	       <div class="p-2 bd-highlight">
	 		   <a class="nav-link" href="mgrMain.mgr">관리자 페이지</a>
	 		  </div>
	 	 </c:if>
	 
	 		  <div class="p-2 bd-highlight">
	 		   <a class="nav-link" href="logout.net">로그아웃</a>
	 		  </div>
	 		  
	 		  <c:if test='${ id !="admin" && empty email}'>
	 		  <div class="p-2 bd-highlight">
	 		   <a class="nav-link" href="memberInfo.my">마이페이지</a>
	 		   </div>
	 		   </c:if>
	 		  <div class="p-2 bd-highlight">
	 		    <a class="nav-link">${id } 님 </a> 
	 		  </div>
	 	</div>
	</c:if>
	<c:if test="${empty id}">
    <div class="d-flex flex-row-reverse bd-highlight">

        <div class="p-2 bd-highlight"><a href="register.net">register</a></div>
        <div class="p-2 bd-highlight"><a href="login.net">login</a></div>

    </div>
    </c:if>
    <div class="container" style="margin-bottom: 44px;">
        <div class="row align-items-center">
            <div class="col-lg-5 col-md-12">
                <a href="main.jsp">
                    <h1 id="logo" style="font-size: 5rem">Connecting</h1>
                </a>
            </div>
            <div class="col-lg-7 col-md-12">
                <div>
                    <form action="BoardSearchBarListAction.bo" id="searchform">
                        <input type="text" style="width:84%" placeholder="Search" name="keyword"/>
                        <button type="submit" id="searchbtn">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"></a>
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
            </div>

            <h2 class="accordion-header" id="headingThree">
                <button class="bg-dark accordion-button" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree"
                    style="color: #fff; font-size: 1.5rem; border-radius: 30%">
                    스마트서치</button>
            </h2>
        </div>
    </nav>

    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
        data-bs-parent="#accordionExample" style="">
        <div class="accordion-body">
            <fieldset id="radio_1" class="form-group">
                <legend class="mt-4">카테고리</legend>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios1" value="all"> 전체
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios1" value="0"> 전시회
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios2" value="1"> 박람회
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios3" value="2"> 버스킹
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios3" value="3"> 연극/공연
                    </label>
                </div>
                <div style="visibility:hidden" class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="category"
                            id="optionsRadios3" value="-1"> 
                    </label>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <legend class="mt-4">지역별</legend>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios1" value="all"> 전체
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios2" value="0"> 서울
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios3" value="1"> 경기/인천
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios3" value="2"> 대전/충청/강원
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios3" value="3"> 부산/대구/경상
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label"> <input type="radio" class="form-check-input" name="loc"
                            id="optionsRadios3" value="4"> 광주/전라/제주
                    </label>
                </div>
            </fieldset>

        </div>
        <button type="button" class="btn btn-primary" id="smart_btn">검색하기</button>
    </div>
	
	<script>
		$(document).ready(function () {
			
			$("#smart_btn").click(function () {
				var category = $("input[name='category']:checked").val();
				var loc = $("input[name='loc']:checked").val();				
				console.log(category);
				console.log(loc);				
				location.href = "BoardSmartSearchListAction.bo?category=" + category + "&loc=" + loc;
			});
			
			$("#searchform").submit(function () {
				var keyword = $("#searchbar").val();
				if (keyword == "") {
					alert("검색어를 입력해 주세요.");
					$("#searchbar").focus();
					return false;
				} else {
					
				}
			});
		})
	</script>
</body>

</html>