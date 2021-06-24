/**
 * 
 */
 
 /** 초기 설정 1. 가입날짜
  */
  var profile_joinDate = document.querySelector('.profile__joinDate');
  var joinDate = profile_joinDate.innerText;
  joinDate = setJoinDate(joinDate);
  profile_joinDate.innerText = joinDate;
 
 /** 초기 설정 2. 받은 평가
  */
  const rates = document.querySelectorAll('.rate');
  for(rate of rates){
		setRateInStar(rate);
	}
 
 /** const btnAvatar
	* 1. 클릭하면 file 팝업을 띄운다.
	* 2. input file이 변경되면 form을 제출한다.
  */
  
  const btnAvatar = document.querySelector('.profile__photo');
	const inputFile = document.querySelector('.input-file');
  const profileForm = document.querySelector('.profile__form');
  
  btnAvatar.addEventListener('click', ()=>{
		inputFile.click();
		inputFile.addEventListener('change', ()=>{
			profileForm.submit();
		});
});

/** 초기설정 3. 가격 표시
 */
const infoPrices = document.querySelectorAll('.info__price');
for(infoPrice of infoPrices){
	setPrice(infoPrice);
	if(infoPrice.innerText == '0 원'){
		infoPrice.innerText = '무료나눔';
	}
}


/* products */

function goSelect(productNum){
	location.href = '/product/select/'+productNum;
}

/* reviews */

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