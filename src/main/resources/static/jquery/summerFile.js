/**
 * 
 */

$("#socialContent").summernote({
			height:500,
			minHeight:null,
			maxHeight:null,
			focus:true,
			placeholder: '내용을 작성해주세요.',
			callbacks: {
				onImageUpload: function(file) {
				   uploadFile(file[0]);
				},
				onMediaDelete: function(file){
					deleteFile(file);
				}
			}
					
});

function deleteFile(file) {
	let fileName = $(file[0]).attr("src");
	fileName = fileName.substring(fileName.lastIndexOf('/')+1);
	$.post("summerFileDelete", {fileName:fileName}, function(result){
		console.log(result);
	});
}

function uploadFile(file) {
	const formData = new FormData();
	formData.append('file', file);
	let fileName="";
	$.ajax({
		type: "POST",
		url: "./summerFileUpload",
		data:formData,
		enctype:"multipart/form-data",
		cache:false,
		processData:false,
		contentType:false,
		success:function(result){
			fileName=result.trim();
			console.log(fileName);
			$("#socialContent").summernote('insertImage', fileName);
		} 
		
	})
};