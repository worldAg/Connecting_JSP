$(document).ready(function () {	
	goBoard(1);
	
	$(".go-list").click(function () {
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
		type: "post",
		data: sendData,
		url: "boardList.bo",
		dataType: "json",
		cache: "false",
		success: function (data) {
			let output = "";
			$(data.boardlist).each(function (index, item) {
				// 게시글의 지역을 구분
				if (item.loc == 0) {
					local = "[서울]";
				} else if (item.loc == 1) {
					local = "[경기/인천] ";
				} else if (item.loc == 2) {
					local = "[대전/충청/강원]";
				} else if (item.loc == 3) {
					local = "[부산/대구/경상] ";
				} else if (item.loc == 4) {
					local = "[광주/전라/제주] ";
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
