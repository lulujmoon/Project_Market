/**
 * 
 */

let count = 1;

$("#add").click(function(){	
	
	if(count<7) {
	$("#file").append('<div class="inputimg"><input type="file" name="file" class="img" onchange="setThumbnail(event);"><input type="button" id="del" value="Delete"></div>');
	count++;
	} else {
		alert("최대 7개까지만 첨부가능합니다");
	}
	
})

$(".del").click(function(){
		$("#file").empty();
})
	
//이벤트 위임
$("#file").on("click", "#del", function(){
	console.log($(this).parent());
	$(this).parent().remove();
	count--;
});


$("#thumbnail").on("click", ".del", function(){
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


//빈파일 저장되지 않게
const btnSubmit = document.querySelector('#insertbtn');
const form = document.querySelector('.form');
 
 btnSubmit.addEventListener('click', ()=>{
   let files = document.querySelectorAll('.img');
   for(file of files){
		console.log(file.value);
		console.log(typeof(file.value));
      if(file.value == ""){
		console.log(file.parentNode);
        file.parentNode.innerHTML = "";
      }
   }

   form.submit();
});


$(document).ready(function(){
	(function(){
		let productNum = $("#productNum").attr("title");		
	})
})
