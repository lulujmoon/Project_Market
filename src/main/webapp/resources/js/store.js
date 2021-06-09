/**
 * 
 */
 
 /** 초기 설정 1. 가입날짜
 	* 1. .profile__joinDate 안의 텍스트를 가져온다.
 	* 2. yyyy년 mm월 dd일 로 작성한다.
 	* 3. 작성한 텍스트를 다시 .joinDate 안에 넣어준다.
  */
  
  var profile_joinDate = document.querySelector('.profile__joinDate');
  var joinDate = profile_joinDate.innerText;
  var dateArray = joinDate.split('-');
  
  joinDate = dateArray[0]+'년 '+dateArray[1]+'월 '+dateArray[2]+'일 가입';
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


