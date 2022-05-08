<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시판 수정</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
	</script>
	<script src="resources/js/jquery-3.6.0.js"></script>
	<script src="resources/js/bo_write.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
	<style>
		 * {
	         font-family: 'Gaegu', cursive!important;
	      }
	      
		.a {
			border:none;
		}
		
		.a:focus{
			outline:none;
		}
	
		.imgupload{
			display:inline-block;
		}
		
		.content{
			display:inline-block;
		}
		
		td{
			border-bottom:1px solid black;
		}
		
		tr{
			height:50px;
		}
		
		table{
			border-bottom:1px solid black;
		}

		.form_header{
			display:flex;
			justify-content:center;
		}
		
		.sel{
			margin:30px 30px;
			display:flex;
			justify-content:center;
		}

		.update_btn{
			margin-right: 50px;
			margin-top: 50px;
			float: right;
		}
	</style>
</head>
<body>
    <jsp:include page="../main/header.jsp" />
    
    <div class="container cont_form">
    	<form class="board_form" action="BoardModifyAction.bo" method="post" style="font-size:23px" enctype="Multipart/form-data">
		
    	<div class="sel">
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		<select name="category">
	   			<option value="">카테고리</option>
	   			<option value="0">전시회</option>
	   			<option value="1">박람회</option>
	   			<option value="2">버스킹</option>
	   			<option value="3">연극&#47;공연</option>
	   		</select>		
	  		&nbsp;&nbsp;&nbsp;
	  		<select name="loc">
	      		<option value="">지역</option>
	   			<option value="0">서울</option>
	   			<option value="1">경기&#47;인천</option>
	   			<option value="2">대전&#47;충청&#47;강원</option>
	   			<option value="3">부산&#47;대구&#47;경상</option>
	   			<option value="4">광주&#47;전라&#47;제주</option>
	   		</select>
		</div>

		<div class="form_header">
		<div class="imgupload">
			<label>
				<span id="showImage">
					
				
				<c:if test='${empty boardInfo.board_img }'>
					<c:set var='src' value='images/nonimg.png' />
				</c:if> 
				<c:if test='${!empty boardInfo.board_img }'>
					<c:set var='src' value='${"boardupload/"}${boardInfo.board_img}'/>
				</c:if>
				
				
				<img style="width:300px" src="${src}" alt="board_img">
				</span>
				<span id="filevalue" style ="display:none">${boardInfo.board_img}</span>
				<input type="file" style="display:none;"
	                   id="change_img" name="board_img"
	                   accept="image/*" />

			</label>
		</div>
		
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <div class="content">
       	<table>
	      <thead>
	         <tr>
	            <td colspan="2">
	               <input class="a" type="text" maxlength="50" style="width:300px" 
	                value = "${boardInfo.title}" placeholder="제목을 입력하세요..." name="title">
	            </td>
	         </tr>
	      </thead>
	      <tbody>
	         <tr>
	            <th>주최자명</th>
	            <td><input class="a" type="text" value = "${boardInfo.host_name}" name="host_name"></td>
	         </tr>

	         <tr>
	            <th>장소</th>
	            <td><input class="a" value = "${boardInfo.address}" type="text" name="address"></td>
	         </tr>
	         <tr>
	            <th>날짜</th>
	            <th><input type="date" name="start_date" value = "${boardInfo.start_date}">
	               ~
	               <input type="date" name="end_date" value = "${boardInfo.end_date}">
	            </th>
	         </tr>
	         <tr>
	            <th>시간</th>
	            <th><input type="time" name="start_time" value = "${boardInfo.start_time}">
	               ~
	               <input type="time" name="end_time" value = "${boardInfo.end_time}">
	            </th>
	         </tr>
	      </tbody>
        </table>
       </div> 
    	</div>
		       
                      
		<div class="form-group">
			<label for="content" class="form-label mt-4">본문</label>
			
      		<textarea class="form-control" name="content" id="content" rows="15" cols="600" 
      		style="font-size:23px" placeholder="내용을 입력하세요.">${boardInfo.content}</textarea>
    	</div>
    	<input type="text" name="board_id" value="${boardInfo.board_id}" style="display:none">
    	
    	<div class="update_btn">
    	  	<button type="submit" class="btn btn-primary btn-lg">수정</button>
  			
		  	<a href="BoardDelete.bo?board_id=${boardInfo.board_id}">
		  	<button type="button" class="btn btn-danger btn-lg" id="del">삭제</button></a>
    	</div>

		</form>
    </div>
 	

<script>

	
	//select
	$("select[name='category']").val('${boardInfo.category}').prop('selected',true);
	$("select[name='loc']").val('${boardInfo.loc}').prop('selected',true);
	
	$("#del").click(function() {
		var check = confirm("정말로 삭제하시겠습니까?")
		
		if(!check){
			alert("게시글 삭제를 취소하셨습니다.");
			return false;
		}
	})

</script>
</body>
</html>