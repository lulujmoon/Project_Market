
/** @function setPrice(className)
 *  -- 숫자를 '100,000원' 형태로 바꾸는 함수
 *  1. number.toLocaleString(locale, option) 이용
 *  2. 가격 정보가 담긴 선택자를 매개변수로 받는다.
 */
 function setPrice(selector){
	let priceDiv = document.querySelector(selector);
	let price = Number(priceDiv.innerText);
	price = price.toLocaleString('currency');
	priceDiv.innerText = price+' 원';
}

/** @function setRateInStar(selector)
 *	-- 평가를 숫자 형태에서 별 형태로 바꾸는 함수
 *  1. 수로 된 평가 정보가 담긴 쿼리 변수를 매개변수로 받는다.
 *  2. 홀수인지 짝수인지 구분한다.
 *  3. 2로 나눈 값을 구하고 새로 작성한 텍스트 안에 꽉찬 별을 그 값만큼 넣어준다.
 *  4. 홀수이면 반 별을 텍스트 안에 넣어준다.
 *  5. 나머지는 빈 별로 채운다.
 *  6. .rate 안에 텍스트를 대체한다.
 */
 
 function setRateInStar(rate){
	  const fullStar = '<i class="fas fa-star"></i>';
	  const halfStar = '<i class="fas fa-star-half-alt"></i>';
	  const emptyStar = '<i class="far fa-star"></i>'

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


/** @function calculateTime(datetime)
 *	-- 매개변수로 datetime을 받아서 'n분 전', 'n시간 전' 등으로 바꿔 반환한다.
 *	1. datetime과 현재시각의 차이를 계산한다.
 *	2. 차이가 1시간 미만이면 'n분 전'
 *	3. 차이가 1시간 이상 24시간 미만이면 'n시간 전'
 *	4. 차이가 1일 이상 7일 미만이면 'n일 전'
 *	5. 차이가 7일 이상이면 'yyyy일 mm월 dd일'
 */
 
 function calculateTime(datetime){
	datetime = new Date(datetime);
	var now = new Date();
	var gap = now - datetime;
	var seconds = Math.floor(gap/1000);
	var result;
	
	if(seconds<60){
		result = '방금 전';
	}else if(seconds>=60 && seconds<3600){
		result = Math.floor(seconds/60)+'분 전';
	}else if(seconds>=3600 && seconds<86400){
		result = Math.floor(seconds/3600)+'시간 전';
	}else if(seconds>=86400 && seconds<604,800){
		result = Math.floor(seconds/86400)+'일 전';
	}else{
		result = datetime.getFullYear()+'년 '+(datetime.getMonth()+1)+'월 '+datetime.getDate()+'일';
	}
	return result;
}