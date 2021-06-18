/**
 * 
 */

const btnAdd = document.querySelector('.add');
const uploadForm = document.querySelector('#upload-form');
const previewContainer = document.querySelector('.preview-container');
var previews = document.querySelectorAll('.preview');
var inputs = document.querySelector('.inputs');

const btnPresubmit = document.querySelector('.btn-presubmit');
const btnSubmit = document.querySelector('.btn-submit');
const productContent = document.querySelector('#productContent');
const productPrice = document.querySelector('.product-price');
var counter = 0;


/** @function makeInput()
 *	-- inputs 영역 안에 input type file을 추가한다.
 */
function makeInput(){
	let inputFile = document.createElement('input');
	inputFile.type = 'file';
	inputFile.name = 'file';
	inputFile.accept = 'image/*';
	inputFile.required = 'required';
	inputFile.multiple = 'multiple';
	inputFile.classList.add('input-file', 'file_'+inputNum);
	
	inputs.appendChild(inputFile);
	inputFile.click();
	
	return inputFile;
}

/** @function makePreview(file)
 *	-- previewContainer 안에 div preview를 추가한다.
 */
function makePreview(file){ 
		let preview = document.createElement('div');
		preview.classList.add('preview', 'preview_'+inputNum, 'preview_'+inputNum+'_'+innerNum);
		let image = document.createElement('img');
		let reader = new FileReader();
		reader.onload = function(evt){
			image.src = evt.target.result;
		}
		reader.readAsDataURL(file);
		preview.appendChild(image);
		previewContainer.appendChild(preview);
}

/** @function makeBtnDel()
 *	-- 이미지 삭제 버튼을 생성하고 클릭하면 deleteFile()을 호출한다.
 */
function makeBtnDel(){
	let delWrapper = document.createElement('div');
	delWrapper.classList.add('del-wrapper');
	let btnDel = document.createElement('div');
	btnDel.classList.add('btn-del', 'del_'+inputNum, 'del_'+inputNum+'_'+innerNum);
	btnDel.innerHTML = '<i class="fas fa-times"></i>';
	previewContainer.append(delWrapper);
	delWrapper.append(btnDel);
	btnDel.addEventListener('click', deleteFile);
}

/** @function deleteFile()
 *	-- 이미지 파일을 삭제한다.
 *	1. 클래스명에서 inputNum과 innerNum을 찾는다.
 *	2. 버튼을 클릭하면 preview와 버튼, input을 모두 삭제한다.
 *	2-1. input이 없는(글 수정 시 불러온 기존의) 파일은 inputNum이 0이므로 조건문으로 제외한다.
 *	3. input이 multiple을 허용하므로 선택한 파일을 따로 삭제하려면
 *	3-1. DataTransfer를 생성해 input 내의 파일을 '선택한 파일만 제외하고' 복사한다.
 *	3-2. input.files에 dataTransfer.files를 대입한다.
 *	4. 해당 inputNum의 preview와 btnDel의 클래스 번호를 새로 부여한다.
 *	5. counter를 초기화한다.
 */
 function deleteFile(evt){
	let className = evt.currentTarget.classList.item(2);
	numArr = className.split('_');
	let inputNum = numArr[1];
	let innerNum = numArr[2];
	let preview = document.querySelector('.preview_'+inputNum+'_'+innerNum);
	let delWrapper = evt.currentTarget.parentNode;
	
	if(inputNum>0){
		let inputFile = document.querySelector('.file_'+inputNum);
		const dt = new DataTransfer();
		for(let i=0;i<inputFile.files.length;i++){
			if(i!=innerNum){
				dt.items.add(inputFile.files[i]);
			}
		}
		inputFile.files = dt.files;
	}

	preview.remove();
	delWrapper.remove();
	
	let previewGroup = document.querySelectorAll('.preview_'+inputNum);
	let btnDelGroup = document.querySelectorAll('.del_'+inputNum);
	for(let i=0;i<previewGroup.length;i++){
		let oldClassName = previewGroup[i].classList.item(2);
		previewGroup[i].classList.replace(oldClassName, 'preview_'+inputNum+'_'+i);
		oldClassName = btnDelGroup[i].classList.item(2);
		btnDelGroup[i].classList.replace(oldClassName, 'del_'+inputNum+'_'+i);
	}
	counter = (document.querySelectorAll('.preview')).length;		
}

