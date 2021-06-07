/**
 * 
 */

var input = document.querySelector('.img');
var preview = document.querySelector('.preview');

input.style.opacity = 0;



//초기 개수
let count = 1;

function addFileForm(){
	var valimg = $(".img").val();
	if(count < 8){
		if(valimg != null){
			$("#imgform").append(
				'<div class="inputimg"><input type="file" name="file" class="img" multiple onchange="addFileForm(); setThumbnail(event);"><input type="button" id="del" value="Delete"></div>'
				);
			count++;
			}
		}else {
			alert("사진은 최대 7장까지 가능합니다.");
			$("#del").trigger("click");
		}
};

//delete 버튼 클릭하면 파일 입력칸 삭제
$("#thumbnail").on("click", "#del", function(){
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


$(document).ready(function(){
	(function(){
		let productNum = $("#productNum").attr("title");		
	})
})
