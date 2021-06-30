/**
 * 
 */

const btnGood = document.querySelector('.btn-good');
let goodval = Number(document.querySelector('.goodval').value);
let socialNum = Number(document.querySelector('.socialNum').value);

if(btnGood != null) {
	$(document).ready(function() {
		
		if(goodval>0) {
			btnGood.innerHTML = '<i class="fas fa-thumbs-up"></i>';
		} else {
			btnGood.innerHTML = '<i class="far fa-thumbs-up"></i>';
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
						$(".goodval").html('<i class="fas fa-thumbs-up"></i>');
						good_reload();
					} else {
						$(".goodval").html('<i class="far fa-thumbs-up"></i>');
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

/** 초기설정 1. 날짜 표시
 */
 const postDate = document.querySelector('.post__date');
 postDate.innerText = calculateTime(postDate.innerText);
