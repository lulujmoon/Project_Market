
/** 초기설정 1. selectPage
 */
 selectPage();

/** @function showContent()
 *	-- 공지사항의 content를 보여준다.
 */
 function manageContent(){
	let content = event.currentTarget.nextElementSibling;
	content.classList.toggle('active');
}