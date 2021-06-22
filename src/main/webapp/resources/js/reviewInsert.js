/**
 * 
 */

/** @function insertValue()
 *	-- 같은 그룹의 input을 찾아 점수를 넣고 별을 표시한다.
 */
function insertValue(){
	let target = event.currentTarget;
	let code = target.classList.item(2);
	let value = code.split('_')[1];
	if(target.classList.item(1) == 'star-back'){
		value = value*2;
	}else if(target.classList.item(1) == 'star-front'){
		value = value*2-1;
	}
	let input = target.parentNode.parentNode.firstElementChild;
	input.value = value;
}

/** @function setStars()
 *	-- value에 맞게 별을 표시한다.
 */
function setStars(){
	let container = event.currentTarget;
	let input = container.firstElementChild;
	let value = input.value;
	let type = null;
	let code = null;
	
	if(value%2==0){
		type = 'star-back';
		code = value/2;
	}else{
		type = 'star-front';
		code = Math.floor(value/2)+1;
	}
	let rateBack = container.querySelector('.rate-back');
	let rateFront = container.querySelector('.rate-front');
	
	setOtherStars(code, rateBack, rateFront);
	setSelectedStars(type, code, rateBack, rateFront);
}
 
/** @function hoverStars() 
 * 	-- 별들에 class를 추가한다. 
 * 	1. 이전 별에는 star-back의 full을 채우고 star-front의 half를 제거한다.
 * 	2. 다음 별에는 star-back의 full과 star-front의 half를 제거한다.
 * 	3. 선택한 별이 star-front이면 half를 추가한다.
 * 	4. 선택한 별이 star-back이면 full을 추가한다. 
 */ 
  
function hoverStars(){ 
	let target = event.currentTarget;
	let type = target.classList.item(1);
	let code = target.classList.item(2).split('_')[1];
	let rateBack = target.parentNode.parentNode.querySelector('.rate-back');
	let rateFront = target.parentNode.parentNode.querySelector('.rate-front');
	
	setOtherStars(code, rateBack, rateFront);
	setSelectedStars(type, code, rateBack, rateFront);
}

/** @function setOtherStars(rateBack, rateFront); 
 *	-- 다른 별들에 class를 추가한다.
 * 	1. 이전 별에는 star-back의 full을 채우고 star-front의 half를 제거한다.
 * 	2. 다음 별에는 star-back의 full과 star-front의 half를 제거한다.
 */
 function setOtherStars(code, rateBack, rateFront){
	for(let i=1;i<code;i++){
		let prevBack = rateBack.querySelector('.star_'+i);
		let prevFront = rateFront.querySelector('.star_'+i);
		prevBack.classList.add('full');
		prevFront.classList.remove('half');
	}
	
	for(let i=5;i>code;i--){
		let nextBack = rateBack.querySelector('.star_'+i);
		let nextFront = rateFront.querySelector('.star_'+i);
		nextBack.classList.remove('full');
		nextFront.classList.remove('half');
	}
}
 

/** @function setSelectedStars() 
 * -- 선택한 별에 class를 추가한다. 
 * 1. 선택한 별이 star-front이면 half를 추가한다.
 * 2. 선택한 별이 star-back이면 full을 추가한다. 
 */ 
function setSelectedStars(type, code, rateBack, rateFront){ 
	let selectedBack = rateBack.querySelector('.star_'+code);
	let selectedFront = rateFront.querySelector('.star_'+code);

	if(type == 'star-front'){ 
		selectedBack.classList.remove('full');
		selectedFront.classList.add('half');
	}else if(type == 'star-back'){
		selectedBack.classList.add('full');
		selectedFront.classList.remove('half');
	}
};

/* 함수 끝 */

const stars = document.querySelectorAll('.star');
const rateContents = document.querySelectorAll('.rate-content');
 

/** @event 1. 별점 설정
 */
for(let star of stars){
	star.addEventListener('click', insertValue);
	star.addEventListener('mouseover', hoverStars);
}

for(let content of rateContents){
	content.addEventListener('mouseout', setStars);
}

/**
 */
 
const btnSubmit = document.querySelector('.btn-submit');
const insertForm = document.querySelector('.insert-form');
const inputs = document.querySelectorAll('.input');
const reviewContent = document.querySelector('.review-content');

btnSubmit.addEventListener('click', ()=>{
	let required = true;
	for(input of inputs){
		if(input.value == ""){
			required = false;
			break;
		}
	}
	if(required){
		let conf = confirm('리뷰를 등록한 후에는 수정할 수 없습니다. 리뷰를 등록하시겠습니까?');
		if(conf){
			reviewContent.value = reviewContent.value.replace(/\n/gm, "<br>");
			insertForm.submit();
		}		
	}else{
		alert('필수 항목을 평가해주세요.');
	}
});