$(document).ready(function () {	
	goBoard(1);
	
	$("#goList").click(function () {
		location.href = "boardList.bo";
	});	
});
	
function goBoard(page) {
	const limit = 6;
	const sendData = "page=" + page + "&state=ajax&limit=" + limit;
	ajaxList(sendData);
} 
			
function ajaxList(sendData) {
	console.log("sendData: " + sendData);
	$.ajax({
		url: "boardList.bo",
		type: "POST",
		data: sendData,
		dataType: "json",
		cache: "false",
		success: function (data) {
			let output = "";
			$(data.boardlist).each(function (index, item) {
				// 게시글의 지역을 구분
				if (item.loc == 0) {
					local = "&#91;서울&#93";
				} else if (item.loc == 1) {
					local = "&#91;경기&#47;인천&#93";
				} else if (item.loc == 2) {
					local = "&#91;대전&#47;충청&#47;강원&#93";
				} else if (item.loc == 3) {
					local = "&#91;부산&#47;대구&#47;경상&#93";
				} else if (item.loc == 4) {
					local = "&#91;광주&#47;전라&#47;제주&#93";
				}
				output += '<div class="col">';
				output += `   <div class="card" onClick="location.href='boardDetail.bo?num=${item.board_id}'" style="cursor:pointer;">`;
				output += '      <img src="./resources/board_upload/' + item.board_img + '" class="card-img-top">';
				output += '      <div class="card-body">';
				output += '         <h5 class="card-title">' + item.title + '</h5>';
				output += '         <h6>' + local + '</h6>';
				output += '         <h6>' + item.start_date + ' &#126; ' + item.end_date + '</h6>';
				output += '</div></div></div>'
			});
			$("#mainBoard").append(output);						
		} // success function ends
	}) // $.ajax ends
};
