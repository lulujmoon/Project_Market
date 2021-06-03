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
/* 함수 끝 */

/* 초기 설정 */
const btnEdit = document.querySelector('.btn-edit');
const btnSubmit = document.querySelector('.btn-submit');
const btnCancel = document.querySelector('.btn-cancel');
const btnAdd = document.querySelector('.btn-add');
const infoContents = document.querySelectorAll('.info__content');
const infoInputs = document.querySelectorAll('.info__input');

btnEdit.classList.toggle('active');
for(content of infoContents){
	content.classList.toggle('active');
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
	let contentInputs = document.querySelectorAll('.content__input');
	const infoForm = document.querySelector('#info-form');
	infoForm.submit();
});


/* 우편번호 */
btnAdd.addEventListener('click', ()=>{
    new daum.Postcode({
        oncomplete: function(data) {
					location.href = '../memberLocation/insert?locationCode='+data.bcode;
        }
    }).open();	
})
