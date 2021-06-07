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
  * 1. .rate에서 수로 된 평가값을 받아온다.
  * 2. 홀수인지 짝수인지 구분한다.
  * 3. 2로 나눈 값을 구하고 새로 작성한 텍스트 안에 꽉찬 별을 그 값만큼 넣어준다.
  * 4. 홀수이면 반 별을 텍스트 안에 넣어준다.
  * 5. 나머지는 빈 별로 채운다.
  * 6. .rate 안에 텍스트를 대체한다.
  */
 
  const rates = document.querySelectorAll('.rate');
  const fullStar = '<i class="fas fa-star"></i>';
  const halfStar = '<i class="fas fa-star-half-alt"></i>';
  const emptyStar = '<i class="far fa-star"></i>'

  for(rate of rates){
		let rateInNum = Number(rate.innerText);
		let rateInStar = '';
		
		if(rateInNum%2!=0){
			for(let i=0;i<rateInNum/2-1;i++){
				rateInStar = rateInStar + fullStar;
			}
			rateInStar = rateInStar + halfStar;
			for(let i=0;i<4-rateInNum/2;i++){
				rateInStar = rateInStar + emptyStar;
			}		
		}else{
			for(let i=0;i<rateInNum/2;i++){
				rateInStar = rateInStar + fullStar;
			}
			for(let i=0;i<5-rateInNum/2;i++){
				rateInStar = rateInStar + emptyStar;
			}
		}
		
		rate.innerHTML = rateInStar;
		
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


