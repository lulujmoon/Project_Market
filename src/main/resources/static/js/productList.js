/**
 * 
 */


function goListByCategory(categoryCode, search){
	location.href = './list?categoryCode='+categoryCode+'&search='+search;
} 
 
function goSelect(productNum){
	location.href = './select?productNum='+productNum;
}

function goPage(search, pageNum){
	location.href = './list?curPage='+pageNum+'&search='+search;
}