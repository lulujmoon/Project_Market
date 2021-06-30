/**
 * 
 */

const btnGood = document.querySelector('.btn-good');
let goodval = Number(document.querySelector('.goodval').value);
let socialNum = Number(document.querySelector('.socialNum').value);

if(btnGood != null) {
	$(document).ready(function() {
		
		if(goodval>0) {
			btnGood.innerHTML = '<i class="fas fa-heart"></i> 공감하기';
		} else {
			btnGood.innerHTML = '<i class="far fa-heart"></i> 공감하기';
		}
		
		btnGood.addEventListener('click', ()=>{
			let sendData = {'socialNum' : socialNum, 'good' : goodval};
			$.ajax({
				url : '/social/good',
				type : 'POST',
				data : sendData,
				success : function(data) {
					$(".goodval").val(data);
					if(data == 1) {
						$(".goodval").html('<i class="fas fa-heart"></i>');
						good_reload();
					} else {
						$(".goodval").html('<i class="far fa-heart"></i>');
						good_reload();
					}
				}
			});
		});
	});
}

function good_reload() {
	$("#good").load(window.location.href='/social/select?socialNum='+socialNum);
}

/** @function openSocialReport(socialNum)
 *	-- socialNum을 받아 신고 페이지를 팝업으로 띄운다.
 */
function openSocialReport(socialNum){
	window.open(
		"/report/socialReport?socialNum="+socialNum, 
		'', 
		"width=500,height=600,resizable,scrollbars=yes,left=1300,top=150");
}

/** @function goStore(code)
 *	--멤버 코드를 받아 상점 페이지로 이동한다.
 */
 function goStore(code){
	location.href = '../store/'+code+'/products';
}

/** @function manageReply(code, commentNum)
 *	-- 답글 영역을 생성하고 삭제한다.
 */
 function manageReply(code, commentNum){
	const target = document.querySelector('.wrapper_'+code);
	
	if(target.lastElementChild.classList.contains('reply-form')){
		target.removeChild(target.lastElementChild);
	}else{
		const replyForm = document.createElement('form');
		replyForm.action = '../comment/reply';
		replyForm.method = 'post';
		replyForm.classList.add('reply-form');
		const replyContent = document.createElement('textarea');
		const replyNum = document.createElement('input');
		const replyBtn = document.createElement('button');
		replyContent.name = 'commentContent';
		replyContent.classList.add('comment__content');
		replyNum.type = 'hidden';
		replyNum.name = 'commentNum';
		replyNum.value = commentNum;
		replyBtn.innerText = '답글';
		replyBtn.classList.add('btn-submit');
		
		replyForm.appendChild(replyContent);
		replyForm.appendChild(replyNum);
		replyForm.appendChild(replyBtn);
		
		target.appendChild(replyForm);		
	}
}

/** 초기설정 1. 날짜 표시
 */
 const postDate = document.querySelector('.post__date');
 const itemDates = document.querySelectorAll('.item__date');
 postDate.innerText = calculateTime(postDate.innerText);
 for(let itemDate of itemDates){
	itemDate.innerText = calculateTime(itemDate.innerText);
}
