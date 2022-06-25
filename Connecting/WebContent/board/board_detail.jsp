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
							<span id="heartText">관심 등록</span>
							<img id="heartImg" src="<%=request.getContextPath()%>/resources/img/beforeheart.png" />
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
						<c:if test='${empty memberData.profile_img}'>
							<c:set var='imgPath' value='${resourcesPath}img/profile.png' />
						</c:if>  
						<c:if test='${!empty memberData.profile_img}'>
							<c:set var='imgPath' value='${resourcesPath}${"profile_upload/"}${memberData.profile_img}'/>
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
		<div class="content">
			<%-- 
				XSS(Cross Site Scripting) 방지를 위해 출력 시에도 textarea, c:out 태그 사용.
			 	c:out 태그는 escapeXml 속성 기본값이 true로 HTML 태그를 해석하지 않음.
			 --%>
			<textarea class="form-control" id="textContent" readonly><c:out value="${ boardData.content }" /></textarea>
		</div>
		<div class="writer_date">
			${ boardData.write_date } 작성
		</div>
	</div>
    
    <script>
	    const userId = '<%=(String) request.getSession().getAttribute("id") %>';
		const boardId = '<%=((Board) request.getAttribute("boardData")).getBoard_id() %>';
    	let checked = "";
    	
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
    		
    		isHeart();
    		
    		$("#heartImg").click(function () {
    			changeState();
    		});

    	}); // ready() ends
    	
    	// 사용자의 관심글 등록 여부 확인
    	function isHeart() {
    		$.ajax({
				url: "heartForBoard.bo",
				type: "POST",
				data: {
					"userId": userId,
					"boardId": boardId
				},
				dataType: "json",
				cache: "false",
				async: false, 
				success: function (data) {
					if (data.isAdded == "true") {
						$("#heartText").html("관심 취소");
						$("#heartImg").attr("src", "./resources/img/afterheart.png");
						checked = "true";
					} else {
						$("#heartText").html("관심 등록");
						$("#heartImg").attr("src", "./resources/img/beforeheart.png");
						checked = "false";
					}
				}
			});
    	} // isHeart() ends
    	
    	// 하트 변경 상태에 따라 관심글 추가 및 삭제하도록 함
    	function changeHeart(process) {
    		$.ajax({
				url: "heartAddOrRemove.bo",
				type: "POST",
				data: {
					"userId": userId,
					"boardId": boardId,
					"process": process
				},
				dataType: "json",
				cache: "false",
				async: false,
				success: function (data) {
					if (process == "add") {
						checked = "true";
					} else {
						checked = "false";
					}
				},
				error: function(request, status, error) {
					console.log("code: " + request.status + "\n" 
						+ "받은 데이터: " + request.responseText + "\n" 
						+ "error: " + error + "\n" 
						+ "error status: " + status
					);
				}
			});
    	} // changeHeart() ends
    	
    	// 사용자의 관심글 등록 상태 변경
    	function changeState() {
    		let process = "";
    		// 관심 상태가 true이면 false로 변경
    		if (checked == "true") {
    			console.log("관심 취소 작업 실행");
    			$("#heartText").html("관심 등록");
				$("#heartImg").attr("src", "./resources/img/beforeheart.png");
    			process = "remove";
				changeHeart(process);
    		} 
    		// 관심 상태가 false이면 true로 변경
    		else if (checked == "false") {
    			console.log("관심 등록 작업 실행");
    			$("#heartText").html("관심 취소");
				$("#heartImg").attr("src", "./resources/img/afterheart.png");
    			process = "add";
				changeHeart(process);
    		}
    	}
    </script>
</body>
</html>