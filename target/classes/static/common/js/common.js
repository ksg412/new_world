
/*csrf세팅*/
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

/*ajax세팅*/
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

/*datatable세팅*/
$.extend(true, $.fn.dataTable.defaults, {
    searching : false,
    lengthMenu : [ 10, 20, 50 ],
    ordering : false,
    processing : true,
    pagingType : "full_numbers",
    scrollX: true,
    autoWidth: false,
    scrollCollapse: true,
    //scrollXInner : "1265px",
    fixedColumns: true,
    serverSide: true,
    language:{
        "decimal" : "",
        "emptyTable" : "데이터가 없습니다.",
        "info" : "_START_ - _END_ (총 _TOTAL_ 명)",
        "infoEmpty" : "0명",
        "infoFiltered" : "(전체 _MAX_ 명 중 검색결과)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "_MENU_ 개씩 보기",
        "loadingRecords" : "로딩중...",
        "processing" : "처리중...",
        "search" : "검색 : ",
        "zeroRecords" : "검색된 데이터가 없습니다.",
        "paginate" : {
            "first" : "첫 페이지",
            "last" : "마지막 페이지",
            "next" : "다음",
            "previous" : "이전"
        },
        "aria" : {
            "sortAscending" : " :  오름차순 정렬",
            "sortDescending" : " :  내림차순 정렬"
        }
    }
});

/*로그아웃처리*/
function securityLogout(){
    $("#logout_form").attr("action","/logout").submit();
}

(function(window){

});