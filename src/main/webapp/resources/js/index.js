/**
 * 
 */
 
 /* 캐러셀 */
  
 const carousel_slide = document.querySelector('.carousel-slide');
 const carousel_images = document.querySelectorAll('.carousel-images');
 const prev_btn = document.querySelector('#prev-btn');
 const next_btn = document.querySelector('#next-btn');
 
 let counter = 1;
 let size;

 window.addEventListener('load', ()=>{
	
	 size = carousel_images[0].clientWidth;
	 carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';
		 
	 window.addEventListener('resize', ()=>{
	  size = carousel_images[0].clientWidth;	
	  carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';
	});
	
	 
	 prev_btn.addEventListener('click', ()=>{
		carousel_prev();
	});
	
	 next_btn.addEventListener('click', ()=>{
		carousel_next();
	});
	 
	 carousel_check();
	
});


/* 캐러셀 함수 */
 function carousel_prev(){
	if(counter<=0){
			return
		};
		carousel_slide.style.transition = 'transform 0.4s ease-in-out';
		counter--;
		carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';
}

 function carousel_next(){
		if(counter>=carousel_images.length-1){
			return
		};
		carousel_slide.style.transition = 'transform 0.4s ease-in-out';
		counter++;
		carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';
}

 function carousel_check(){
		carousel_slide.addEventListener('transitionend', ()=>{
		if(carousel_images[counter].id === 'lastClone'){
			counter = carousel_images.length-2;
			carousel_slide.style.transition = "none";
			carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';	
		}
		
		if(carousel_images[counter].id === 'firstClone'){
			counter = carousel_images.length - counter;
			carousel_slide.style.transition = "none";
			carousel_slide.style.transform = 'translateX('+(-size*counter)+'px)';	
		}	
	});	
}
	 
 
 
 /* 캐러셀 3초마다 넘기기 */
 function carousel(){
	carousel_next();
	carousel_check();
}

const start = setInterval(carousel, 3000);
 
 