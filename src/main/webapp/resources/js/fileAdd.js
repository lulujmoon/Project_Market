/**
 * 
 */

//초기 개수
let count = 1;


//add 버튼 클릭하면 파일 입력 칸 추가
$("#add").click(function(){
	if(count < 7){	
		let image = $("#inputimg").html();
		$("#thumbnail").append(image);
		count++;
	}else {
		alert("사진은 최대 7장까지 가능합니다.");
	}
});

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
		document.querySelector("div#img_container").appendChild(img);
	};
	
	reader.readAsDataURL(event.target.files[0]);
};