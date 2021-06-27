/**
 * 
 */
 
 /** 초기설정 1. 정렬 기준을 찾아서 class에 selected를 추가해준다. 
 	*  1. hidden 클래스가 있는 div는 순서대로 카테고리코드, 마이로케이션, 오더, 페이지의 값을 가지고 있다.
 	*  2. 순서대로 값과 클래스명이 일치하는 부분을 찾아서 selected 클래스를 추가해준다.
  */
 const hiddens = document.querySelectorAll('.hidden');
 const types = ['category', 'myLocation', 'order', 'page'];
 for(let i=0;i<types.length;i++){
	 let container = document.querySelector('.list-'+types[i]);
	 let selectedContent = container.querySelector('.code_'+hiddens[i].innerText);
	 selectedContent.classList.add('selected');	
}
 

/** @function goSelect()
 */
 
 function goSelect(productNum){
	location.href = './select/'+productNum;
}

/** 초기설정 2. 날짜 표시
 */
 const info__dates = document.querySelectorAll('.info__date');
 for(date of info__dates){
	 let datetime = date.innerText;
	 date.innerText = calculateTime(datetime);	
}

/** 초기설정 3. 가격 표시
 */
 const infoPrices = document.querySelectorAll('.info__price');
 for(infoPrice of infoPrices){
	setPrice(infoPrice);
	if(infoPrice.innerText == '0 원' && infoPrice.previousElementSibling.innerText != 14){
		infoPrice.innerText = '무료나눔';
	}
}