/** @function checkFiles(files)
 *	-- 파일의 확장자를 체크한다.
 *	1. 이미지 파일이 아니면 false를 반환하고, 모두 이미지면 true를 반환한다.
 */
function checkFiles(files){
	const fileCheck = /\.(gif|jpg|jpeg|png)$/;
	for(file of files){
		if((file.name).search(fileCheck)==-1){
			return false;
		}
	}
	return true;
}

/** @function deleteFileInDB(fileNum)
 *	-- 파일 번호를 받아 DB에서 삭제한다.
 */
function deleteFileInDB(fileNum){
	$.ajax({
		url:"../fileDelete",
		type: "POST",
		data: {fileNum:fileNum},
		success:function(result){
			if(result<0){
				alert('삭제되지 않았습니다. 다시 시도해주세요.');
			}
		}
	});
}


/** 초기설정 1. productContent의 <br>을 /n으로 변경한다.
 */
productContent.value = productContent.value.replace(/<br>/gm, "\n");
productContent.value = productContent.value.replace(/\t/gm,"");

/** 초기설정 2. 기존 이미지의 del 버튼에 이벤트를 부여한다.
 */
const btnDels = document.querySelectorAll('.btn-del');
for(btnDel of btnDels){
	btnDel.addEventListener('click', deleteFile);
}

/** 이벤트 1. 이미지 추가 버튼 클릭
 *	1. input type file 추가하고 click()
 *	2. change 이벤트 발생 시 preview와 btn-del 생성
 */
 
btnAdd.addEventListener('click', ()=>{
	if(counter < 7){
		let inputFile = makeInput();
		
		inputFile.addEventListener('change', function(){
			counter = counter + this.files.length;
			if(counter<8){
				let check = checkFiles(this.files);
				if(check){				
					for(file of this.files){					
						makePreview(file);
						makeBtnDel();
						innerNum++;
					}
					inputNum++;
					innerNum = 0;
				}else{
					alert('첨부할 수 있는 파일 형식은 gif, jpg, jpeg, png입니다. \n현재 파일 형식은 '+file.name+'입니다.');
					inputs.removeChild(inputFile);
				}						
			}else{
				alert('이미지는 최대 7장까지 첨부 가능합니다.');
			}
			counter = (document.querySelectorAll('.preview')).length;		
		});
	}else{
		alert('이미지는 최대 7장까지 첨부 가능합니다.');
		counter = (document.querySelectorAll('.preview')).length;
	}
});

	var inputNum = 1;
	var innerNum = 0;


/** 이벤트 2. 등록 버튼 클릭
 *	-- 조건을 확인하고 모두 부합하면 btn-submit의 click 이벤트를 발생시킨다.
 *	1. 빈 input file을 삭제한다.
 *	2. 상품 설명의 엔터를 <br>로 바꾼다.
 *	3. file은 최소한 한 개가 필요하다.
 *	4. 가격은 숫자여야 한다.
 */
 btnPresubmit.addEventListener('click', ()=>{
	let result = true;
	for(input of inputs.children){
		if(input.value == ""){
			inputs.removeChild(input);
		}
	}
	
	productContent.value = productContent.value.replace(/\n/gm, "<br>");
	
	if(document.querySelectorAll('.preview').length == 0){
		alert('사진을 한 장 이상 등록해주세요.');
		result = false;
	}
	
	let priceCheck = /\D/;
	if((productPrice.value).search(priceCheck)!=-1){
		alert('가격은 숫자만 입력 가능합니다.');
		result = false;
	}
	
	if(result){
/*		for(input of inputs.children){
			for(let file of input.files){
				console.log(file.name);
			}
		}*/
		btnSubmit.click();
	}
	
});