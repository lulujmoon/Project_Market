/**
 * 
 */

var commentNum = '${comment.commentNum}'; // 게시글 번호
 
$('[name=commentInsertBtn]').click(function() { // 댓글 등록 버튼 클릭 시 
    var insertData = $('[name=commentInsert]').serialize(); //commentInsert의 내용을 가져옴
    commentInsert(insertData); //Insert 함수 호출(아래)
});
 
//댓글 목록 
function commentList() {
    $.ajax({
        url : '/comment/list',
        type : 'get',
        data : {'commentNum': commentNum},
        success : function(data) {
            var a =''; 
            $.each(data, function(key, value) { 
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.socialNum+'">'+'댓글번호 : '+value.socialNum+' / 작성자 : '+value.username;
                a += '<a onclick="commentUpdate('+value.socialNum+',\''+value.commentContent+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.socialNum+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.socialNum+'"> <p> 내용 : '+value.commentContent +'</p>';
                a += '</div></div>';
            });
            
            $(".commentList").html(a);
        }
    });
}
 
//댓글 등록
function commentInsert(insertData){
    $.ajax({
        url : '/comment/insert',
        type : 'post',
        data : insertData,
        success : function(data){
            if(data == 1) {
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=content]').val('');
            }
        }
    });
}
 
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(socialNum, commentContent){
    var a ='';
    
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+socialNum+'" value="'+commentContent+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+socialNum+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+socialNum).html(a);
    
}
 
//댓글 수정
function commentUpdateProc(socialNum){
    var updateContent = $('[name=content_'+socialNum+']').val();
    
    $.ajax({
        url : '/comment/update',
        type : 'post',
        data : {'content' : updateContent, 'socialNum' : socialNum},
        success : function(data){
            if(data == 1) commentList(commentNum); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(socialNum){
    $.ajax({
        url : '/comment/delete/'+socialNum,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(commentNum); //댓글 삭제후 목록 출력 
        }
    });
}
 
$(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력 
});
