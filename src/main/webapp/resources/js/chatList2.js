
// 가장 처음 메세지 리스트를 가져온다.
const FirstMessageList = function(){
	$.ajax({
		url:"chatAjaxList",
		method:"get",
		data:{
		},
		success:function(data){
			console.log("메세지 리스트 리로드 성공");
			
			$('.inbox_chat').html(data);
			
			// 메세지 리스트중 하나를 클릭했을 때
			$('.chat_list').on('click', function(){

				let otherUser = $(this).attr('otherUser');				
				let check = confirm(otherUser+"님에게 판매하시겠습니까?")
				
				if(check==true){
				location.href="../reservation/insert?buyer="+otherUser+"&&productNum="+productNum+"&&locationCode="+locationCode
				}
			});
			
		}
	})
};

   
const productNum = document.querySelector('.product-num').innerText;
const locationCode = document.querySelector('.location-code').innerText;



$(document).ready(function(){
	// 메세지 리스트 리로드
	FirstMessageList();
	const sendTimes = document.querySelectorAll('.send-time');
	for(let time of sendTimes){
		console.log(time);
		time.innerText = calculateTime(time.innerText);
	}
});
