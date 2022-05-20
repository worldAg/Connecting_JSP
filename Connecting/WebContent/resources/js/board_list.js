$(document).ready(function () {	
	const userId = '<%=(String) request.getSession().getAttribute("id")%>';
	// 글쓰기 버튼 클릭 시 글쓰기 페이지로 이동
	$("#goWrite").click(function () {
		if (userId == "null") {
			alert("로그인 후 이용 가능한 서비스입니다.");
			location.href = "login.net";
		} else {
			location.href = "boardWrite.bo";
		}
	});
	
	$(".all-list").click(function () {
		location.href = "boardList.bo";
	});
	
	$("#viewcount").change(function () {
		goBoard(1);
	});
			
	$("#orderby").change(function () {
		goBoard(1);
	});

}); // ready end

function goBoard(page) {
	const limit = $("#viewcount option:selected").val();		
	const orderby = $("#orderby option:selected").val();
	const category = '<%=(String) request.getAttribute("category")%>';
	const loc = '<%=(String) request.getAttribute("loc")%>';
	
	const sendData = "page=" + page + "&state=ajax&limit=" + limit + "&orderby=" + orderby + "&category=" + category + "&loc=" + loc;
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
			$("#viewcount").val(data.limit);
			if (data.listcount > 0) {
				$("#board-table tbody").remove();
				let num = data.listcount - (data.page - 1) * data.limit;
				let output = "<tbody>";
				let category = "";
				$(data.boardlist).each(function (index, item) {
					// 게시글의 카테고리를 구분
					if (item.category == 0) {
						category = "[전시회] ";
					} else if (item.category == 1) {
						category = "[박람회] ";
					} else if (item.category == 2) {
						category = "[버스킹] ";
					} else if (item.category == 3) {
						category = "[연극/공연] ";
					}
					output += "<tr>";
					output += "		<td>" + (num--) + "</td>";
					output += "		<td><a href='boardDetail.bo?num=" + item.board_id + "'>" + category + textLengthOverCut(item.title) + "</a></td>";
					output += "		<td>" + item.start_date + "</td>";
					output += "		<td>" + item.end_date + "</td>";
					output += "		<td>" + textLengthOverCut(item.address, 10, "...") + "</td>";
					output += "		<td>" + item.heart_num + "</td>";
					output += "</tr>";
				});
						
				output += "</tbody>";
				$("#board-table").append(output);						
				$(".pagination").empty(); // 페이징 처리 영역 내용 제거
				
				output = "";
				digit = "이전&nbsp;";
				href = "";
				i = 1;
				
				if (data.page > 1) {
					href = "href=javascript:goBoard(" + (data.page - 1) + ")";
				} 			
				output += setPaging(href, digit);
						
				for (let i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href = "";
					
					if (i != data.page) {
						href = "href=javascript:goBoard(" + i + ")";
					}
					output += setPaging(href, digit);
				}
						
				digit = "&nbsp;다음&nbsp;";
				href = "";
				if (data.page < data.maxpage) {
					href = "href=javascript:goBoard(" + (data.page + 1) + ")";
				}
				output += setPaging(href, digit);
						
				$(".pagination").append(output);
			} 
		} // success function ends
	}); // $.ajax ends
} 
		
function setPaging(href, digit) {
	/* 
		- 이전/다음을 클릭할 수 없으면 <a>에 class gray disabled
		- 현재 페이지라면 <li>에 class active
		- 모든 <li>에 page-item, 모든 <a>에 page-link
	*/
	let active = "";
	if (href == "") { // href가 빈문자열인 경우
		if (isNaN(digit)) { // 이전&nbsp; 또는 다음&nbsp;
			active = " disabled";
		} else {
			active = " active";
		}
	}
			
	let output = "<li class='page-item" + active + "'>";
	let anchor = "<a class='page-link'" + href + ">" + digit + "</a></li>";	
	output += anchor;
	return output;
}
		
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