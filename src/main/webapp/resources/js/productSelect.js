/**
 * 
 */

/* 찜 */
let heartval = $("#heartNumber").attr("title");
let productNum = $("#productNum").attr("title");
$(document).ready(function(){
	
	
	if(heartval>0) {
		console.log("heart : "+heartval);
		$("#heart").prop("src", "/resources/images/빨강.png");
		$(".heart").attr("name", heartval);
		$(".heart").attr("onclick", "if ( confirm('찜취소')==false ){return false;}");
		
	} else {
		console.log("heart : "+heartval);
		$("#heart").prop("src", "/resources/images/검정.png");
		$(".heart").attr("name", heartval);
		$(".heart").attr("onclick", "if ( confirm('찜하기')==false ){return false;}");
	}
	
	$(".heart").on("click", function(){
		
		let that = $(".heart");
		
		let sendData = {'productNum' : productNum, 'heart' : that.prop('name')};
		$.ajax({
			url : '/product/heart',
			type : 'POST',
			async: false,
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