
/** 초기설정 1. selectPage
 */
 const pageContainer = document.querySelector('.page-container');
 selectPage(pageContainer);

/** @function showContent()
 *	-- 공지사항의 content를 보여주고 숨긴다.
 */
 function manageContent(){
	let content = event.currentTarget.nextElementSibling;
	content.classList.toggle('active');
}

/** @function deleteNotice(noticeNum)
 *	-- 공지글을 삭제한다.
 */
 function deleteNotice(noticeNum){
	let conf = confirm('정말 삭제하시겠습니까? 삭제된 게시물은 복구할 수 없습니다.');
	if(conf){
		const deleteForm = document.createElement('form');
		deleteForm.action = './delete';
		deleteForm.method = 'post';
		
		const inputNum = document.createElement('input');
		inputNum.type = 'hidden';
		inputNum.name = 'noticeNum';
		inputNum.value = noticeNum;
		
		deleteForm.appendChild(inputNum);
		document.body.appendChild(deleteForm);
		
		deleteForm.submit();
	}
}