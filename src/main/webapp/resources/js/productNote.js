/**
 * 
 */

const btnAdd = document.querySelector('.add');
const uploadForm = document.querySelector('#upload-form');
const previewContainer = document.querySelector('.preview-container');
const previews = document.querySelectorAll('.preview');
const inputs = document.querySelector('.inputs');

const btnPresubmit = document.querySelector('.btn-presubmit');
const btnSubmit = document.querySelector('.btn-submit');
const productContent = document.querySelector('#productContent');
const productPrice = document.querySelector('.product-price');
var counter = 0;

/** @function makeInputFile()
 *	-- input type="file"을 생성하고 반환한다.
 */
function makeInputFile(){
	let inputFile = document.createElement('input');
	inputFile.type = 'file';
	inputFile.name = 'file';
	inputFile.accept = "image/*";
	inputFile.required = 'required';
	inputFile.multiple = 'multiple';
	inputFile.classList.add('input-file');
	return inputFile;
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

/** @function makePreview(file)
 *	-- 이미지를 추가하면 preview를 생성한다.
 */
function makePreview(file){
	let preview = document.createElement('div');
	preview.classList.add('preview');
	previewContainer.appendChild(preview);
	let image = document.createElement('img');
	let reader = new FileReader();
	reader.onload = function(evt){
		image.src = evt.target.result;
	}
	reader.readAsDataURL(file);
	preview.appendChild(image);
}

/** 이벤트 1. 이미지 추가 버튼 클릭
 *	1. input type="file"을 생성하고 클릭 이벤트를 발생시킨다.
 *	2. 해당 input에 change 이벤트가 발생하면 파일을 읽고 미리보기를 생성한다.
 *	3. 파일은 최대 7개까지 추가할 수 있다.
 *	4. 파일 형식이 이미지가 아니면 alert하고 input을 삭제한다.
 */
btnAdd.addEventListener('click', ()=>{
	if(counter < 7){
		let inputFile = makeInputFile();	
		inputs.appendChild(inputFile);
		inputFile.click();
		
		inputFile.addEventListener('change', function(){
			counter = counter + this.files.length;
			if(counter < 8){
				let check = checkFiles(this.files);
				if(check){
					for(file of this.files){
						makePreview(file);
					}
				}else{
					alert('첨부할 수 있는 파일 형식은 gif, jpg, jpeg, png입니다. \n현재 파일 형식은 '+file.name+'입니다.');
					inputs.removeChild(inputFile);
				}	
			}else{
				alert('이미지는 최대 7장까지 첨부 가능합니다.');
			}
		});
		counter = (document.querySelectorAll('.preview')).length;
	}else{
		alert('이미지는 최대 7장까지 첨부 가능합니다.');
	}
});

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
	
	if(inputs.childElementCount == 0){
		alert('사진을 한 장 이상 등록해주세요.');
		result = false;
	}
	
	let priceCheck = /\D/;
	if((productPrice.value).search(priceCheck)!=-1){
		alert('가격은 숫자만 입력 가능합니다.');
		result = false;
	}
	
	if(result){
		btnSubmit.click();
	}
	
});
