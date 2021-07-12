

/** 초기설정 1. 가격 표시 방법
 *	number.toLocaleString(locale, option) 이용
 */
 const topPrice = document.querySelector('.top__price');
 const topCategory = document.querySelector('.top__category');
 setPrice(topPrice);
 if(topPrice.innerText=='0 원' && topCategory.innerText != '삽니다'){
	topPrice.innerText = '무료나눔';
}
 
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
  
/** 초기설정 5. 판매자 가입일 표시
 */
 const seller__joinDate = document.querySelector('.seller__joinDate');
 joinDate = seller__joinDate.innerText;
 joinDate = setJoinDate(joinDate);
 seller__joinDate.innerText = joinDate;

/** 초기설정 6. 캐러셀
 */
 window.addEventListener('load', ()=>{
	 setCarousel();	
});

/** 초기설정 7. 판매상태
 */
 const top__status = document.querySelector('.top__status');
 const status = top__status.innerText;
 if(status == '판매 중'){
	top__status.style.backgroundColor = 'green';
}else if(status == '판매완료'){
	top__status.style.backgroundColor = '#b4b4b4';
}else if(status == '예약 중'){
	top__status.style.backgroundColor = '#f7bd11';
}




/** 기능 1. 찜
 */
const btnHeart = document.querySelector('.btn-heart');
let heartValue = Number(document.querySelector('.heartValue').value);
let productNum = Number(document.querySelector('.productNum').value);

if(btnHeart!=null){
	$(document).ready(function(){
		
		if(heartValue>0) {
			btnHeart.innerHTML = '<i class="fas fa-heart"></i>';
		} else {
			btnHeart.innerHTML = '<i class="far fa-heart"></i>';
		}
		
		btnHeart.addEventListener('click', ()=>{
			let sendData = {'productNum' : productNum, 'heart' : heartValue};
			$.ajax({
				url : '/product/heart',
				type : 'POST',
				data : sendData,
				success : function(data) {
					$(".heartValue").val(data);
					if(data==1) {
						$(".heartValue").html('<i class="fas fa-heart"></i>');
						heart_reload();
					} else {
						$(".heartValue").html('<i class="far fa-heart"></i>');
						heart_reload();
					}
				}
			});
		});
	});
}
		
function heart_reload() {
	document.location.reload(true);
}

function submit() {
	document.status.submit();
}
 
/** @function goSellerPage(sellerCode)
 *	-- 멤버코드를 받아 store/products 페이지로 이동한다.
 */
 function goSellerPage(sellerCode){
	location.href = '/store/'+sellerCode+'/products/';
}

/** @function openReport(productNum)
 *	-- productNum을 받아 신고 페이지를 팝업으로 띄운다.
 */
function openReport(productNum){
	window.open(
		"/report/report?productNum="+productNum, 
		'', 
		"width=500,height=600,resizable,scrollbars=yes,left=1300,top=150");
}

$(document).ready(function(){
	var status = $("#productStatus").val();
	$("#status").val(status);

	});

/** @function manageStatus()
 *	-- 상태변경 옵션을 보여주거나 숨긴다.
 */
 function manageStatus(){
	const statusSelect = document.querySelector('.status-select');
	statusSelect.classList.toggle('active');
}

/** @function submitStatus()
 *	-- status-form을 제출한다.
 */
 function submitStatus(){
	const inputStatus = document.querySelector('.input-status');
	const btnSubmit = document.querySelector('.btn-status-submit');
	let val = event.currentTarget.innerText;
	inputStatus.value = val;
	btnSubmit.click();
}

function deleteProduct(productNum) {
	let con = confirm("삭제하시겠습니까?");
	if(con) {
        const deleteForm = document.createElement('form');
		deleteForm.action = './delete';
		deleteForm.method = 'post';
		
		const inputNum = document.createElement('input');
		inputNum.type = 'hidden';
		inputNum.name = 'productNum';
		inputNum.value = productNum;
		
		deleteForm.appendChild(inputNum);
		document.body.appendChild(deleteForm);
		
		deleteForm.submit();
    
        }
	}
	
/** @function suggestPrice(productNum, counterpart)
 *	-- 가격 제안 팝업을 띄운다.
 */
function suggestPrice(productNum, counterpart){
	window.open(
		'/notification/nego?productNum='+productNum+'&notiRecvUser='+counterpart,
		'',
		'width=400, height=100, top=400, left=600, resizable'
	);
}

