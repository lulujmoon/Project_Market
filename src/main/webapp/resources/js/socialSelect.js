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
	document.location.reload(true);
}

/** @function openSocialReport(socialNum)
 *	-- socialNum을 받아 신고 페이지를 팝업으로 띄운다.
 */
function openSocialReport(socialNum){
	window.open(
		"/report/socialReport/"+socialNum, 
		'', 
		"width=500,height=600,resizable,scrollbars=yes,left=1300,top=150");
}

/** @function goStore(code)
 *	--멤버 코드를 받아 상점 페이지로 이동한다.
 */
 function goStore(code){
	location.href = '/store/'+code+'/products';
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
		replyForm.action = '/comment/reply';
		replyForm.method = 'post';
		replyForm.classList.add('reply-form');
		const replyContent = document.createElement('textarea');
		const replyNum = document.createElement('input');
		const replyBtn = document.createElement('button');
		replyContent.name = 'commentContent';
		replyContent.required = 'required';
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

/** @function manageCommentEdit(commentNum)
 *	-- comment 수정 폼을 생성하고 삭제한다.
 */
 function manageCommentEdit(commentNum){
	const target = event.currentTarget.parentNode.parentNode.nextElementSibling;
	
	const editForm = document.createElement('form');
	editForm.action = '/comment/update';
	editForm.method = 'post';
	editForm.classList.add('edit-form');
	const editContent = document.createElement('textarea');
	editContent.name = 'commentContent';
	editContent.value = target.innerText;
	editContent.classList.add('comment__content');
	editContent.style.width = '898px';
	const editNum = document.createElement('input');
	editNum.type = 'hidden';
	editNum.name = 'commentNum';
	editNum.value = commentNum;	
	const editBtn = document.createElement('button');
	editBtn.innerText = '수정';
	editBtn.classList.add('btn-submit');
	editBtn.style.width = '920px';
	
	editForm.appendChild(editContent);
	editForm.appendChild(editNum);
	editForm.appendChild(editBtn);
	
	target.innerHTML = "";
	target.appendChild(editForm);
}

/** 초기설정 1. 날짜 표시
 */
 const postDate = document.querySelector('.post__date');
 const itemDates = document.querySelectorAll('.item__date');
 postDate.innerText = calculateTime(postDate.innerText);
 for(let itemDate of itemDates){
	itemDate.innerText = calculateTime(itemDate.innerText);
}

/** @function deleteSocial(socialNum) 
 *	글 삭제
 */
 function deleteSocial(socialNum){
	let conf = confirm('삭제한 게시물은 복구할 수 없습니다. 그래도 삭제하시겠습니까?');
	if(conf){
		$.post(
			'./delete',
			{socialNum: socialNum},
			()=>{
				alert('삭제되었습니다.');
				location.href = './list';
			}
		);
	}
}

/** @function deleteComment(commentNum)
 *	-- 댓글 삭제
 */
 function deleteComment(commentNum){
	let conf = confirm('삭제한 댓글은 복구할 수 없습니다. 그래도 삭제하시겠습니까?');
	if(conf){
		$.post(
			'/comment/delete',
			{commentNum: commentNum},
			()=>{
				location.reload();
			}
		)
	}
}