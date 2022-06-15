<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="bo.board.db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Board Detail</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/boardView.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="container">
		<div class="d-flex justify-content-end btns">
			<c:if test="${sessionScope.id == boardData.user_id}">
				<button type="button" class="btn btn-info" id="edit">수정</button>
			</c:if>
			<c:if test="${sessionScope.id == 'admin' || sessionScope.id == boardData.user_id}">
				<button type="button" class="btn btn-danger" id="delete">삭제</button>
			</c:if>
		</div>
	
		<div class="row">
			<!-- 게시글 이미지 -->
			<div class="col-md-5" id="showMainImg">
				<img id="boardImg" src="<%=request.getContextPath()%>/resources/board_upload/${boardData.board_img}" />
			</div>
			
			<!-- 게시글 정보 -->
			<div class="col-md-7">		
				<div class="d-flex justify-content-end">
					<%-- 로그인한 사용자의 경우 관심글 등록 가능 --%>
					<c:if test="${!empty sessionScope.id}">
						<div id="heart">
							<span>관심글</span>
							<img class="heart-img" src="<%=request.getContextPath()%>/resources/img/beforeheart.png" />
						</div>
					</c:if>
				</div>		
				<h4 id="boardTitle">
					<c:if test="${boardData.category == 0}">&#91;전시회&#93;</c:if>
					<c:if test="${boardData.category == 1}">&#91;박람회&#93;</c:if>
					<c:if test="${boardData.category == 2}">&#91;버스킹&#93;</c:if>
					<c:if test="${boardData.category == 3}">&#91;연극	&#47;공연&#93;</c:if>
					<c:out value="${ boardData.title }" />
				</h4>
				
				<hr />
				
				<!-- 게시글 정보  -->
				<div class="d-flex justify-content-center align-items-center">
					<div class="member-info">
						<c:set var="resourcesPath" value='${pageContext.request.contextPath}${"/resources/"}'/>
						<c:if test='${empty memberInfo.profile_img}'>
							<c:set var='imgPath' value='${resourcesPath}img/profile.png' />
						</c:if>  
						<c:if test='${!empty memberInfo.profile_img}'>
							<c:set var='imgPath' value='${resourcesPath}${"profile_upload/"}${memberInfo.profile_img}'/>
						</c:if>
	    				<div id="showProfile">
	    					<img id="profileImg" src="${imgPath}" alt="profile">
						</div>
						<div id="writer">${memberData.id}</div>
					</div>
					<div class="board-info">
					    <h5>주최: ${ boardData.host }</h5>
						<h5>장소: ${ boardData.address }</h5>
						<h5>날짜: ${ boardData.start_date } ~ ${ boardData.end_date }</h5>
						<h5>시간: ${ boardData.start_time } ~ ${ boardData.end_time }</h5>
					</div>
				</div>
			</div>
		</div>
		<div class="mb-3" style="margin-top: 50px;">
			<textarea class="form-control" id="textContent" rows="3" style="" readonly>
				<c:out value="${ boardData.content }" />
			</textarea>
		</div>
	</div>
    
    <script>
	    var userId = '<%=(String) request.getSession().getAttribute("id") %>';
		var boardId = '<%=((Board) request.getAttribute("boardData")).getBoard_id() %>';
    	var checked = "";
    	
    	$(document).ready(function () {
    		$("#edit").click(function () {
    			location.href = 'boardModifyView.bo?num=' + boardId;
    		});
    		
    		$("#delete").click(function() {
    			var check = confirm("정말로 삭제하시겠습니까?")
    			if(!check){
    				alert("게시글 삭제를 취소하셨습니다.");
    			} else {
    				location.href="boardDelete.bo?num=" + boardId;
    			}
    		});
    		
    		selectImage();
    		
    		$("div#heart").click(function () {
    			console.log("이벤트 발생!");
    			changeState();
    		});
    		
    		var textContent = document.getElementById("text-content");
    		resize(textContent);
    	}); // ready() ends
    	
    	function resize(obj) {
    	    obj.style.height = '1px';
    	    obj.style.height = (12 + obj.scrollHeight) + 'px';
    	}
    	
    	function changeState() {
    		
    		if (checked == "true") {    			
    			console.log("관심 등록 상태");
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
				url: "heartForBoard.bo",
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
						$("#heart-text").html("관심 취소");
						$("#heart-img").attr("src", ".resources/img/afterheart.png");
						checked = "true";
					} else {
						$("#heart-text").html("관심 등록");
						$("#heart-img").attr("src", ".resources/img/beforeheart.png");
						checked = "false";
					}
				}
			}); // ajax ends
    	}
    </script>
</body>
</html>