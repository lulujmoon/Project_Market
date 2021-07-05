
/** 초기설정 1. content에서 사진, 문단 생략하기
 *	1. 정규식을 이용해 <img>와 <p>, </p> 부분을 삭제한다.
 */
 const socialContents = document.querySelectorAll('.scl__content');
 const regStartP = /<p>/gm;
 const regEndP = /<\/p>/gm;
 for(let content of socialContents){
	let replaced = content.innerHTML.replace(regStartP, '');
	replaced = replaced.replace(regEndP, '&nbsp;');
	content.innerHTML = replaced;
}

/** 초기설정 2. 날짜 표기
 */
 const socialDates = document.querySelectorAll('.scl__date');
 for(let date of socialDates){
	let result = calculateTime(date.innerText);
	date.innerText = result;
}

/** 초기설정 3. 정렬 기준을 찾아서 class에 selected를 추가해준다. 
 	*  1. hidden 클래스가 있는 div는 순서대로 카테고리코드, 마이로케이션, 페이지의 값을 가지고 있다.
 	*  2. 순서대로 값과 클래스명이 일치하는 부분을 찾아서 selected 클래스를 추가해준다.
  */
 const hiddens = document.querySelectorAll('.hidden');
 const types = ['category', 'myLocation', 'page'];
 for(let i=0;i<types.length;i++){
	 let container = document.querySelector('.list-'+types[i]);
	 let selectedContent = container.querySelector('.code_'+hiddens[i].innerText);
	 selectedContent.classList.add('selected');	
}

/** @function goSocialSelect(socialNum)
 *	-- 소셜 상세 페이지로 이동한다.
 */
function goSocialSelect(socialNum){
	location.href = '/social/select/'+socialNum;
}

/** @function goSocialHome()
 *	-- 우리동네의 초기 페이지로 이동한다.
 */
 function goSocialHome(){
	location.href = './list';
}