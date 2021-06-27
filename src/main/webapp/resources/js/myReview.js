
/** 초기설정 1. alert
 */
 const message = document.querySelector('.alert');
 if(message.innerText != ""){
	alert(message.innerText);
	history.back();
}

/** @function deleteReview(productNum)
 *	-- 삭제 버튼을 누르면 에이젝스로 delete로 보낸다. 
 *	1. 이벤트 버블링을 막는다.
 */
 function deleteReview(reviewNum){
	event.stopPropagation();
	
	let conf = confirm('정말 삭제하시겠습니까? 삭제된 후기는 복구할 수 없습니다.');
	if(conf){
		$.post(
			'/review/delete', 
			{reviewNum:reviewNum}, 
			function(result){
				if(result>0){
					location.reload();
				}else{
					alert('삭제되지 않았습니다. 다시 시도해주세요.');
				}
		});
	}
}