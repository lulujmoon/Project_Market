<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<title>CHAT</title>
</head>
<body>

<script type="text/javascript">
    var stompClient = null;

    // How to Thymeleaf send data to JavaScript

    /*<![CDATA[*/
    var projectId = /*[[${projectId}]]*/ 'default';
    /*]]>*/

    console.log('project info : ' + projectId); 

    function connect() { // 생성된 소켓과 연결
        var socket = new SockJS('/teamhash-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {

            console.log('Connected: ' + frame);

                 
            stompClient.subscribe('/receive/chat/'+projectId, function(chat){ // 구독 기능을 통해 해당 주소에 메세지가 오면 showChat 함수 호출
                showChat(JSON.parse(chat.body));
            });
        });
    }
    function disconnect() { // 연결 종료
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }


    function sendChat(){ // 메세지 전송 index.html의 text box에 입력된 값을 읽어온다.
        stompClient.send("/send/chat/"+projectId, {}, 
                        JSON.stringify({'sender':$("#sender").val(), 
                                        'message': $("#chatMessage").val()}));
    }

    function showChat(chat) { // 수신한 메세지를 출력
                            // DB에 저장된 값을 출력해주는 Thymeleaf와 형태를 맞추기 위해 
                            // 적절한 배치 필요

        var sendDateTime = chat.sendDateTime.split("T"); // 시간과 분 출력 
        sendDateTime = sendDateTime[1].split(":");
        $("#chattings").append("<tr><td><span>" + chat.sender +
        "</span></td><td><span>" + chat.message + 
        "</span></td><td><span>" + sendDateTime[0] + 
        ":" + sendDateTime[1] + "</span></td></tr>");
    }


    $(function () { // 함수 호출 연결
        $("form").on('submit', function (e) {
            e.preventDefault();
        });
        $( "#chatSend").click(function(){ sendChat();});
    });

    //창을 열고 닫을 때 연결과 해제 
    window.onload = function (){
        connect();
    }

    window.BeforeUnloadEvent = function(){
        disconnect();
    }

</script>

</body>
</html>