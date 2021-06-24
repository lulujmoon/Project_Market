
/** 초기설정 1. alert
 *	-- div class="alert"가 비어있지 않으면 그 안의 메세지를 alert한다.
 */
const message = document.querySelector('.alert');
if(message.innerText!=""){
	alert(message.innerText);
	location.href="./login";
}