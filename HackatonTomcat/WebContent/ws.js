var WS = function () {
   WS.call = callToBackEnd('/HackatonTomcat/test','POST',"prueba",function(){alert("llamando al back")});
}

var callToBackEnd = function (urlAddress, typeComm,dataToSend, callbackFunction) {
   $.ajax({
       url: urlAddress,
       type: 'POST',
       contentType: "text/plain",
       dataType: 'string',
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