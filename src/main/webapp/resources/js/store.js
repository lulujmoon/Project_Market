/**
 * 
 */
 
 /** const btnAvatar
	* 1. 클릭하면 file 팝업을 띄운다.
	* 2. input file이 변경되면 form을 제출한다.
  */
  
  const btnAvatar = document.querySelector('.profile__photo');
	const inputFile = document.querySelector('.input-file');
  const profileForm = document.querySelector('.profile__form');
  
  btnAvatar.addEventListener('click', ()=>{
		inputFile.click();
		inputFile.addEventListener('change', ()=>{
			profileForm.submit();
		})
})