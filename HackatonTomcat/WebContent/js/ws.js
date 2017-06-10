var WS = function () {
    WS.call = callToBackEnd('/','POST',"prueba",function(){alert("llamando al back")});
}

var callToBackEnd = function (urlAddress, typeComm,dataToSend, callbackFunction) {
    dataToSend = encrypt(dataToSend);
    $.ajax({
        url: urlAddress,
        //url: '/postsmanager/api/controller/get/post/in/file/by/page/id/'+userId+'/'+postId,
        type: typeComm,
        contentType: "application/json",
        dataType: 'json',
        data: dataToSend,
        success: function (comments) {
            if (callbackFunction) {
                callbackFunction();
            }
            console.log("success postsss");
            console.log(comments);
           // $.post.getReadComments($.user.id, pageId, postId, comments.data, "comments");

        },
        error: function (comments) {
            console.log("fail post");
        }
    });
}


$(document).ready(function () {
    WS.call();
});