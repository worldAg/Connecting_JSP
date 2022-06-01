<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Mypage</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/connecting/favicon.ico" />
</head>
<body>
	<jsp:include page="../main/header.jsp" />



			<div class="p-2 bd-highlight">
				<a href="memberModify.net" style="margin-right: 50px">회원정보 변경 </a>
			</div>
			<div class="p-2 bd-highlight" id="reg">
				<a href="logout.net">logout</a>
			</div>
			<div class="p-2 bd-highlight" id="reg">
				<p>${memberInfo.id}님</p>
			</div>
		</div>

		<div class="item my_info">
			<table class="table caption-top" id="info_t">
				<thead class="table-dark">
					<tr>
						<th colspan="2" scope="row">회원정보</th>
					</tr>
				</thead>
				<tr class="table-light">
					<th>name:</th>
					<td>${memberInfo.name}</td>
				</tr>
				<tr class="table-light">
					<th>id:</th>
					<td>${memberInfo.id}</td>
				</tr>
				<tr class="table-light">
					<th>email:</th>
					<td>${memberInfo.email}</td>
				</tr>
				<tr class="table-light">
					<th>register date:</th>
					<td>${memberInfo.reg_date}</td>
				</tr>
			</table>
		</div>
		<div class="item main">
			<div class="col-lg-4">
				<div class="ch_img">


					
					<form id="img_form" action="updateImg.my" method="post" enctype="multipart/form-data">
						<input type="text" style="display: none" name="id" value="${sessionScope.id }">
						<label for="profile"> 
						<span id="showImage">
							
							<c:if test='${empty memberInfo.profile_img }'>
								<c:set var='src' value='images/profile.png' />
							</c:if>  
							<c:if test='${!empty memberInfo.profile_img }'>
								<c:set var='src' value='${"memberupload/"}${memberInfo.profile_img}'/>
							</c:if>
							<img id="profileImg" src="${src}" alt="profile"> 
							<i class="fas fa-cog fa-lg" aria-hidden="true"></i>
			
						</span>
							<input type="file" style="display: none" id="profile"
							name="profile" accept="image/*">
						</label>
					</form>
				</div>
				<div id="sub_info">
					<h2>${memberInfo.name}</h2>
					<p>
						<a class="btn btn-secondary" href="myHeartList.my">관심글 <i
							class="fas fa-heart fa-lg" aria-hidden="true"></i></a>
					</p>
				</div>
				
			</div>
		</div>


		<div class="item my_board" >
			<table class="table t_head">
				<thead class="table-dark">
					<tr>
						<th>내가 작성한 게시글</th>
					</tr>
				</thead>
			</table>
			<div style="height: 450px; overflow: scroll">
			<table class="table table-hover">
				
				
				<c:forEach var="b" items="${myboard}">
					<tr class="table-info">
						<td>
							<a href="BoardDetailAction.bo?num=${b.board_id }" class="list-group-item">
									<p>${b.title}</p> <small id="emailHelp"
									class="form-text text-muted"> ${b.start_date} ~
										${b.end_date}</small>
							</a>
						</td>
					</tr>

				</c:forEach>				
			</table>
			</div>
		</div>
	</div>

	<script>

		
		$('input[type=file]')
				.change(
						function(event) {
							var inputfile = $(this).val().split('\\');
							var filename = inputfile[inputfile.length - 1];
							var pattern = /(gif|jpg|jpeg|png)$/i;
							if (pattern.test(filename)) {

								var reader = new FileReader();
								reader.readAsDataURL(event.target.files[0]);

								reader.onload = function(event) {
									$("#sub_info").addClass("hidden");
									var button_ = '<button id="img_submit" class="btn btn-outline-info">확인</button>'
									$('#showImage')
											.html(
													'<img src="'
															+ event.target.result
															+ '" style ="width:140px; height:140px;'
															+ ' border-radius: 50%;">'
															+ '<i class="fas fa-cog fa-lg"></i>'
															+ button_);
								};

							} else {
								alert('확장자는 gif, jpg, jpeg, png가 가능합니다.');
								$(this).val("");
							}
						})

		$('#img_form').submit(function(event) {
			$("#sub_info").removeClass("hidden");

		})
	</script>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>
	
</body>
</html>