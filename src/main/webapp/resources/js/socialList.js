/**
 * 
 */

function goListByCategory(categoryCode, search){
	category.href = './list?categoryCode='+categoryCode+'&search='+search;
} 
 
function goSelect(socialNum){
	category.href = './select?socialNum='+socialNum;
}

function goPage(search, pageNum){
	category.href = './list?curPage='+pageNum+'&search='+search;
}