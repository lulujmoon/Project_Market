/**
 * 
 */
 
 const kakaoBtn = document.querySelector('.btn-kakao');
 kakaoBtn.addEventListener('click', ()=>{
	location.href='https://kauth.kakao.com/oauth/authorize?client_id=bdf85067bd67f89b950ae22189274a9c&redirect_uri=http://localhost/member/auth/kakao/callback&response_type=code';
});

const message = document.querySelector('.alert');
if(message.innerText != ""){
	alert(message.innerText);
}