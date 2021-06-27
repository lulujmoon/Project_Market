
 
/** 초기설정 1. 캐러셀
 */
 window.addEventListener('load', ()=>{
	 setCarousel();
});

 function goSelect(productNum){
	location.href = '/product/select/'+productNum;
}

/** 초기설정 2. 가격 표시
 */
const infoPrices = document.querySelectorAll('.info__price');
for(infoPrice of infoPrices){
	setPrice(infoPrice);
	if(infoPrice.innerText == '0 원' && infoPrice.previousElementSibling.innerText != 14){
		infoPrice.innerText = '무료나눔';
	}
}