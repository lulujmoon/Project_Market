

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