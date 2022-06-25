$(document).ready(function () {	
	myList();
	
	$("#heartList").click(function () {
		$("#myHeart").empty();
		myHeart();
	});
	
});

// 사용자가 작성한 게시글 목록
function myList() {
	$.ajax({
		url: "mylist.my",
		type: "POST",
		data: {},
		dataType: "json",
		cache: "false",
		success: function (data) {
				let output = '<table class="table table-hover"><thead>';
				output += '<tr class="table-success">';
				output += '<th scope="col" class="text-center">제목</th>';
				output += '<th scope="col" class="text-center">작성일</th>';
				output += '<th scope="col" class="text-center">관심수</th>';
				output += '</tr></thead><tbody>'
				$(data.mylist).each(function (index, item) {
					output += `<tr onClick="location.href='boardDetail.bo?num=${item.board_id}'">`;
					output += '<td>' + textLengthOverCut(item.title, 20) + '</td>';
					output += '<td class="text-center">' + textLengthOverCut(item.write_date, 10, " ") + '</td>';
					output += '<td class="text-center">' + item.heart_count + '</td></tr>';
				});
				output += '</tbody></table>';
				$("#myList").append(output);
			
		}, // success function ends
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" 
				+ "받은 데이터: " + request.responseText + "\n" 
				+ "error: " + error + "\n" 
				+ "error status: " + status
			);
		}
	}) // $.ajax ends
}; // myList() ends

// 사용자의 관심글 목록
function myHeart() {
	$.ajax({
		url: "mylist.my",
		type: "POST",
		data: {},
		dataType: "json",
		cache: "false",
		success: function (data) {
				let output = '<table class="table table-hover"><thead>';
				output += '<tr class="table-danger">';
				output += '<th scope="col" class="text-center">제목</th>';
				output += '<th scope="col" class="text-center">지역</th>';
				output += '<th scope="col" class="text-center">종료일</th>';
				output += '</tr></thead><tbody>'
				$(data.myheart).each(function (index, item) {
					output += `<tr onClick="location.href='boardDetail.bo?num=${item.board_id}'">`;
					output += '<td>' + textLengthOverCut(item.title, 20) + '</td>';
					output += '<td class="text-center">' + textLengthOverCut(item.address, 10, " ") + '</td>';
					output += '<td class="text-center">' + item.end_date + '</td></tr>';
				});
				output += '</tbody></table>';
				$("#myHeart").append(output);
			
		}, // success function ends
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" 
				+ "받은 데이터: " + request.responseText + "\n" 
				+ "error: " + error + "\n" 
				+ "error status: " + status
			);
		}
	}) // $.ajax ends
}; // myHeart ends

function textLengthOverCut(txt, len, lastTxt) {
	if (len == "" || len == null) { // 기본값
		len = 15;
	}
	if (lastTxt == "" || lastTxt == null) { // 기본값
		lastTxt = "...";
	}
	if (txt.length > len) {
		txt = txt.substr(0, len) + lastTxt;
	}
	return txt;
}
