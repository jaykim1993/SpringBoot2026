/* 회원가입 유효성 검사 규칙 */

function signupForm(){
	console.log("회원가입 form");
	/* DOM으로 form 연결 */
	let form = document.signup_form;
	if(form.id.value==""){
		alert("아이디를 입력하셔야 합니다.");
		form.id.focus();
	} else if(form.pw.value==""){
		alert("비밀번호를 입력하셔야 합니다.");
		form.pw.focus();
	} else if(form.pwchk.value==""){
		alert("비밀번호 확인이 필요합니다.");
		form.pwchk.focus();
	} else if(form.pw.value!=form.pwchk.value){
		alert("입력하신 비밀번호가 다릅니다.");
		form.pwchk.focus();
	}else if(form.mail.value==""){
		alert("email을 입력하셔야 합니다.");
		form.email.focus();
	} else if(form.phone.value==""){
		alert("전화번호를 입력하셔야 합니다.");
		form.phone.focus();
	} else {
		/* 위조건에 해당없으면 전송 */
		form.submit();
	}
}