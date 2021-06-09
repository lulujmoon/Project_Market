/**
 * 
 */
 
/* 찜 */
const btnHeart = document.querySelector('.btn-heart');
let heartValue = Number(document.querySelector('.heartValue').value);
let productNum = Number(document.querySelector('.productNum').value);

$(document).ready(function(){
	
	if(heartValue>0) {
		btnHeart.innerHTML = '<i class="fas fa-heart"></i>';
	} else {
		btnHeart.innerHTML = '<i class="far fa-heart"></i>';
	}
	
	btnHeart.addEventListener('click', ()=>{
		let sendData = {'productNum' : productNum, 'heart' : that.prop('name')};
		$.ajax({
			url : '/product/heart',
			type : 'POST',
			data : sendData,
			success : function(data) {
				that.prop('name', data);
				if(data==1) {
					$("heart").prop("src","/resources/images/빨강.png");
					heart_reload();
				} else {
					$("heart").prop("src","/resources/images/검정.png");
					heart_reload();
				}
			}
		});
	});
});
		


function heart_reload() {
	$("#heart").load(window.location.href='/product/select?productNum='+productNum);
}


function confirm() {
	if(heartval==1) {
		if(confirm('찜하기 취소')== false) {return false;};
	} else {
		if(confirm('찜하기')== false) {return false;};
	}

}


/** 초기설정 1. 가격 표시 방법
 *	number.toLocaleString(locale, option) 이용
 */
 setPrice('.top__price');
 
/** 초기설정 2. 별점 표시
 */
 const rates = document.querySelectorAll('.rate');
 for(rate of rates){
	setRateInStar(rate);
}

/** 초기설정 3. 가격제안 표시
 */
 const top__nego = document.querySelector('.top__nego');
 if(top__nego.innerText == 'true'){
	top__nego.innerHTML = '<i class="fas fa-comment-dollar"></i> 가격 제안 가능';
}else{
	top__nego.innerHTML = '<i class="fas fa-comment-dollar"></i> 가격 제안 불가';
}

/** 초기설정 4. 판매일자 표시
 *	<i class="fas fa-clock"></i> 
 */
 const top__productDate = document.querySelector('.top__productDate');
 var datetime = top__productDate.innerText;
  top__productDate.innerHTML = '<i class="fas fa-clock"></i> '+calculateTime(datetime);	