 
const btnEdit = document.querySelector('.btn-edit');
const btnSubmit = document.querySelector('.btn-submit');
const btnCancel = document.querySelector('.btn-cancel');
const btnDelete = document.querySelector('.btn-delete');
const btnAdd = document.querySelector('.btn-add');
const infoContents = document.querySelectorAll('.info__content');
const infoInputs = document.querySelectorAll('.info__input');
const formNotice = document.querySelectorAll('.form-notice');
 
/** @function manageEdit(), @function manageBtns()
	* 1. 수정 버튼을 누르면 info__content 요소는 display:none, info__input 요소를 보여준다.
	* 2. 수정 버튼은 사라지고 완료, 취소 버튼을 보여준다.
	* 3. 완료 버튼을 누르면 '/member/update'로 form을 보낸다.
	* 4. 취소 버튼을 누르면 다시 info__content 요소를 보여준다.
 */
 
 function manageEdit(){
	for(content of infoContents){
		content.classList.toggle('active');
	}
	for(input of infoInputs){
		input.classList.toggle('active');
	}
}

function manageBtns(){
	btnEdit.classList.toggle('active');
	btnSubmit.classList.toggle('active');
	btnCancel.classList.toggle('active');	
}

/** @function deleteMember(username) 
 *	-- 회원 탈퇴를 위한 폼을 만들어 제출한다.
 */
 function deleteMember(username){
	let check = confirm('정말 탈퇴하시겠습니까? 탈퇴해도 작성한 글은 삭제되지 않습니다.');
	if(check){
		let newForm = document.createElement('form');
		newForm.action = './delete';
		newForm.method = 'post';
		newForm.id = 'member-delete-form';
		
		let inputUsername = document.createElement('input');
		inputUsername.type = 'hidden';
		inputUsername.name = 'username';
		inputUsername.value = username;
		
		newForm.appendChild(inputUsername);
		document.body.appendChild(newForm);
		
		const form = document.querySelector('#member-delete-form');
		form.submit();
	}
}
	

/** @function makeLocationInsertForm(locationCode)
	* 1. username과 법정동코드를 매개변수로 받는다.
	* 2. form을 만든다.
	* 3. action="../memberLocation/insert", method="post"
	* 4. form에 username과 법정동코드를 input으로 담는다.
 */
 
 function makeLocationInsertForm(locationCode){
	 let newForm = document.createElement('form');
	 newForm.action = '../memberLocation/insert';
	 newForm.method = 'post';
	 newForm.id = 'location-insert-form';
	
	 let inputLocationCode = document.createElement('input');
	 
	 inputLocationCode.type = "hidden";
	 inputLocationCode.name = "locationCode";
	 inputLocationCode.value = locationCode;

	 newForm.appendChild(inputLocationCode);
	 
	 document.body.appendChild(newForm);
}

/** @function makeLocationDeleteForm(locationNum)
 *  1. form을 만든다.
 *  2. action="../memberLocation/delete", method="post"
 *  3. form에 locationNum을 input으로 담는다.
 */
 
 function makeLocationDeleteForm(locationNum){
	let newForm = document.createElement('form');
	newForm.action = '../memberLocation/delete';
	newForm.method = 'post';
	newForm.id = 'location-delete-form';
	
	let inputLocationNum = document.createElement('input');
	inputLocationNum.type = 'hidden';
	inputLocationNum.name = 'locationNum';
	inputLocationNum.value = locationNum;
	
	newForm.appendChild(inputLocationNum);
	document.body.appendChild(newForm);
}


/** @function insertLocation()
 *	-- 내 지역 저장 
 */
function insertLocation(){
    new daum.Postcode({
        oncomplete: function(data) {
					makeLocationInsertForm(data.bcode);
					let insertForm = document.querySelector('#location-insert-form');
					insertForm.submit();
        }
    }).open();
};

/** @function deleteLocation(locationNum)
 *	-- 내 지역 삭제 
 */
function deleteLocation(locationNum){
	makeLocationDeleteForm(locationNum);
	let deleteForm = document.querySelector('#location-delete-form');
	deleteForm.submit();
}

/** @function checkCondition(password, phone, email)
 *	-- 정보 수정 폼 제출 전 조건을 확인한다.
 *	0. 비밀번호, 전화번호, 이메일을 매개변수로 받는다.
 *	1. 비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상 포함된 8~20자
 *	2. 전화번호는 10 ~ 11 자리의 숫자
 *	3. 이메일은 @ 포함
 *	4. 모든 조건 부합 시 0, 비밀번호 문제는 1, 전화번호 문제는 2, 이메일 문제는 3을 반환한다.
 *		4-1. 여러 조건 미충족 시 비밀번호-전화번호-이메일 순으로 우선순위를 정해 반환한다.
 */
 function checkCondition(password, phone, email){
	let result;
	
	const pwCheck = /^(?=.*[a-zA-Z])(?=.*[\d])(?=.*[`~!@#$%^&*\-_=+\/]).{8,20}$/;
	const phoneCheck = /\d{10,11}/;
	const emailCheck = /@+/;
	
	if(password.search(pwCheck)<0){
		result = 1;		
	}else if(phone.search(phoneCheck)<0){
		result = 2;
	}else if(email.search(emailCheck)<0){
		result = 3;
	}else{
		result = 0;
	}
	return result;
}
 


/* 초기 설정 */

	for(content of infoContents){
		content.classList.toggle('active');
	}	
	btnEdit.classList.toggle('active');


/* 초기 설정 끝 */


btnEdit.addEventListener('click', ()=>{
	manageBtns();
	manageEdit();
	for(notice of formNotice){
		notice.classList.remove('hidden');
	}	
});

btnCancel.addEventListener('click', ()=>{
	manageBtns();
	manageEdit();
	for(notice of formNotice){
		notice.classList.add('hidden');
	}
});


/** 기능. 회원정보 수정
 *	1. 완료 버튼을 누르면 실행
 *	2. 비밀번호와 이메일, 전화번호 조건을 확인
 *	3-1. 부합하지 않으면 알림
 *	3-2. 부합하면 폼 제출
 */

	
btnSubmit.addEventListener('click', ()=>{
	let password = infoInputs[1].value;
	let phone = infoInputs[3].value;
	let email = infoInputs[4].value;
	
	
	let result = checkCondition(password, phone, email);
	
	switch(result){
		case 1: 
			alert('비밀번호를 확인하세요. 영문 대,소문자와 숫자,특수기호가 적어도 1개이상 포함되어야 하며 글자수는 8~20자여야 합니다.');
			break;
		case 2:
			alert('전화번호를 확인하세요. 10 ~ 11 자리의 숫자여야 합니다.');
			break;
		case 3:
			alert('이메일을 확인하세요.');
			break;
		case 0:
			const formData = new FormData();
			for(input of infoInputs){
				formData.append(input.name, input.value);
			}
			
			$.ajax({
				type: 'POST', 
				url: './update',
				data: formData, 
				processData: false,
				success: (result)=>{			
					if(result){
						alert('수정되었습니다.');
						location.reload;
					}}
				});
			break;
	}
	


});