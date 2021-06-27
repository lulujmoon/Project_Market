
/** 초기설정 1. content에서 사진, 문단 생략하기
 *	1. 정규식을 이용해 <img>와 <p>, </p> 부분을 삭제한다.
 */
 const socialContents = document.querySelectorAll('.scl__content');
 const regContent = /(<img)[^>]*>/gm;
 const regStartP = /<p>/gm;
 const regEndP = /<\/p>/gm;
 for(let content of socialContents){
	let replaced = content.innerHTML.replace(regContent, '');
	replaced = replaced.replace(regStartP, '');
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

/** @function goSelect(socialNum)
 *	socialNum이 일치하는 글의 셀렉트 페이지로 이동한다.
 */
 function goSelect(socialNum){
	location.href = './select?socialNum='+socialNum;
}