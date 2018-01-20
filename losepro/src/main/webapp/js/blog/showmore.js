$(function () {
    var blogSize = $("#blogSize").val();
    var pageNumber = $("#pageNumber").val();
    var pageTotal = $("#pageTotal").val();

    if (blogSize < 20) {
        $("#nextPage").attr("href","javascript:alert('已经是最后一页了...')").css("color","grey")
    }else{
        if(pageNumber == 1){
            $("#beforePage").attr("href","javascript:alert('已到达第一页了...')").css("color","grey")
        }else if(pageNumber == pageTotal){
            $("#nextPage").attr("href","javascript:alert('已经是最后一页了...')").css("color","grey")
        }
    }
})
