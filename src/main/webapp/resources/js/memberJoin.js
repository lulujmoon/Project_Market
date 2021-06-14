
/** 초기설정 1. 중복체크 여부
 *	1. username의 value가 변경될 때마다 다시 false로 설정해야 한다.
 */
 const inputUsername = document.querySelector('#username');
 var checked = false;
 
 inputUsername.addEventListener('change', ()=>{
	checked = false;
});

/** 기능 1. 유저네임 중복체크
 */
 const btnCheck = document.querySelector('.btn-check');
 btnCheck.addEventListener('click', ()=>{
	$.post('./idCheck', {
		username: inputUsername.value
	}, (result)=>{
		if(result == 0){
			alert('사용 가능한 아이디입니다.');
			checked = true;
		}else{
			alert('이미 존재하는 아이디입니다.');
			inputUsername.value = "";
			checked = false;
		}
	})
});

/** 기능 2. 가입폼 제출
 *	1. 중복체크 여부를 확인하고 제출한다.
 */
 const form = document.querySelector('#form-submit');
 const btnSubmit = document.querySelector('.btn-submit');
 
 btnSubmit.addEventListener('click', ()=>{
	if(checked){
		form.submit();
	}else{
		alert('아이디 중복체크는 필수입니다.');
	}
});
