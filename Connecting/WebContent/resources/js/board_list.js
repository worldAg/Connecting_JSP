$(document).ready(function () {	
			
	// 글쓰기 버튼 클릭 시 글쓰기 페이지로 이동
	$("#writeBtn").click(function () {
		location.href = "boardWrite.bo";
	})
			
			
			
			$("#orderby").change(function () {
				goBoard(1);
			});			
			
			$("input[type='radio'][name='select_what']").change(function() {
			    if (this.value == "board") {
			        goBoard(1, 0);
			        $("#orderby").val("0").prop("selected", true);
			        $("#writeBtn").removeClass("hide-container");
			        $("#board-container").removeClass("hide-container");			        
			        $("#notice-container").addClass("hide-container");			       
			    } else if (this.value == "notice") {
			    	showNotice();
			    	$("#writeBtn").addClass("hide-container");
			    	$("#board-container").addClass("hide-container");	
			    	$("#notice-container").removeClass("hide-container");		
			    }
			}); // 게시글 혹은 공지사항 라디오 버튼을 눌렀을 경우 처리.
		}); // document.ready ends
		
		function showNotice() {
			$.ajax({
				url: "NoticeList.bo",
				dataType: "json",
				cache: "false",
				
				success: function (data) {
					if (data.listcount > 0) {
						console.log("notice list success!");
						$("#notice-container table tbody").empty();
						var num = data.listcount;
						var output = "";
						$(data.noticelist).each(function (index, item) {
							output += "<tr>";
							output += "		<td>" + (num--) + "</td>";
							output += "		<td><a href='noticeDetail.mgr?notice_id=" + item.notice_id + "'>" + textLengthOverCut(item.title) + "</a></td>";
							output += "		<td>" + (item.write_date) + "</td>";
							output += "</tr>";
						}); // each ends
						$("#notice-container table tbody").append(output);
					} // if ends
				} // success ends
			});
		}
		
		function goBoard(page, orderby) {
			if (orderby === undefined) {
				orderby = $("#orderby option:selected").val();
			}
			var sendData = "page=" + page + "&state=ajax&orderby=" + orderby;
			ajaxBoard(sendData);
		} // function goBoard ends
		
		function ajaxBoard(sendData) {
			console.log("sendData: " + sendData);
			$.ajax({
				type: "post",
				data: sendData,
				url: "BoardList.bo",
				dataType: "json",
				cache: "false",
				
				success: function (data) {
					console.log("AJAX success!");
					if (data.listcount > 0) {
						$("#board-table tbody").remove();
						var num = data.listcount - (data.page - 1) * data.limit;
						
						var output = "<tbody>";
						var category = "";
						
						$(data.boardlist).each(function (index, item) {
							// 게시글의 카테고리를 구분해주는 부분
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
							output += "		<td><a href='BoardDetailAction.bo?num=" + item.board_id + "'>" + category + textLengthOverCut(item.title) + "</a></td>";
							output += "		<td>" + item.id + "</td>";
							output += "		<td>" + item.write_date + "</td>";
							output += "		<td>" + item.start_date + "</td>";
							output += "		<td>" + item.end_date + "</td>";
							output += "		<td style='text-align:right;'>" + item.heart_num + "</td>";
							output += "</tr>";
						});
						
						output += "</tbody>";
						$("#board-table").append(output);						
						
						// 페이징 처리 부분
						$("ul.pagination").empty();
						output = "";
						var digit = "이전&nbsp;";
						var href = "";
						
						if (data.page > 1) {
							href = "href=javascript:goBoard(" + (data.page - 1) + ")";
						} 
						
						output += setPagingBoard(href, digit);
						
						for (var i = data.startpage; i <= data.endpage; i++) {
							digit = i;
							href = "";
							if (i != data.page) {
								href = "href=javascript:goBoard(" + i + ")";
							}
							output += setPagingBoard(href, digit);
						}
						
						digit = "&nbsp;다음&nbsp;";
						href = "";
						if (data.page < data.maxpage) {
							href = "href=javascript:goBoard(" + (data.page + 1) + ")";
						}
						output += setPagingBoard(href, digit);
						
						$("ul.pagination").append(output);
					} // if data.listcount > 0 ends
				} // success function ends
			}); // $.ajax ends
		} // function ajax ends
		
		function setPagingBoard(href, digit) {
			// 이전/다음을 클릭할 수 없으면 <a>에 class gray
			// 현재 페이지라면 <li>에 class active
			// 모든 <li>에 page-item
			// 모든 <a>에 page-link
			var active = "";
			var gray = "";
			
			if (href == "") {
				if (isNaN(digit)) {
					gray = " gray";
				} else {
					active = " active";
				}
			}
			
			var output = "<li class='page-item" + active + "'>";
			var anchor = "<a class='page-link" + gray + "'" + href + ">" + digit + "</a></li>";
			
			output += anchor;
			return output;
		}
		
		function textLengthOverCut(txt, len, lastTxt) {
	        if (len == "" || len == null) { // 기본값
	            len = 12;
	        }
	        if (lastTxt == "" || lastTxt == null) { // 기본값
	            lastTxt = "...";
	        }
	        if (txt.length > len) {
	            txt = txt.substr(0, len) + lastTxt;
	        }
	        return txt;
	    }