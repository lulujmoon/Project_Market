
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
				//alert('room : '+ $(this).attr('room'));
				
				let room = $(this).attr('room');
				let otherUser = $(this).attr('otherUser');
				
				let send_msg = "";
				send_msg += "	<input type='text' class='write_msg' placeholder='메세지를 입력하세요.' />";
				send_msg += "	<button class='btn-send' type='button'><i class='fa fa-paper-plane-o' aria-hidden='true'></i></button>";
		          
				// 메세지 입력, 전송 칸을 보인다.
				$('.send_message').html(send_msg);
				
				// 메세지 전송버튼을 눌렀을 때
				$('.btn-send').on('click',function(){
					
					// 메세지 전송 함수 호출
					SendMessage(room, otherUser);
					
				});
				
				// 메세지 내용을 불러오는 함수 호출
				MessageContentList(room);
				
				// 선택한 채팅방에 selected 클래스 추가, 다른 채팅방에서는 삭제
				$(this).addClass('selected');
				$(this).siblings().removeClass('selected');
			});
			
		}
	})
};

// 메세지 리스트를 다시 가져온다.
const MessageList = function(){
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
				//alert('room : '+ $(this).attr('room'));
				
				let room = $(this).attr('room');
				let otherUser = $(this).attr('otherUser');
				
				let send_msg = "";
				send_msg += "	<input type='text' class='write_msg' placeholder='메세지를 입력하세요.' />";
				send_msg += "	<button class='btn-send' type='button'><i class='fa fa-paper-plane-o' aria-hidden='true'></i></button>";
		          
				// 메세지 입력, 전송 칸을 보인다.
				$('.send_message').html(send_msg);
				
				// 메세지 전송버튼을 눌렀을 때
				$('.msg_send_btn').on('click',function(){
					
					// 메세지 전송 함수 호출
					SendMessage(room, otherUser);
					
				});
				
				// 메세지 내용을 불러오는 함수 호출
				MessageContentList(room);
				
			});
			
		}
	})
};
   
const productNum = document.querySelector('.product-num').innerText;
const locationCode = document.querySelector('.location-code').innerText;

// 메세지 내용을 가져온다.
// 읽지 않은 메세지들을 읽음으로 바꾼다.
const MessageContentList = function(room) {
	
	$.ajax({
		url:"chatContentList",
		
		method:"GET",
		data:{
			room : room,
		},
		success:function(data){
			let history = document.querySelector('.msg_history');
			let hiddenMessage = document.querySelector('.hidden-message');
			console.log("메세지 내용 가져오기 성공");
			hiddenMessage.innerHTML = data.trim();
			// 메세지 내용을 html에 넣는다
			if(history.innerHTML != hiddenMessage.innerHTML){
				history.innerHTML = hiddenMessage.innerHTML;
			}
			// 이 함수로 메세지 내용을 가져올때마다 스크롤를 맨아래로 가게 한다.
			$(".msg_history").scrollTop($(".msg_history")[0].scrollHeight);
		}
	});
	
	$('.unread'+room).empty();

};


// 메세지를 전송하는 함수
const SendMessage = function(room, otherUser){
	
	let content = $('.write_msg').val();
	
	content = content.trim();
	
	if(content == ""){
		alert("메세지를 입력하세요!");
	}else{
		$.ajax({
			url:"chatSendInList",
			method:"GET",
			data:{
				room : room,
				otherUser: otherUser,
				content: content
			},
			success:function(data){
				console.log("메세지 전송 성공");
				
				// 메세지 입력칸 비우기
				$('.write_msg').val("");
				
				// 메세지 내용  리로드
				MessageContentList(room);
				
				// 메세지 리스트 리로드
				MessageList();
				
			},
			error : function() {
				alert('서버 에러');
			}
		});
	}
	
};

$(document).ready(function(){
	// 메세지 리스트 리로드
	FirstMessageList();
	const sendTimes = document.querySelectorAll('.send-time');
	for(let time of sendTimes){
		console.log(time);
		time.innerText = calculateTime(time.innerText);
	}
});

/** 기능. 채팅 새로 가져오기
 *	-- 3초마다 현재 채팅방의 채팅을 새로 가져온다.
 */
 function refreshChat(){
	const currentRoom = document.querySelector('.selected');
	if(currentRoom != null){
		let roomNum = $(currentRoom).attr("room");
		MessageContentList(roomNum);
		
		const sendUser = document.querySelector('.rcv__sendUser');
		const otherUsers = document.querySelector('.other-user');
		for(let other of otherUsers){
			if(sendUser.innerText == other.innerText){
				sendUser.classList.add('selected');
			}
		}
	}
}

	setInterval(refreshChat, 3000);
