/**
 * 
 */

// 알림 읽음 처리
function read(notiNum) {
	$.ajax({
		url:"./readChk",
		method:"POST",	
		data:{notiNum : notiNum},
		success:function() {
			console.log("알림 읽기 완료");
			window.location.replace("/notification/list");
		}
	});
}

// 알림 삭제
function notiDelete(notiNum) {
	if(confirm("정말 삭제하시겠습니까?")){			
		$.ajax({
			url:"./notiDelete",
			method:"POST",
			data:{notiNum : notiNum},
			success:function() {
				console.log("알림 삭제 완료");
				window.location.replace("/notification/list");
			}
		});
	}else {
		return false;
	}
		
}