/**
 * 
 */
 
 /** 초기설정 1. 정렬 기준을 찾아서 class에 selected를 추가해준다. 
  */
 const orderValue = document.querySelector('.order-value').innerText;
 const orderContents = document.querySelectorAll('.order-content');
 
 if(orderValue == 'lowPrice'){
	for(content of orderContents){
		if(content.innerText == '저가순'){
			content.classList.add('selected');
		}
	}
}else if(orderValue == 'highPrice'){
	for(content of orderContents){
		if(content.innerText == '고가순'){
			content.classList.add('selected');
		}
	}
}else{
	for(content of orderContents){
		if(content.innerText == '최신순'){
			content.classList.add('selected');
		}
	}
}

/** @function goSelect()
 */
 
 function goSelect(productNum){
	location.href = './select/'+productNum;
}