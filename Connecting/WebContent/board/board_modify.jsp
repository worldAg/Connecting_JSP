<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Board Modify</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/boardForm.css" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	
	<div class="container board-form">
		<form name="boardModify" action="boardModifyAction.bo" method="post" enctype="Multipart/form-data">
			<div class="form-group row">
				<!-- 이미지 업로드 -->
				<div class="col-md-6" id="showImage">
					<span id="resetBtn">
						<!-- 이미지 변경 시 이미지 초기화 x버튼 생성 -->
					</span>
					<label>
						<span id="imgUpdate">
							<c:set var="resourcesPath" value='${pageContext.request.contextPath}${"/resources/"}'/>
							<c:if test='${empty boardInfo.board_img}'>
								<c:set var='imgPath' value='${resourcesPath}img/default-img.png' />
							</c:if>
							<c:if test='${!empty boardInfo.board_img}'>
								<c:set var='imgPath' value='${resourcesPath}${"board_upload/"}${boardInfo.board_img}'/>
							</c:if>
							<img id="boardImg" src="${imgPath}" alt="이미지 업로드" />
						</span>
						<input type="file" id="editImg" name="board_img" accept="image/*" style="display:none">
					</label>
					<span id="originalImg" style="display:none">${boardInfo.board_img}</span>
				</div>
				<div class="col-md-6 board-info">
					<!-- 카테고리 및 지역 select -->
					<div class="sel">
						<span id="selectedCat" style="display:none">${boardInfo.category}</span>
						<select id="category" name="category">
							<option value="0">전시회</option>
							<option value="1">박람회</option>
							<option value="2">버스킹</option>
							<option value="3">연극&#47;공연</option>
						</select>
						<span id="selectedLoc" style="display:none">${boardInfo.loc}</span>
						<select id="loc" name="loc">
							<option value="0">서울</option>
							<option value="1">경기&#47;인천</option>
							<option value="2">대전&#47;충청&#47;강원</option>
							<option value="3">부산&#47;대구&#47;경상</option>
							<option value="4">광주&#47;전라&#47;제주</option>
						</select>
					</div>
					<!--  게시글 정보 입력  -->
					<table>
						<thead>
							<tr>
								<td colspan="2">
									<input class="input-text" id="title" type="text" maxlength="30" 
										placeholder="제목을 입력하세요..." name="title" value="${boardInfo.title}">
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>주최명</th>
								<td><input class="input-text" type="text" name="host" value="${boardInfo.host}"></td>
							</tr>
							<tr>
								<th>장소</th>
								<td><input class="input-text" type="text" name="address" value="${boardInfo.address}"></td>
							</tr>
							<tr>
								<th>날짜</th>
								<th>
									<input type="date" name="start_date" value="${boardInfo.start_date}"> &#126;
									<input type="date" name="end_date" value="${boardInfo.end_date}">
								</th>
							</tr>
							<tr>
								<th>시간</th>
								<th>
									<input type="time" name="start_time" value="${boardInfo.start_time}"> &#126;
									<input type="time" name="end_time" value="${boardInfo.end_time}">
								</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="form-label mt-4">본문</label>
				<textarea class="form-control" id="content" rows="15"
					placeholder="내용을 입력하세요." name="content">${boardInfo.content}</textarea>
			</div>
			
			<!-- board_id 값 전달을 위한 input -->
			<input type="text" name="board_id" value="${boardInfo.board_id}" style="display:none">
			
			<div class="mt-4 text-center">
				<button type="submit" class="btn btn-success btn-lg">수정 완료</button>
			</div>
		</form>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/board_write.js"></script>
</body>
</html>