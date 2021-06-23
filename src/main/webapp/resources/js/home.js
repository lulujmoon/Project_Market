
 
 /* 캐러셀 */
 window.addEventListener('load', ()=>{
	 setCarousel();
});

 function goSelect(productNum){
	location.href = '/product/select/'+productNum;
}

$(document).ready(function(){
		
	let category = $("#category").val();
	let price = document.getElementById('price').innerText;
	
	if(category != 14) {
		if(price == 0) {
			$("#price").text("무료나눔");
		}
	} 
});