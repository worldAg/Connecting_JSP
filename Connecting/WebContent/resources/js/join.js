$(function() { // = $(document).ready(functon(){...})

	let checkid = false;
	let checkpass = false;
	let checkemail = false;
	let checkimg = 0;
	
	// 회원 정보 수정 페이지일 경우 유효성 검사 기본 값은 true
	if ($("form").attr('name') === "memberModifyForm") {
		checkid = true;
		checkpass = true;
		checkemail = true;
	}
	
	// 회원 정보 수정 페이지일 경우 기존의 프로필 사진이 없을 경우 x버튼 없애기
	if ($('#originalImg').text() == "") {
		$('#resetBtn').html('');
	}
	
	// 아이디 유효성 검사
	$("input[id='floatingId']").on('keyup', function() {
		$(".id-message").empty();
		const pattern = /^\w{5,12}$/; // \w는 [A-Za-z0-9_]의 의미
		const id = $("input:eq(0)").val();
		if (!pattern.test(id)) {
			$(".id-message").css('color', 'red').html("아이디는 5~12자의 영문, 숫자와 특수문자&#40;_	&#41;만 사용 가능합니다.");
			checkid = false;
			return;
		}

		$.ajax({
			url: "idcheck.net",
			data: { "id": id },
			success: function(resp) {
				if (resp == -1) { // DB에 해당 id가 없는 경우
					$(".id-message").css('color', 'green').html("사용 가능한 아이디입니다.");
					checkid = true;
				} else { // DB에 해당 id가 있는 경우(0)
					$(".id-message").css('color', 'red').html("사용 중인 아이디입니다.");
					checkid = false;
				}
			}
		});
	}) // id keyup ends

	// 비밀번호 확인 
	$("input[name='pass']").on('keyup', function() {
		const pass = $("input[name='pass']").val();
		const pass2 = $("input[name='pass2']").val();

		if (pass2 == "") {
			$(".pass-message").css('color', 'red').html("비밀번호 확인이 필요합니다.");
			checkpass = false;
		} if (pass != pass2) {
			$(".pass-message").css('color', 'red').html("비밀번호가 일치하지 않습니다.");
			checkpass = false;
		} else {
			$(".pass-message").css('color', 'green').html("비밀번호가 일치합니다.");
			checkpass = true;
		}
	});

	$("input[name='pass2']").on('keyup', function() {
		const pass = $("input[name='pass']").val();
		const pass2 = $("input[name='pass2']").val();

		if (pass != pass2) {
			$(".pass-message").css('color', 'red').html("비밀번호가 일치하지 않습니다.");
			checkpass = false;
		} else {
			$(".pass-message").css('color', 'green').html("비밀번호가 일치합니다.");
			checkpass = true;
		}
	});

	// 이메일 유효성 검사
	$("input[name='email']").on('keyup', function() {
		$(".email-message").empty();
		/*
			\w는 [A-Za-z0-9_]의 의미.
			+는 1회 이상 반복을 의미. {1,}와 동일.
			\w+는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미.
		*/
		const pattern = /^\w+@\w+[.]\w{3}$/;
		const email = $("input[name='email']").val();
		if (!pattern.test(email)) {
			$(".email-message").css('color', 'red').html("이메일 형식에 맞지 않습니다.");
			checkemail = false;
		} else {
			$(".email-message").css('color', 'green').html("이메일 형식에 맞습니다.");
			checkemail = true;
		}
	}); // email keyup ends

	// 프로필 이미지
	$('input[type=file]').change(function(event) {
		checkimg++;
		const inputfile = $(this).val().split('\\');
		const filename = inputfile[inputfile.length - 1]; // inputfile.length - 1 = 2
		const pattern = /(gif|jpg|jpeg|png)$/i;
		if (pattern.test(filename)) {
			$('#filename').text(filename);
			const reader = new FileReader(); // 파일을 읽기 위한 객체 생성
			// DataURL 형식으로 파일 읽어오기
			reader.readAsDataURL(event.target.files[0]);
			reader.onload = function(event) {// 읽기에 성공했을 때 실행되는 이벤트 핸들러
				$('#showImage').html('<img id="profileImg" src="' + event.target.result + '">');
				$('#resetBtn').html('<img id="xBtn" src="./resources/img/x-circle.png">');
			};
		} else {
			alert('확장자는 gif, jpg, jpeg, png 사용 가능합니다.');
			$(this).val("");
			$('#showImage').html('<img id="profileImg" src="./resources/img/profile.png">');
			$('#resetBtn').html('');
		}
	});
	
	// x버튼 클릭 시 기본 프로필 이미지로 변경
	$('#resetBtn').click(function() {
		checkimg++;
		$('input[type=file]').val(""); // input file 초기화
		$('#showImage').html('<img id="profileImg" src="./resources/img/profile.png">');
		$('#resetBtn').html(''); // x버튼 없애기
	});
	
	$('form').submit(function() {
		if (!checkid) {
			alert("아이디를 확인해 주세요.");
			$("input:eq(0)").val('').focus();
			return false;
		}

		if (!checkpass) {
			alert("비밀번호를 확인해 주세요.");
			$("input[name='pass']").val('').focus();
			return false;
		}

		if (!checkemail) {
			alert("이메일을 확인해 주세요.");
			$("input:eq(5)").focus();
			return false;
		}

		/*	프로필 이미지 변경 안했을 경우(checkimg == 0)
			$('#originalImg').text()의 기존 파일명을 파라미터 'check'라는 이름으로 form에 추가해 전송	*/
		if (checkimg == 0) {
			const original = $('#originalImg').text();
			const changeVal = "<input type='hidden' value='" + original + "' name='check'>";
			$(this).append(changeVal);
		}
	});

})