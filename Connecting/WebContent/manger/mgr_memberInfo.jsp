<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="preconnect" href="https://fonts.googleapis.com"> <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Gaegu&display=swap" rel="stylesheet">
<title>회원 정보 팝업창</title>
<style>
	* {
	     font-family: 'Gaegu', cursive!important;
	}
	table {
		text-align:center;
		font-size:20px;
	}
	div{
        text-align:center;
        font-size:21px;
    }
</style>
</head>
<body>

	<div class="card text-white bg-info mb-3" style="max-width: 20rem;">
  		<div class="card-header">회원 상세 정보</div>
 		<div class="card-body">
			<table class="table table-hover">
				<tr class="table-info">
					<th scope="row">아이디</th>
					<td>${memberinfo.id}</td>
			    </tr>
			    <tr class="table-info">
					<th scope="row">이름</th>
					<td>${memberinfo.name}</td>
			    </tr>
			    <tr class="table-info">
					<th scope="row">이메일</th>
					<td>${memberinfo.email}</td>
			    </tr>
			    <tr class="table-info">
					<th scope="row">가입일</th>
					<td>${memberinfo.reg_date}</td>
				</tr>
			</table>
   			<button type="button" class="btn btn-secondary" data-bs-dismiss="card" 
   			 	onclick="window.close()">Close</button>
    	</div>
	</div>
     
</body>
</html>