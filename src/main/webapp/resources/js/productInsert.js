/**
 * 
 */
 
 const btnInsert = document.querySelector('.btn-insert');
 const productContent = document.querySelector('#productContent');
 const uploadForm = document.querySelector('#uploadForm');
	btnInsert.addEventListener('click', ()=>{
		productContent.value = productContent.value.replace(/\n/gm, "<br>");
		uploadForm.submit();
	});