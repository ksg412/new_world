var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var options={
    dataType: "json", // 서버에서 보내줄 데이터의 타입
    contentType: "application/json; charset=utf-8",
    type : "post",
    beforeSend: function(xhr){
        xhr.setRequestHeader(header, token);
    },
    error:function(request,status,error){
        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        alert("오류가 발생되었습니다.");
    }
};

$.ajaxSetup( options );

/*
(function(window){
    Query.ajaxSetup( options );
    alert("aa");
});
*/




function aaa(){
    alert('aa');
}