/**
 * 
 */


let count = $(".form").attr("title");
let index = count;
console.log(count);

$("#add").click(function(){	
	
	if(count<7) {
	$("#file").append('<div class="inputimg"><input type="file" name="file" class="img" onchange="previewImage(this, '+count+')"><input type="button" class="del" value="Delete" title="${files.fileNum}"><div id="preview'+index+'"></div></div>'
);

	count++;
	index++;
	} else {
		alert("최대 7개까지만 첨부가능합니다");
	}
	
})

//이벤트 위임
$("#file").on("click", ".del", function(){
	console.log($(this).parent());
	$(this).parent().remove();
	count--;
	index--;
	console.log(count);
});





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


//파일 입력시 이미지 미리보기
function previewImage(f,idx) {
	
	let file = f.files;
	
	//확장자 체크
	if(!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) {
		alert('gif, jpg, png 파일을 선택해 주세요. \n\n현재 파일 : '+file[0].name);
		
		//선택한 파일 초기화
		f.outerHTML = f.outerHTML;
		document.getElementById('preview'+idx).innerHTML='';
	} else {
		let reader = new FileReader();
		
		//파일 읽기가 완료되었을때 실행
		reader.onload = function(rst) {
			document.getElementById('preview'+idx).innerHTML = '<img src="' +rst.target.result+'">';
		}
		//파일 읽기
		reader.readAsDataURL(file[0]);
	}
	
}

//파일 삭제
$(".del").click(function(){
	let check = confirm("정말 삭제하실 것입니까?");
	
	if(check){
		
	let fileNum = $(this).attr("title");
	let obj = $(this);
	
	$.ajax({
		url:"./fileDelete",
		type: "POST",
		data: {fileNum:fileNum},
		success:function(result){
			result=result.trim();
			if(result>0){
				alert("삭제 성공");			
				$(obj).parent().remove();
			}else {
				alert("삭제 실패");
			}
		}
		
	});
	}
	
});


function submitCheck() {

	if(count>0) {
		return true;
	}
	
	return false;
}

