<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<footer class="footer">
	<div class="footer__contents">
		<div class="footer__logo">
			<i class="fas fa-lemon"></i>
		</div>
		<div>Favicon made by <a href="https://www.flaticon.com/authors/vectors-market" title="Vectors Market">Vectors Market</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
	</div>
</footer>

<script type="text/javascript">
	var socket = null;
	$(document).ready(function(){
		connectWS();
	});
	
	function connectWS(){
		console.log("connect")
		var ws = new WebSocket("ws://" + location.host + "/chating");
		socket = ws;
		
		ws.onopen = function() {
			console.log('Info: connection opened.');
		};
		

	    ws.onmessage = function (event) {
	        console.log("ReceiveMessage:", event.data+'\n');
	        let $socketAlert = $('div#socketAlert');
	        $socketAlert.html(event.data);
	        $socketAlert.css('display', 'block');
	        
	        setTimeout( function() {
	        	$socketAlert.css('display', 'none');
	        }, 3000);
	    };
	    ws.onclose = function (event) { 
	        console.log('Info: connection closed.');
	        //setTimeout( function(){ connect(); }, 1000); // retry connection!!
	    };
	    ws.onerror = function (err) { console.log('Error:', err); };
	}
</script>