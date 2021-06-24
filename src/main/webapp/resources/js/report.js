
const sendForm = document.querySelector('.send-form');
const btnReport = document.querySelector('.btn-report');
btnReport.addEventListener('click', ()=>{
	sendForm.submit();
	window.open("about:blank", "_self").close();
})