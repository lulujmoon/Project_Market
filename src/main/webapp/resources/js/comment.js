/**
 * 
 */

var socialNum = '${vo.socialNum}'; // 게시글 번호
 
$('[name=commentInsertBtn]').click(function() { // 댓글 등록 버튼 클릭 시 
    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsert의 내용을 가져옴
    commentInsert(insertData); //Insert 함수 호출(아래)
});
 
//댓글 목록 
function commentList() {
    $.ajax({
        url : '/comment/list',
        type : 'get',
        data : {'socialNum': socialNum},
        success : function(data) {
            var a =''; 
            $.each(data, function(key, value) { 
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.commentNum+'">'+'댓글번호 : '+value.commentNum+' / 작성자 : '+value.username;
                a += '<a onclick="commentUpdate('+value.commentNum+',\''+value.commentContent+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.commentNum+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.commentNum+'"> <p> 내용 : '+value.commentContent +'</p>';
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
                $('[name=commentContent]').val('');
            }
        }
    });
}
 
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(commentNum, commentContent){
    var a ='';
    
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+commentNum+'" value="'+commentContent+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdate('+commentNum+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+commentNum).html(a);
    
}
 
//댓글 수정
function commentUpdate(commentNum){
    var updateContent = $('[username=commentContent_'+commentNum+']').val();
    
    $.ajax({
        url : '/comment/update',
        type : 'post',
        data : {'commentContent' : updateContent, 'commentNum' : commentNum},
        success : function(data){
            if(data == 1) commentList(socialNum); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(commentNum){
    $.ajax({
        url : '/comment/delete/'+commentNum,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(socialNum); //댓글 삭제후 목록 출력 
        }
    });
}
 
$(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력 
});
