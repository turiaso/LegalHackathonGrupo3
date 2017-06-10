
$(document).ready(function () {
    var egg = new Egg(); egg.addCode("up,up,down,down,left,right,left,right,b,a", function () { 
        jQuery('#egggif').fadeIn(500, function (){
             window.setTimeout(function () { jQuery('#egggif').hide(); }, 5000); }); }).addHook(function () {
                 window.location.href="form.html";
                window.location.replace="aaa"
 }).listen();
  $('body').on('click', '#diva #submitBtn', function () {
        var data = { title: "", msg: "" };
        data.title = $("#title").value;
        data.msg = $("#msg").value;
        console.log(data);
    });
});