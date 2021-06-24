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
	
/** 초기 설정 3. 현재 페이지에 selected 클래스를 추가한다.
 */
 const hidden = document.querySelector('.hidden');
 let selectedPage = document.querySelector('.code_'+hidden.innerText);
 selectedPage.classList.add('selected');
 
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

function goSelect(productNum){
	location.href = '/product/select/'+productNum;
}
