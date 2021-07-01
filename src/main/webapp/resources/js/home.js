
 
/** 초기설정 1. 캐러셀
 */
 window.addEventListener('load', ()=>{
	 setCarousel();
});

function goProductSelect(productNum){
	location.href = '/product/select/'+productNum;
}

function goSocialSelect(socialNum){
	location.href = '/social/select?socialNum='+socialNum;
}
/** 초기설정 2. 가격 표시
 */
const infoPrices = document.querySelectorAll('.info__price');
for(let infoPrice of infoPrices){
	setPrice(infoPrice);
	if(infoPrice.innerText == '0 원' && infoPrice.previousElementSibling.innerText != 14){
		infoPrice.innerText = '무료나눔';
	}
}

/** 초기설정 3. 날짜 표시
 */
const infoDates = document.querySelectorAll('.info__date');
for(let infoDate of infoDates){
	infoDate.innerText = calculateTime(infoDate.innerText);
}

const sclDates = document.querySelectorAll('.scl__date');
for(let sclDate of sclDates){
	sclDate.innerText = calculateTime(sclDate.innerText);
}