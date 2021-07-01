/**
 * 
 */

// 알림 읽음 처리
function read(notiNum) {
	$.ajax({
		url:"/notification/readChk",
		method:"POST",	
		data:{notiNum : notiNum},
		success:function() {
			console.log('성공');
			document.location.reload(true);
		}
	});
}

// 알림 삭제
function notiDelete(notiNum) {
	if(confirm("정말 삭제하시겠습니까?")){			
		$.ajax({
			url:"/notification/notiDelete",
			method:"POST",
			data:{notiNum : notiNum},
			success:function() {
				console.log("알림 삭제 완료");
				window.location.replace("/notification/list");
			}
		});
	}else {
		return false;
	}
}

/** @function acceptPrice()
 *	--가격제안 수락 확인 후 페이지로 이동한다.
 */
function acceptPrice(counterpart, user, productName, productNum){
	let conf = confirm('가격 제안을 수락하시겠어요?');
	if(conf){
		location.href = '/chat/chatSendInList?room=0&otherUser='+counterpart+'&content=※'+user+'님이 '+productName+' 상품의 가격제안을 수락하셨습니다.&productNum='+productNum;
	}
}

/** 초기설정 . 알림 버튼
 */
const btnNoti = document.querySelector('.btn-noti');
const notiContainer = document.querySelector('.noti-container');
btnNoti.addEventListener('click', ()=>{
	$.get(
		'/notification/list',
		function(result){
			notiContainer.innerHTML = result;
			
			const notiDates = document.querySelectorAll('.noti__date');
 			for(let notiDate of notiDates){
				notiDate.innerText = calculateTime(notiDate.innerText);
			}
			setClick();
		});	
		notiContainer.classList.toggle('active');
});


/** 클릭 이벤트 추가 -> 읽음 처리, 가격 제안 수락
 */
function setClick(){
	const notiCards = document.querySelectorAll('.noti__card');
	for(let notiCard of notiCards){
		let notiNum = Number(notiCard.querySelector('.notiNum'));
		let counterpart = notiCard.querySelector('.counterpart');
		let user = notiCard.querySelector('.user');
		let product = notiCard.querySelector('.product');
		let productNum = notiCard.querySelector('.productNum');
		notiCard.addEventListener('click', ()=>{
			read(notiNum);
			acceptPrice(counterpart, user, product, productNum);
		});
	}	
}