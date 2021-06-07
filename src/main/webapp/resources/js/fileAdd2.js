/**
 * 
 */

let count = $("#file").attr("title");

$("#add").click(function(){	
	
	if(count<8) {
	$("#file").append('<div class="inputimg"><input type="file" name="file" class="img" onchange="addFileForm(); setThumbnail(event);"><input type="button" id="del" value="Delete"></div>');
	count++;
	} else {
		alert("최대 7개만 가능");
	}
	
})


$("#del").click(function(){
	$("#file").empty();
})
	
//이벤트 전달(위임)
$("#file").on("click", "#del", function(){
	$(this).parent().remove();
	count--;
});



//파일 입력시 이미지 미리보기
function setThumbnail(event){
	var reader = new FileReader();
	
	reader.onload = function(event){
		var img = document.createElement("img");
		img.setAttribute("src", event.target.result);
		document.querySelector("div.inputimg").appendChild(img);
	};
	
	reader.readAsDataURL(event.target.files[0]);
};

