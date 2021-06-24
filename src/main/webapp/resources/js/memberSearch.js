
/** 초기설정 1. alert
 *	-- div class="alert"가 비어있지 않으면 그 안의 메세지를 alert한다.
 */
const result = document.querySelector('.alert');
if(result.innerText!=""){
	let loc = null;
	let message = null;
	if(result.innerText == 'success'){
		message = '이메일이 발송되었습니다.';
		loc = './login';
	}else if(result.innerText == 'fail'){
		message = '일치하는 정보가 없습니다.';
		loc = './search';
	}
	
	alert(message);
	location.href = loc;
}