var imgFile = []; //file
var imgSrc = []; //img path
var imgName = []; //img name
$(function() {
    // 鼠标经过显示删除按钮
    $('.content-img-list').on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });
    // 鼠标离开隐藏删除按钮
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });
    // 单个图片删除
    $(".content-img-list").on("click", '.content-img-list-item a .gcllajitong', function() {
        var index = $(this).parent().parent().parent().index();
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 4) { //显示上传按钮
            $('.content-img .file').show();
        }
    });


    $(".content-img-list").on("click", '.content-img-list-item a .gclfangda', function() {
        var index = $(this).parent().parent().parent().index();
        $(".modal-content").html("");

        var bigimg = $(".modal-content").html();
        $(".modal-content").html(bigimg + '<div class="show"><img src="' + imgSrc[index] + '" alt=""><div>');
        // $(".modal-content").append(
        //     '<div class="show"><img src="' + imgSrc[a] + '" alt=""><div>'
        // );



    });


});
//图片上传
$('#upload').on('change', function(e) {
    var imgSize = this.files[0].size;
    if (imgSize > 1024 * 1024 * 20) { //1M
        return alert("Over 20MB");
    };
    if (this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
        return alert("img format is rong");
    }

    var imgBox = '.content-img-list';
    var fileList = this.files;
    for (var i = 0; i < fileList.length; i++) {
        var imgSrcI = getObjectURL(fileList[i]);
        imgName.push(fileList[i].name);
        imgSrc.push(imgSrcI);
        imgFile.push(fileList[i]);
    }
    addNewContent(imgBox);
    this.value = null; //上传相同图片
});

//提交请求
$('#btn-submit-upload').on('click', function() {
    // FormData上传图片
    var formFile = new FormData();
    // formFile.append("type", type);
    // formFile.append("content", content);
    // formFile.append("mobile", mobile);
    // 遍历图片imgFile添加到formFile里面
    $.each(imgFile, function(i, file) {
        formFile.append('myFile[]', file);
    });
    //    $.ajax({
    //        url: '',
    //        type: 'POST',
    //        data: formFile,
    //        async: true,
    //        cache: false,
    //        contentType: false,
    //        processData: false,
    //        // traditional:true,
    //        dataType:'json',
    //        success: function(res) {
    //            console.log(res);
    //            if(res.code==0){
    //                alert("已提交")
    //    //             $("#adviceContent").val("");
    // 			// $("#contact").val("");
    //            }else{
    //                alert(res.message);
    //                $('.content-img .file').show();
    //                $("#adviceContent").val("");
    //                $("#cotentLength").text("0/240");
    // 			$("#contact").val("");
    // 			imgSrc = [];imgFile = [];imgName = [];
    // addNewContent(".content-img-list");

});

//删除
function removeImg(obj, index) {
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = ".content-img-list";
    addNewContent(boxId);
}

//图片展示
function addNewContent(obj) {
    // console.log(imgSrc)
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt="">' +
            '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' +
            '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
    }
}

//建立可存取到file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}


//accept the image and change them into Base64 type string
function readURL(input) {
    if (input.files && input.files[0]) {
​
        var reader = new FileReader();
​
        reader.onload = function (e) {
            $('.image-upload-wrap').hide();
​
            $('.file-upload-image').attr('src', e.target.result);
            $('.file-upload-content').show();
​
            $('.image-title').html(input.files[0].name);
        };
​
        reader.readAsDataURL(input.files[0]);
​
    }else {
        removeUpload();
    }
}
​
function removeUpload() {
	    $('.file-upload-input').replaceWith($('.file-upload-input').clone());
	    $('.file-upload-content').hide();
	    $('.image-upload-wrap').show();
	}
	$('.image-upload-wrap').bind('dragover', function () {
	    $('.image-upload-wrap').addClass('image-dropping');
	});
	$('.image-upload-wrap').bind('dragleave', function () {
	    $('.image-upload-wrap').removeClass('image-dropping');
	});




