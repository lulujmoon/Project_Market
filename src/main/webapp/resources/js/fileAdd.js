/**
 * 
 */

var input = document.querySelector('.img');
var preview = document.querySelector('.preview');

input.style.opacity = 0;

input.addEventListener('change', updateImageDisplay);

function updateImageDisplay() {
  while(preview.firstChild) {
    preview.removeChild(preview.firstChild);
  }

  const curFiles = input.files;
  if(curFiles.length === 0) {
    const para = document.createElement('p');
    para.textContent = '추가된 파일이 없습니다';
    preview.appendChild(para);
  } else {
    const list = document.createElement('ol');
    preview.appendChild(list);

    for(const file of curFiles) {
      const listItem = document.createElement('li');
      const para = document.createElement('p');
/*      const del = document.createElement('a');

		del.textContent= 'x';
		del.addEventListener('click',deleteFile(file))*/
	
        para.textContent = `${file.name}`;
        const image = document.createElement('img');
        image.src = URL.createObjectURL(file);
		
        listItem.appendChild(image);
        listItem.appendChild(para);
/*		listItem.appendChild(del);*/
		
      list.appendChild(listItem);
    }
  }
}



function deleteFile(file) {

	let fileName = document.querySelector('p');

	console.log(fileName);
/*	let fileName = $(files[0]).attr("src");
	fileName = fileName.substr(fileName.lastIndexOf('/')+1);
	alert(fileName);*/
/*	$.post("summerFileDelete", {fileName:fileName}, function(result){
		console.log(result);
	});*/
}





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
