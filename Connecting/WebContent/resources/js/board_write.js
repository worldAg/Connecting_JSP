$(document).ready(function() {	
	let check = 0; // 게시글 이미지 변경 여부
	
	// 수정페이지에서 기존의 category, loc를 기본으로 선택되도록 함
	const category = $('#selectedCat').text();
	const local = $('#selectedLoc').text();
    $("#category option[value='" + category + "']").attr('selected', 'selected');
    $("#loc option[value='" + local + "']").attr('selected', 'selected');

	// 게시글 이미지 추가
	$('input[type=file]').change(function(event) {
		check++;
		const inputfile = $(this).val().split('\\');
		const filename = inputfile[inputfile.length - 1];
		const pattern = /(gif|jpg|jpeg|png)$/i;
		if (pattern.test(filename)) {
			const reader = new FileReader(); // 파일을 읽기 위한 객체 생성
			// DataURL 형식으로 파일 읽어오기
			reader.readAsDataURL(event.target.files[0]);
			reader.onload = function(event) {// 읽기에 성공했을 때 실행되는 이벤트 핸들러
				// 이미지 경로 변경 및 이미지 초기화 가능한 x버튼
				$('#imgUpdate').html('<img id="boardImg" src="' + event.target.result + '">');
				$('#resetBtn').html('<img id="xBtn" src="./resources/img/x-reset.png">');
			}
		} else {
			alert('확장자는 gif, jpg, jpeg, png 사용 가능합니다.')
			$(this).val("");
			check = 0;
		}
	});
	
	// 수정페이지의 경우 x버튼 클릭 시 이미지 초기화
	$('#resetBtn').click(function() {
		check = 0;
		$('input[type=file]').val(""); // input file 초기화
		// 기존에 저장된 이미지로 변경
		const original = $('#originalImg').text();
		$('#imgUpdate').html('<img id="boardImg" src="./resources/board_upload/' + original + '">');
		$('#resetBtn').html(''); // x버튼 없애기
	});
	
	$('form').submit(function() {
		
		/*	게시글 수정 시 이미지 변경 안했을 경우(check == 0)
			$('#originalImg').text()의 기존 파일명을 파라미터 'check'라는 이름으로 form에 추가해 전송	*/
		if (check == 0) {
			const original = $('#originalImg').text();
			const changeVal = "<input type='hidden' value='" + original + "' name='check'>";
			$(this).append(changeVal);
		}
		
		if ($('select[name = category]').val() == "") { // 카테고리
			alert("카테고리를 선택하세요.");
			return false;
		}
		
		if ($('select[name = loc]').val() == "") { // 지역
			alert("지역을 선택하세요.");
			return false;
		}
		
		if ($('#board_img').val() == "") { // 게시글 이미지
			alert("게시글 사진은 필수입니다.");
			return false;
		}
		
		if ($('input[type = text]').eq(1).val() == "") { // 제목
			alert("제목은 필수입니다.");
			$('input[type = text]').eq(1).focus();
			return false;
		}
		
		if ($('input[type = text]').eq(2).val() == "") { // 주최명
			alert("주최명을 입력해 주세요.");
			$('input[type = text]').eq(2).focus();
			return false;
		}
		
		if ($('input[type = text]').eq(3).val() == "") { // 장소
			alert("장소를 입력해 주세요.");
			$('input[type = text]').eq(3).focus();
			return false;
		}
		
		// 날짜
		if ($('input[name = start_date]').val() == "") {
			alert("시작날짜를 입력해 주세요.");
			return false;
		}
		
		if ($('input[name = end_date]').val() == "") {
			alert("종료날짜를 입력해 주세요.");
			return false;
		}
		
		// 시간
		if ($('input[name = start_time]').val() == "") {
			alert("시작시간을 입력해 주세요.");
			return false;
		}
		
		if ($('input[name = end_time]').val() == "") {
			alert("종료시간을 입력해 주세요.");
			return false;
		}
		
		if ($('#content').val() == "") {
			alert("내용을 입력해 주세요.");
			return false;
		}
	})
	
	// 시작날짜와 종료날짜 비교
	$('input[name = end_date]').change(function() {
		const sysdate = new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0]; // yyyy-mm-dd
		const end_date = $('input[name = end_date]').val();
		const start_date = $('input[name = start_date]').val();

		if (start_date == "") {
			alert("시작일을 먼저 설정해 주세요.");
			$('input[name = end_date]').val("");
		} else if (end_date < sysdate) {
			alert("지난 날짜입니다.");
			$('input[name = end_date]').val("");
		} else if (start_date > end_date) {
			alert("종료일을 시작일과 같거나 이후로 설정해 주세요.");
			$('input[name = end_date]').val("");
		}
	});
	
	// 종료날짜 설정 후 시작날짜 변경 시
	$('input[name = start_date]').change(function() {
		const end_date = $('input[name = end_date]').val();
		const start_date = $('input[name = start_date]').val();

		if (start_date != "" && end_date != "") {
			if (start_date > end_date) {
				alert("시작일을 종료일과 같거나 이전로 설정해 주세요.");
				$('input[name = start_date]').val("");
			}
		}
	});
	
	// 시작시간과 종료시간 비교
	$('input[name = end_time]').change(function() {
		const end_time = $('input[name = end_time]').val();
		const start_time = $('input[name = start_time]').val();
		const end_date = $('input[name = end_date]').val();
		const start_date = $('input[name = start_date]').val();

		if (start_time == "") {
			alert("시작 시간을 먼저 설정해 주세요.");
			$('input[name = end_time]').val("");
		} else if (start_time >= end_time) {
			if (end_date == start_date) { // 시작일과 종료일이 같을 경우
				alert("종료 시간은 시작 시간 이후로 설정해 주세요.")
				$('input[name = end_time]').val("");
			}
		}
	});
	
})