<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<link rel="stylesheet" href="../resources/css/chat.css">
	<title>Chat List</title>
	
<style type="text/css">
/***   헷갈려서 확인하려고 해놨어요  ***/
	.outgoing_msg{
		text-align: right;
	}
	.incoming_msg{
		color: white;
		text-align: left;
	}
	.sent_msg{
		background-color: white;
	}
	
	.received_msg{
		background-color: blue;
	}
</style>
</head>
<body>
<div class="msg-container">
	
		<div class="messaging">
	      <div class="inbox_msg">
	      	<!-- 메세지 목록 영역 -->
	        <div class="inbox_people">
	          <div class="headind_srch">
	            <div class="recent_heading">
	              <h4>Recent</h4>
	            </div>
	          </div>
	          
	          <!-- 메세지 리스트 -->
	          <div class="inbox_chat">
		          <div>메시지 리스트</div>
	          </div>
	  
	        </div>
	        
	        <!-- 메세지 내용 영역 -->
	        <div class="mesgs">
	        <div>내용</div>
	          <!-- 메세지 내용 목록 -->
	          <div class="msg_history" name="contentList">
	          <div>내용</div>
	            <!-- 메세지 내용이 올 자리 -->
	          </div>
	          <div class="send_message">
	          <div>입력</div>
	          </div>
	          <!-- 메세지 입력란이 올자리 -->
	        </div>
	      </div>
	      
	    </div>
	</div>
	
	<script>
	
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
					send_msg += "<div class='type_msg'>";
					send_msg += "	<div class='input_msg_write row'>";
					send_msg += "		<div class='col-11'>";
					send_msg += "			<input type='text' class='write_msg form-control' placeholder='메세지를 입력...' />";
					send_msg += "		</div>";
					send_msg += "		<div class='col-1'>";
					send_msg += "			<button class='msg_send_btn' type='button'><i class='fa fa-paper-plane-o' aria-hidden='true'></i></button>";
					send_msg += "		</div>";
					send_msg += "	</div>";
					send_msg += "</div>";
			          
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
					send_msg += "<div class='type_msg'>";
					send_msg += "	<div class='input_msg_write row'>";
					send_msg += "		<div class='col-11'>";
					send_msg += "			<input type='text' class='write_msg form-control' placeholder='메세지를 입력...' />";
					send_msg += "		</div>";
					send_msg += "		<div class='col-1'>";
					send_msg += "			<button class='msg_send_btn' type='button'><i class='fa fa-paper-plane-o' aria-hidden='true'></i></button>";
					send_msg += "		</div>";
					send_msg += "	</div>";
					send_msg += "</div>";
			          
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
				console.log("메세지 내용 가져오기 성공");
				
				// 메세지 내용을 html에 넣는다
				$('.msg_history').html(data);
				
				// 이 함수로 메세지 내용을 가져올때마다 스크롤를 맨아래로 가게 한다.
				$(".msg_history").scrollTop($(".msg_history")[0].scrollHeight);
			},
			error : function() {
				alert('서버 에러');
			}
		})
		
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
	});
	
	
	</script>
</body>
</html>