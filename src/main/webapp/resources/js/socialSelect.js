/**
 * 
 */

const btnGood = document.querySelector('.btn-good');
let goodval = Number(document.querySelector('.goodval').value);
let socialNum = Number(document.querySelector('.socialNum').value);

$(document).ready(function() {

	if(goodval>0) {
		btnGood.innerHTML = '<i class="fas fa-heart"></i>';
	} else {
		btnGood.innerHTML = '<i class="far fa-heart"></i>';
	}

	btnGood.addEventListener('click', ()=>{
		let sendData = {'socialNum' : socialNum, 'good' : goodval};
		$.ajax({
			url : '/social/good',
			type : 'POST',
			data : sendData,
			success : function(data) {
				$(".goodval").val(data);
				if(data==1) {
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

function good_reload() {
	$("#good").load(window.location.href='/social/select/'+socialNum);
}
