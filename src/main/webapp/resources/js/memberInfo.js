/**
 * 
 */
 
/**
	* @function showEdit()
	* 1. 수정 버튼을 누르면 info__content 요소는 display:none, info__input 요소를 보여준다.
	* 2. 수정 버튼은 사라지고 완료, 취소 버튼을 보여준다.
	* 3. 완료 버튼을 누르면 '/member/update'로 form을 보낸다.
	* 4. 취소 버튼을 누르면 다시 info__content 요소를 보여준다.
 */
 
 function showEdit(){
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

/**
	* @function makeInsertForm
	* 1. username과 법정동코드를 매개변수로 받는다.
	* 2. form을 만든다.
	* 3. action="../memberLocation/insert", method="post"
	* 4. form에 username과 법정동코드를 input으로 담는다.
 */
 
 function makeInsertForm(locationCode){
	 let newForm = document.createElement('form');
	 newForm.action = '../memberLocation/insert';
	 newForm.method = 'post';
	 newForm.id = 'location-insert-form';
	
	 let inputUsername = document.createElement('input');
	 let inputLocationCode = document.createElement('input');
	 
	 inputLocationCode.type = "hidden";
	 inputLocationCode.name = "locationCode";
	 inputLocationCode.value = locationCode;

	 newForm.appendChild(inputLocationCode);
	 
	 document.body.appendChild(newForm);
}

/*
 * @function makeDeleteForm(locationNum)
 * 1. form을 만든다.
 * 2. action="../memberLocation/delete", method="post"
 * 3. form에 locationNum을 input으로 담는다.
 */
 
 function makeDeleteForm(locationNum){
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


/* 내 지역 저장 */
function insertLocation(btnNum){
    new daum.Postcode({
        oncomplete: function(data) {
					makeInsertForm(data.bcode);
					let insertForm = document.querySelector('#location-insert-form');
					insertForm.submit();
        }
    }).open();
};

/* 내 지역 삭제 */
function deleteLocation(btnNum, locationNum){
	makeDeleteForm(locationNum);
	let deleteForm = document.querySelector('#location-delete-form');
	deleteForm.submit();
}

/* 함수 끝 */

/* 초기 설정 */
const btnEdit = document.querySelector('.btn-edit');
const btnSubmit = document.querySelector('.btn-submit');
const btnCancel = document.querySelector('.btn-cancel');
const btnAdd = document.querySelector('.btn-add');
const infoContents = document.querySelectorAll('.info__content');
const infoInputs = document.querySelectorAll('.info__input');

const btnInserts = document.querySelectorAll('.btn-insert');
const btnDeletes = document.querySelectorAll('.btn-delete');

btnEdit.classList.toggle('active');
for(content of infoContents){
	content.classList.toggle('active');
}

for(btnInsert of btnInserts){
	btnInsert.classList.toggle('active');
}
/* 초기 설정 끝 */


btnEdit.addEventListener('click', ()=>{
	manageBtns();
	showEdit();
});

btnCancel.addEventListener('click', ()=>{
	manageBtns();
	showEdit();
});

btnSubmit.addEventListener('click', ()=>{
	const infoForm = document.querySelector('#info-form');
	infoForm.submit();
});
