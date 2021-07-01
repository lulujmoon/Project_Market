


/** @function openMemberInfo()
 *	-- 팝업으로 memberInfo창을 연다.
 *	1. 작성하던 내용을 세션 스토리지에 저장한다.
 */
 function openMemberInfo(){
	let pop = window.open('/member/info', '', 'width=1100,height=700, top=200, left=350, resizable');
	pop.addEventListener('beforeunload', ()=>{
		location.reload();
	});		
}