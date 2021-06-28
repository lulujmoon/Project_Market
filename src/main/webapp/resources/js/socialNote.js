


/** @function openMemberInfo()
 *	-- 팝업으로 memberInfo창을 연다.
 *	1. 작성하던 내용을 세션 스토리지에 저장한다.
 */
 function openMemberInfo(){
	let title = document.querySelector('.title').value;
	let content = document.querySelector('.content').value;
	
	sessionStorage.setItem('title', title);
	sessionStorage.setItem('content', content);
	
	let pop = window.open('/member/info', '', 'width=1100,height=700, top=200, left=350, resizable');
	pop.addEventListener('beforeunload', ()=>{
		location.reload();
	});
		
}


/** 초기설정 1. 세션에 저장된 정보를 불러온다.
 *	1. 세션에서 정보를 꺼내 넣어준다.
 *	2. 세션 스토리지를 비운다.
 */
document.querySelector('.title').value = sessionStorage.getItem('title');
if(sessionStorage.getItem('content')!=null){
	$('#socialContent').summernote('reset');
	$('#socialContent').summernote('pasteHTML', sessionStorage.getItem('content'));	
}
sessionStorage.removeItem('title');
sessionStorage.removeItem('content');