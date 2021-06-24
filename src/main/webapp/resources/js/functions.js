
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
	}else if(seconds>=86400 && seconds<604800){
		result = Math.floor(seconds/86400)+'일 전';
	}else{
		result = datetime.getFullYear()+'년 '+(datetime.getMonth()+1)+'월 '+datetime.getDate()+'일';
	}
	return result;
}

/** @function setJoinDate(joinDate)
 *	-- yyyy-mm-dd 형식의 데이터를 받아 yyyy년 mm월 dd일 형식으로 바꿔준다.
 */
 function setJoinDate(joinDate){
  var dateArray = joinDate.split('-'); 
  joinDate = dateArray[0]+'년 '+dateArray[1]+'월 '+dateArray[2]+'일 가입';

  return joinDate;
}

/** @function selectPage() 
 *	-- 현재 페이지에 selected 클래스를 추가한다.
 */
 function selectPage(){
	 const pageVal = document.querySelector('.page-value');
	 let selectedPage = document.querySelector('.code_'+pageVal.innerText);
	 selectedPage.classList.add('selected');	
}

/** @function carousel
 * -- 이미지를 캐러셀 형태로 만든다.
 *	0. 슬라이드, 이미지, 버튼 변수 선언. 첫번째 서클 채우기
 *  1. 초기 상태
 *		1-1. carouselImages의 사이즈를 구해 carousel-slide의 위치를 그만큼 왼쪽으로 이동한다.
 *		1-2. 리사이즈되면 사이즈를 다시 구하고 다시 이동한다.
 *	2. @function goPrev() 
 *		-- 이전 버튼 클릭 시
 *		2-1. counter를 1 감소시킨다.
 *		2-2. 슬라이드의 위치를 transition과 transform을 이용해 이동시킨다.
 *		2-3. counter가 0 이하면 return한다.
 *	3. @function goNext() 
 *		-- 다음 버튼 클릭 시
 *		3-1. counter를 1 증가시킨다.
 *		3-2. 슬라이드의 위치를 transition과 transform을 이용해 이동시킨다.
 *		3-3. counter가 carouselImages.length-1 이상이면 return한다.
 *	4. counterSlide transitionend 이벤트
 *		-- 처음/마지막 이미지에 갔을 때 체크, 서클 변경
 *		4-1. 슬라이드에 transitioned 이벤트가 발생했을 때 익명함수를 실행한다.
 *		4-2. 현재 counter에 해당하는 이미지가 lastClone이면
 *			4-2-1. 카운터를 carouselImages.length-2로 바꾼다.
 *			4-2-2. transition을 없앤다.
 *			4-2-3. 마지막 이미지로 transform 시킨다.
 *		4-3. 현재 counter에 해당하는 이미지가 lastClone이면
 *			4-3-1. 카운터를 carouselImages.length-counter로 바꾼다.
 *			4-3-2. transition을 없앤다.
 *			4-3-3. 처음 이미지로 transform 시킨다.
 *		4-4. counter+1과 클래스가 일치하는 서클을 색이 있는 서클로 교체하고, 나머지는 색이 없는 서클로 교체한다.
 *	5. @function autoSlide()
 *		-- 캐러셀을 3초마다 넘긴다.
 *		5-1. goNext()와 checkCounter()을 실행한다.
 *		5-2. 함수 밖에서 setInterval을 설정한다.
 *	6. -- 서클 버튼을 누르면 해당 번호로 이동한다. 	
 */
 function setCarousel(){
	 const carouselSlide = document.querySelector('.carousel-slide');
	 const carouselImages = document.querySelectorAll('.carousel-images');
	 const prevBtn = document.querySelector('#prev-btn');
	 const nextBtn = document.querySelector('#next-btn');
	 const circles = document.querySelectorAll('.circle');
	 
	 let counter = 1;
	 let size;
	 
	 if(circles.length != 0){
		 circles[0].classList.add('selected');		
	}
	 
	 size = carouselImages[0].clientWidth;
	 carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';
	 
	 window.addEventListener('resize', ()=>{
	 	 size = carouselImages[0].clientWidth;
		 carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';		
	});
	 
	 function goPrev(){
		if(counter<=0){
			return;
		}
		counter--;
		carouselSlide.style.transition = 'transform 0.4s ease-in-out';
		carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';
	}
	
	 function goNext(){
		if(counter>=carouselImages.length-1){
			return;
		}
		counter++;
		carouselSlide.style.transition = 'transform 0.4s ease-in-out';
		carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';
	}	
	
	 prevBtn.addEventListener('click', goPrev);
	 nextBtn.addEventListener('click', goNext);

		carouselSlide.addEventListener('transitionend', ()=>{
			if(carouselImages[counter].id === 'last-clone'){
				counter = carouselImages.length-2;
				carouselSlide.style.transition = 'none';
				carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';
			}else if(carouselImages[counter].id === 'first-clone'){
				counter = carouselImages.length-counter;
				carouselSlide.style.transition = 'none';
				carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';				
			}
			
			if(circles.length != 0){
				for(let i=0;i<circles.length;i++){
					if(circles[i].id === 'circle_'+(counter-1)){
						circles[i].classList.add('selected');
					}else{
						circles[i].classList.remove('selected');			
					}
				}				
			}
		});
	
	const start = setInterval(goNext, 4500);
	
	if(circles.length != 0){
		for(let i=0;i<circles.length;i++){
			circles[i].addEventListener('click', ()=>{
				counter = i+1;
				carouselSlide.style.transition = 'transform 0.4s ease-in-out';
				carouselSlide.style.transform = 'translateX('+(-size*counter)+'px)';
			});
		}		
	}
}