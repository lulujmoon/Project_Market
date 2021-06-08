<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<c:import url="../template/setting.jsp"></c:import>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- summernote -->
  <!--   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script> -->
<!-- ---------- -->
<title>Hello, world!</title>

</head>
<body>
<h2>Product Insert Page</h2>
	<form class="form" id="uploadFrom" action="./insert" method="POST" enctype="multipart/form-data">
		

	
			<input type="button" id="add" value="ADD" class="add">
			<div id="file" title="1">
				<div class="inputimg">
						<input type="file" name="file" required class="img" multiple onchange="addFileForm(); setThumbnail(event);">
						<img src="">
						<input type="button" id="del" value="Delete">
			 </div>
			</div>
	
		
<input type="file" class="temporaryFile" name="thumbnailImg[0]" onChange="temporaryFileTransmit(0);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[1]" onChange="temporaryFileTransmit(1);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[2]" onChange="temporaryFileTransmit(2);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[3]" onChange="temporaryFileTransmit(3);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[4]" onChange="temporaryFileTransmit(4);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[5]" onChange="temporaryFileTransmit(4);" style="display:none;"/>
<input type="file" class="temporaryFile" name="thumbnailImg[6]" onChange="temporaryFileTransmit(4);" style="display:none;"/>
<table>
    <tr>
        <th>이미지 업로드 : </th>
        <td>
            <a href="javascript:;" onClick="temporaryFileUpload(0);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:inline;"/>
            </a>
            <a href="javascript:;" onClick="temporaryFileUpload(1);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
            <a href="javascript:;" onClick="temporaryFileUpload(2);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
            <a href="javascript:;" onClick="temporaryFileUpload(3);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
            <a href="javascript:;" onClick="temporaryFileUpload(4);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
             <a href="javascript:;" onClick="temporaryFileUpload(5);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
             <a href="javascript:;" onClick="temporaryFileUpload(6);">
                <img class="thumbnailImg" src="/resources/images/plusimg.jpg" width="100px" height="100px" style="display:none;"/>
            </a>
        </td>
    </tr>
</table>

		
		
		
		<div class="form-group">
	    <label>상품 명</label>
	    <input type="text" name="productName">
	  </div>
	    <div class="form-group">
	    <label>판매자</label>
	    <input readonly="readonly" type="text" name="username" value="${principal.username}">
	  </div>
	  	<div>
		  <div class="form-group">
		    <label for="category">카테고리</label>
		    <select class="form-control" id="category" name="categoryCode">
		      <option value="1">디지털/가전</option>
		      <option value="2">가구/인테리어</option>
		      <option value="3">유아동/유아도서</option>
		      <option value="4">생활/가공식품</option>
		      <option value="5">스포츠/레저</option>
		      <option value="6">여성의류/잡화</option>
		      <option value="7">남성의류/잡화</option>
		      <option value="8">게임/취미</option>
		      <option value="9">뷰티/미용</option>
		      <option value="10">반려동물용품</option>
		      <option value="11">도서/티켓/음반</option>
		      <option value="12">삽니다</option>
		    </select>
		  </div>
		</div>
	    <div class="form-group">
	    <label for="productContents">상품 설명</label>
	    <textarea id="productContent" rows="5" name="productContent"></textarea>
	  </div>
	    <div class="form-group">
	    <label>상품 가격</label>
	    <input type="text" name="productPrice">
	  </div>
	  
	   <input type="hidden" name="productNum">
	   
	  

	 <button id="insertbtn" class="btn btn-outline-secondary">Write</button><br><br><br>
</form>

<!-- <script type="text/javascript" src="../resources/js/summerFile.js"></script> -->
<script type="text/javascript" src="../resources/js/fileAdd2.js"></script>
<script type="text/javascript">
function temporaryFileUpload(num) {

    // 이미지파일의 정보를 받을 배열을 선언한다.
    var tmpFile = new Object();
    tmpFile['file'] = new Array();     // tmpFile['file'] 파일의 정보를 담을 변수
    tmpFile['img'] = new Array();    // tmpFile['file'] 이미지의 경로를 담을 변수
    var tmpNum = 0;
    var addPlus = 0;

    // 먼저 업로드 된 파일의 존재 유무를 확인한다.
    if($(".temporaryFile").eq(num).val()) {

        // 파일이 존재하면 우선 기존 파일을 삭제한 이후에 작업을 진행한다.
        if(confirm("해당 이미지를 삭제 하시겠습니까?") == true) {

            // 먼저 업로드 하지 않을 파일을 제거한다.
            $(".temporaryFile").eq(num).val("");

            // 파일이 제거되면 <input type="file"/>의 수만큼 반복문을 돌린다.
            $(".temporaryFile").each(function(idx) {

                // 반복문을 돌리는 중에 <input type="file"/>의 값이 존재한는 순서로 배열에 담는다.
                if($(".temporaryFile").eq(idx).val()) {
                    tmpFile['file'][tmpNum] = [$(".temporaryFile").eq(idx).clone()];
                    tmpFile['img'][tmpNum] = $(".thumbnailImg").eq(idx).attr("src");
                    tmpNum++;
                }
            });
           
            // 모든 썸네일 이미지 정보를 초기화 한다.
            $(".temporaryFile").val("");
            $(".thumbnailImg").attr("src", "./plusimg.png");
            $(".thumbnailImg").css("display", "none");
           
            // 배열로 받은 파일의 정보를 바탕으로 순서를 재정렬한다.
            for(var key in tmpFile['file']) {
                $(".temporaryFile").eq(key).replaceWith(tmpFile['file'][key][0].clone(true));
                $(".thumbnailImg").eq(key).css("display", "inline");
                $(".thumbnailImg").eq(key).attr("src", tmpFile['img'][key]);
                addPlus++;
            }

            if(addPlus < 5) {
                $(".thumbnailImg").eq(addPlus).css("display", "inline");
            }

        } else {
            return false;
        }
    }
   
    // 파일이 존재하지 않다면 업로드를 시작한다.
    else {

        $(".temporaryFile").eq(num).click();
    }
}

// 임시폴더에 파일을 업로드하고 그 경로를 받아온다.
function temporaryFileTransmit(num) {
    var form = $("#uploadFrom")[0];
    var formData = new FormData(form);
    formData.append("mode", "temporaryImageUpload");
    formData.append("tmpFile", $(".temporaryFile").eq(num)[0].files[0]);
   
    // ajax로 파일을 업로드 한다.
    $.ajax({
          url : "./upload_class.php"
        , type : "POST"
        , processData : false
        , contentType : false
        , data : formData
        , success:function(json) {
            var obj = JSON.parse(json);
            if(obj.ret == "succ") {

                // 업로드된 버튼을 임시폴더에 업로드된 경로의 이미지 파일로 교체한다.
                $(".thumbnailImg").eq(num).attr("src", obj.img);

                // 업로드 버튼이 4개 이하인경우 업로드 버튼을 하나 생성한다.
                if(num < 5) {
                    $(".thumbnailImg").eq(++num).css("display", "inline");
                }

            } else {
                alert(obj.message);
                return false;
            }
        }
    });
}


</script>

</body>
</html>