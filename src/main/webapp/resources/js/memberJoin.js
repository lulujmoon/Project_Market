
/** 초기설정 1. 중복체크 여부
 *	1. username의 value가 변경될 때마다 다시 false로 설정해야 한다.
 *	2. 폼을 제출했는데 검증에 의해 돌아왔을 때는 모델에 담긴 checked 데이터를 통해 true로 설정한다.
 *	2-1. 사용자가 임의로 스크립트를 수정해 중복확인 없이 폼을 제출해도 중복되면 가입이 불가능하므로 크게 신경쓰지 않아도 됨.
 */
 const inputUsername = document.querySelector('#username');
 const divCheck = document.querySelector('.checked');
 
 var checked = false;
 if(divCheck.innerText == 'checked'){
	checked = true;
}
 
 
 inputUsername.addEventListener('change', ()=>{
	checked = false;
});

/** 기능 1. 유저네임 중복체크
 */
 const btnCheck = document.querySelector('.btn-check');
 btnCheck.addEventListener('click', ()=>{
	$.post('./idCheck', {
		username: inputUsername.value
	}, function(result){
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
 const btnSubmit = document.querySelector('.btn-submit');
 const btnPreSubmit = document.querySelector('.btn-preSubmit');
 
 btnPreSubmit.addEventListener('click', ()=>{
	if(checked){
		btnSubmit.click();
	}else{
		alert('아이디 중복체크는 필수입니다.');
	}
});
