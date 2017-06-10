(function () {

   $('#send').on('click', function(){
       var data = { title: "", msg: "" };
        data.title = $("#title").val();
        data.msg = $("#msg").val();
        WS.call('/HackatonTomcat/test','POST',JSON.stringify(data),function(){
            console.warn("sent");
        });

        console.log(data);
   });


})();