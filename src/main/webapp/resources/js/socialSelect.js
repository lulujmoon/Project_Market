/**
 * 
 */

$(document).ready(function() {

	let goodval = ${good}

	if (goodval>0) {
		console.log("good : "+goodval);
		$("#good").prop("src", "/resources/images/빨강.png");
		$(".good").prop("name", goodval);
	} else {
		console.log("good : "+goodval);
		$("#good").prop("src", "/resources/images/검정.png");
		$(".good").prop("name", goodval);
	}

	$(".good").on("click", function() {
			let that = $(".good");
			let sendData = {'socialNum' : '${socialVO.socialNum}', 'good' : that.prop('name')};
		$.ajax({
			url : '/social/good',
			type : 'POST',
			data : sendData,
			success : function(data) {
				that.prop('name', data);
				if (data == 1) {
					$("good").prop("src", "/resources/images/빨강.png");
				} else {
					$("good").prop("src", "/resources/images/검정.png");
				}

			}
		})
	})
})
