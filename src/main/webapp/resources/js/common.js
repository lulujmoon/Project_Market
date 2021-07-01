

/* 로고 */
var nav__logo = document.querySelector(".nav__logo");
nav__logo.addEventListener("click", function(){
	location.href = "/";
});

/* 사이드 메뉴 토글 */
var toggle_btn = document.querySelector("#toggle-btn");
var side_menu = document.querySelector(".side-menu");
toggle_btn.addEventListener("click", function(){
	side_menu.classList.toggle("active");
});

/** 초기설정 . 알림 버튼
 */
const btnNoti = document.querySelector('.btn-noti');
const notiContainer = document.querySelector('.noti-container');
btnNoti.addEventListener('click', ()=>{
	$.get(
		'../notification/list',
		function(result){
			notiContainer.innerHTML = result;
		});
	
	
	
	notiContainer.classList.toggle('active');
});