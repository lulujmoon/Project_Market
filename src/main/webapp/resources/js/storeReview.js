
/** 초기설정. 후기 작성일 계산
 */
 const reviewDates = document.querySelectorAll('.rv__date');
 for(rvdate of reviewDates){
	let datetime = rvdate.innerText;
	rvdate.innerText = calculateTime(datetime);
}

/** @function showProduct(productNum)
 *	--팝업으로 상품 페이지를 보여준다.
 */
 function showProduct(productNum){
	window.open(
		'/product/select/'+productNum, 
		'',
		'resizable');
}

/** @function showContent()
 *	-- content를 보여주고 숨긴다.
 */
 function manageContent(){
	let card = event.currentTarget;
	let more = event.currentTarget.lastElementChild;
	let content = more.previousElementSibling;
	card.classList.toggle('active');
	content.classList.toggle('active');
	more.classList.toggle('active');
}