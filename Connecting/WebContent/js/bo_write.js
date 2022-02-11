$(document).ready(function() {	

	$('input[type=file]').change(function(event){
			var inputfile = $(this).val().split('\\');
			var filename = inputfile[inputfile.length - 1];
			var pattern = /(gif|jpg|jpeg|png)$/i;
			if(pattern.test(filename)){
				var reader = new FileReader(); // 파일을 읽기 위한 객체 생성
				// DataURL 형식으로 파일 읽어오기
				reader.readAsDataURL(event.target.files[0]);
				reader.onload = function(event) {// 읽기에 성공했을 때 실행되는 이벤트 핸들러
					$('#showImage').html('<img width="300px" src="' + event.target.result + '">');	
				};
			} else {
				alert('확장자는 gif, jpg, jpeg, png 사용 가능합니다.')
				$(this).val("");
			}
		console.log($('#change_img').val());
	})
	
	
	
	
	$('.board_form').submit(function(){
		
		//카테고리
		if($('select[name = category]').val() == ""){
			alert("카테고리를 선택하세요.")
			return false;
		}
		
		//지역
		if($('select[name = loc]').val() == ""){
			alert("지역을 선택하세요.")
			return false;
		}
		
		//이미지
		if($('#board_img').val()==""){
			alert("사진을 반드시 첨부해 주세요.")
			return false;
		}
		
		//제목
		if($('input[type = text]').eq(1).val()==""){
			alert("제목을 입력해 주세요.")
			$('input[type = text]').eq(1).focus();
			return false;
		}
		
		//주최자명
		if($('input[type = text]').eq(2).val()==""){
			alert("주최자명을 입력해 주세요.")
			$('input[type = text]').eq(2).focus();
			return false;
		}
		
		//장소
		if($('input[type = text]').eq(3).val()==""){
			alert("장소를 입력해 주세요.")
			$('input[type = text]').eq(3).focus();
			return false;
		}
		
		//날짜
		if($('input[name = start_date]').val()==""){
			
			alert("시작날짜를 입력해 주세요.")
			return false;
		}
		
		if($('input[name = end_date]').val()==""){
			alert("종료날짜를 입력해 주세요.")
			return false;
		}
		
		//시간
		if($('input[name = start_time]').val()==""){
			
			alert("시작시간을 입력해 주세요.")
			return false;
		}
		
		if($('input[name = end_time]').val()==""){
			alert("종료시간을 입력해 주세요.")
			return false;
		}
		
		if($('#content').val()==""){
			alert("내용을 입력해 주세요.")
			return false;
		}
		
		//사진 변경 안했을시 값설정
		if(!$('#change_img').val()){
			var img_val = $('#filevalue').text();
			console.log($('#change_img').val());
			console.log(img_val);
			console.log($('#change_img').val());
			html = "<input type='hidden' value='" + img_val + "' name='check'>";
			$(this).append(html);
		}
		
	})
	
	
	//현재 날짜와 종료날짜 비교
	
	
	
	//시작날짜와 종료날짜 비교
	$('input[name = end_date]').change(function(){
		console.log("end_date")
		
		//yyyy-mm-dd
		var sysdate = new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0];

		var end_date = $('input[name = end_date]').val();
		var start_date = $('input[name = start_date]').val();
		
		if(start_date == ""){
			alert("시작날짜를 먼저 설정해 주세요.")
			$('input[name = end_date]').val("");
		}else if(end_date < sysdate){
			alert("지난 날짜 입니다.")
			$('input[name = end_date]').val("");
		}else if(start_date > end_date){
			alert("종료날짜를 시작날짜와 같거나 이후로 설정해 주세요.")
			$('input[name = end_date]').val("");
		}
		
	})
	
	//시작시간과 종료시간 비교
	$('input[name = end_time]').change(function(){
		var end_time = $('input[name = end_time]').val();
		var start_time = $('input[name = start_time]').val();
		var end_date = $('input[name = end_date]').val();
		var start_date = $('input[name = start_date]').val();
		
		
		if(start_time == ""){
			alert("시작시간을 먼저 설정해 주세요.")
			$('input[name = end_time]').val("");
		}else if(start_time >= end_time){
			if(end_date == start_date){
				alert("종료시간은 시작시간 이후로 설정해 주세요.")
				$('input[name = end_time]').val("");
			}
		}
		
	})
	
})