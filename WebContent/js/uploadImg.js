var imgFile = []; //file
var imgSrc = []; //img path
var imgName = []; //img name
$(function() {
    // show delete button
    $('.content-img-list').on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });
    // hide delete button
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });
    // single picture delete
    $(".content-img-list").on("click", '.content-img-list-item a .gcllajitong', function() {
        var index = $(this).parent().parent().parent().index();
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 4) { //show upload button
            $('.content-img .file').show();
        }
    });


    $(".content-img-list").on("click", '.content-img-list-item a .gclfangda', function() {
        var index = $(this).parent().parent().parent().index();
        $(".modal-content").html("");

        var bigimg = $(".modal-content").html();
        $(".modal-content").html(bigimg + '<div class="show"><img src="' + imgSrc[index] + '" alt=""><div>');
        


    });


});
//upload picture
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
    	imgName.push(fileList[i].name);
    	imgFile.push(fileList[i]);
    	getBase64(fileList[i], function (data) {
    		var imgSrcI = data;
    	    imgSrc.push(imgSrcI);  
    	    addNewContent(imgBox);
    	});
    	//var imgSrcI = getBase64(fileList[i],);
       
    }
    //addNewContent(imgBox);
    this.value = null; //upload picture
});

//submit request
$('#btn-submit-upload').on('click', function() {
    // FormData upload picture
    var formFile = new FormData();

    $.each(imgFile, function(i, file) {
        formFile.append('myFile[]', file);
    });
   

});

//delete
function removeImg(obj, index) {
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = ".content-img-list";
    addNewContent(boxId);
}

//show image
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

//Create a url that can access the file
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
//
function run(file) {
    var url = null;
    var f = file;
	var reader = new FileReader();
	var files = file;
	var that;

	// upload img
	reader.readAsDataURL(f); // 


	reader.onload = function() {

		
		//var img = '<img id="myimg" src="' + this.result
		//		+ '" style="pointer-events: none;"/>'; 
		//$('.ImgBox').append(img); 

		url = this.result;
		var i=1;
		//that = that.split(',')[1];
		//fileBase64.push(that);
		/* console.log(this.result);  */
	}
    return url;
}


function getBase64(input_file, get_data) {
    if (typeof (FileReader) === 'undefined') {
        alert("Sorry,the current browser does not support File Reader, can not convert image to Base64!");
    } else {
        try {
            /*convert image to base64*/
            var file = input_file;
            if (!/image\/\w+/.test(file.type)) {
                alert("ensure images");
                return false;
            }
            var reader = new FileReader();
            reader.onload = function () {
                get_data(this.result);
            }
            reader.readAsDataURL(file);
        } catch (e) {
            alert('convert base64 wrong！' + e.toString())
        }
    }
}


