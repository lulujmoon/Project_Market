
/**
		1. 모두 동의를 누르면 전부 체크/해제
		2. 전부 체크되어있을 때  개별 체크를 풀면 모두 동의도 해제
		3. 개별 체크로 전부 체크 상태를 만들면 모두 동의도 체크
 */
 
 const checkAll = document.querySelector('#checkAll');
 const checkEach = document.querySelectorAll('.checkEach');
 
 /* 1. 모두 동의를 누르면 전부 체크/해제 */
 
 checkAll.addEventListener('click', ()=>{
	if(checkAll.checked == true){
		checkEach.forEach((check)=>{
			check.checked = true;
		}		
	)
	}else{
		for(check of checkEach){
			check.checked = false;
		}		
	}
	});


 /* 
 		2. 전부 체크되어있을 때  개별 체크를 풀면 모두 동의도 해제
		3. 개별 체크로 전부 체크 상태를 만들면 모두 동의도 체크
 */

function test(){
	let result = true;
	for(ch of checkEach){
		if(!ch.checked){
			result = false;
			break;
		}
	}
	checkAll.checked = result;
}
 
 for(check of checkEach){
	check.addEventListener('click', test);
};