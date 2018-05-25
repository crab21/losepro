define(['angular','jquery','echarts'], function (angular,jquery,echarts) {
    //配电柜，map数据
    function seitchLight() {
        // 选择分组
        $('#group select').on('change',function(){
            if(this.value!=''){
                cancelAll();
                var str=$('#group select').val();
                str=str.split("-");
                for(var i=0;i<str.length;i++){
                    $('#site-'+str[i]).addClass('blockAfter');
                    $('#site-'+str[i])[0].setAttribute("data-state", "1");
                }
            }
        })
    }
    //全选
    function all() {
        var liArr = document.querySelectorAll(".map li");
        $('.map li').addClass('blockAfter');
        for (var i = 0; i < liArr.length; i++) {
            liArr[i].setAttribute("data-state", "1");
            liArr[i].getAttribute("data-state");
        }
    }
    //全不选
    function cancelAll() {
        var liArr = document.querySelectorAll(".map li");
        $('.map li').removeClass('blockAfter');
        for (var i = 0; i < liArr.length; i++) {
            liArr[i].setAttribute("data-state", "0");
        }
    }


    return {
        seitchLight: seitchLight,
        all:all,
        cancelAll:cancelAll
    };


});






















