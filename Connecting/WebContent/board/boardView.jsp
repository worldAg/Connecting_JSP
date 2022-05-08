<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bo.board.db.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	
	<script src="<%=request.getContextPath() %>/jQuery/jquery-3.6.0.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
	
	<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
	<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
	
	<style>
		* {
			font-family: 'Gaegu', cursive!important;
		}
		
		teatarea#text-content {	
			min-height: 1rem!important;
			overflow-y: hidden!important;
			resize: none!important;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container" style="margin-top: 100px; margin-bottom: 100px;">
		<div class="row">
			<div class="col-md-4">
				<img src="./boardupload/${boarddata.board_img }" style="width: 100%; height: 300px; border-radius: 10%" />
			</div>
			<div class="offset-md-1 col-md-7">
				<h2 style="font-weight: bold; overflow: hidden;">
					<c:if test="${boarddata.category == 0 }">
						[전시회]
					</c:if>
					<c:if test="${boarddata.category == 1 }">
						[박람회]
					</c:if>
					<c:if test="${boarddata.category == 2 }">
						[버스킹]
					</c:if>
					<c:if test="${boarddata.category == 3 }">
						[연극/공연]
					</c:if>
					<c:out value="${boarddata.title }" />
					
					<%--로그인해야 관심글 등록 가능 --%>
					<c:if test="${!empty sessionScope.id }">
						<div id="heart" style="float: right;">
							<span id="heart-text" style="font-size: 1.5rem">관심글</span>
							<img src="./images/beforeheart.png" id="heart-img" style="width: 50px; height: 50px;" />
						</div>
					</c:if>
					
					
				</h2>
				<hr />
				<div class="d-flex justify-content-center align-items-center">
					<c:if test='${empty member.profile_img }'> 
						<c:set var='src' value='images/profile.png' /> 
					</c:if> 
					<c:if test='${!empty member.profile_img }'> 
						<c:set var='src' value='${"memberupload/"}${member.profile_img}'/> 
					</c:if>
					<img id="profile_img" src="${src}" alt="profile" style="width: 150px; height: 150px; display: inline-block; border-radius: 50%;" />
					
					<div style="display: inline-block; padding-left: 10%">
					    <h3>주최자: ${boarddata.host_name }</h3>
						<h3>장소: ${boarddata.address }</h3>
						<h3>날짜: ${boarddata.start_date } ~ ${boarddata.end_date }</h3>
						<h3>시간: ${boarddata.start_time } ~ ${boarddata.end_time }</h3>
					</div>
				</div>
			</div>
		</div>
		
		<div class="mb-3" style="margin-top: 50px;">
			<label for="exampleFormControlTextarea1" class="form-label">본문</label>
			<textarea class="form-control" id="text-content" rows="3" style="font-size: 1.5rem; font-weight: normal" readonly><c:out value="${boarddata.content }" /></textarea>
		</div>
		
		<c:if test="${sessionScope.id ==  boarddata.id }">
			<div class="row">			
				<div class="offset-md-9 col-md-3 d-flex justify-content-end">
					<button type="button" class="btn btn-info" id="edit">게시글 수정/삭제하기</button>
				</div>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.id ==  'admin' }">
			<div class="row">			
				<div class="offset-md-9 col-md-3 d-flex justify-content-end">
					<button type="button" class="btn btn-danger" id="delete">게시글 삭제하기</button>
				</div>
			</div>
		</c:if>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    
    <script>
	    var userId = '<%=(String) request.getSession().getAttribute("id") %>';
		var boardId = '<%=((BoardBean) request.getAttribute("boarddata")).getBoard_id() %>';
    	var checked = "";
    	
    	$(document).ready(function () {
    		$("#edit").click(function () {
    			location.href = 'BoardModifyView.bo?board_id=' + boardId;
    		});
    		
    		$("#delete").click(function() {
    			var check = confirm("정말로 삭제하시겠습니까?")
    			
    			if(!check){
    				alert("게시글 삭제를 취소하셨습니다.");
    			} else {
    				location.href="BoardDelete.bo?board_id=" + boardId;
    			}
    		})
    		
    		selectImage();
    		
    		$("div#heart").click(function () {
    			console.log("이벤트 발생!");
    			changeState();
    		});
    		
    		var textContent = document.getElementById("text-content");
    		resize(textContent);
    	});
    	
    	function resize(obj) {
    	    obj.style.height = '1px';
    	    obj.style.height = (12 + obj.scrollHeight) + 'px';
    	}
    	
    	function changeState() {
    		
    		if (checked == "true") {    			
    			console.log("관심글로 저장 돼 있음...");
    			console.log("데이터베이스에서 관심글 리스트에서 지우는 작업 실행...");
    			
    			// 관심글로 저장 돼 있을 때 하트 버튼을 클릭 시 관심글에서 제거 돼야 함. 즉, span 태그 안 내용 및 이미지가 빈 하트로 변경 돼야 함.
    			$("#heart-text").html("관심글 등록");
				$("#heart-img").attr("src", "./images/beforeheart.png");
				
				// 데이터베이스에 가서 관심글 목록에서 지우기. 사용자 아이디 및 게시글의 아이디기 필요
				$.ajax({
					url: "AddOrRemoveHeart.bo",
					data: {
						"userId": userId,
						"boardId": boardId,
						"process": "remove"
					},
					dataType: "json",
					cache: "false",
					type: "post",
					async: false,
					success: function (data) {
						if (data.success == "true") {
							console.log("데이터베이스 관심글에서 삭제 성공...");
							checked = "false";
						} else if (data.success == "false") {
							console.log("데이터베이스 관심글에서 삭제 실패...");
						}
					} // success ends
				}); // ajax ends
    		} else if (checked == "false") {
    			console.log("관심글로 저장 돼 있지 않음...");
    			console.log("데이터베이스에서 관심글 리스트에서 추가하는 작업 실행...");
    			
    			// 관심글로 저장 돼 있지 않을 때 하트 버튼을 클릭 시 관심글 리스트에 추가돼야 함. 즉, span 태그 안 내용 및 이미지가 빨간 하트로 변경 돼야 함.
    			$("#heart-text").html("관심글 삭제");
				$("#heart-img").attr("src", "./images/afterheart.png");
				
				// 데이터베이스에 가서 관심글 목록 추가. 사용자 아이디 및 게시글의 아이디기 필요
				$.ajax({
					url: "AddOrRemoveHeart.bo",
					data: {
						"userId": userId,
						"boardId": boardId,
						"process": "add"
					},
					dataType: "json",
					cache: "false",
					type: "post",
					async: false,
					success: function (data) {
						if (data.success == "true") {
							console.log("데이터베이스 관심글에서 추가 성공...");
							checked = "true";
						} else if (data.success == "false") {
							console.log("데이터베이스 관심글에서 추가 실패...");
						}
					} // success ends
				}); // ajax ends
    		}
    	}
    	
    	function selectImage() {
    		$.ajax({
				url: "IsAddedToMemberTable.bo",
				data: {
					"userId": userId,
					"boardId": boardId
				},
				dataType: "json",
				cache: "false",
				type: "post",
				async: false,
				success: function (data) {
					if (data.isAdded == "true") {
						$("#heart-text").html("관심글 삭제");
						$("#heart-img").attr("src", "./images/afterheart.png");
						checked = "true";
					} else {
						$("#heart-text").html("관심글 등록");
						$("#heart-img").attr("src", "./images/beforeheart.png");
						checked = "false";
					}
				} // success ends
			}); // ajax ends
    	} // selectImage function ends
    </script>
</body>
</html>