define(['angular', 'jquery', 'echarts', 'common', 'layer'], function (angular, jquery, echarts, common, layer) {

    //angular会自动根据controller函数的参数名，导入相应的服务
    return function ($scope, $http, $interval) {
        var url = 'http://47.94.208.175:82/';
        url = '';
        init();
        function init() {
            $('.map').height((3 * $('.map').width()) / 4);
        }

        window.addEventListener('resize', function () {
            init();
        })
        setTimeout(function () {
            init();
        }, 0)
        var liArr = document.querySelectorAll(".map li");
        common.seitchLight();
        //全选
        function all() {
            $('.map li').addClass('blockAfter');
            for (var i = 0; i < liArr.length; i++) {
                liArr[i].setAttribute("data-state", "1");
            }
        }

        //全不选
        function cancelAll() {
            $('.map li').removeClass('blockAfter');
            for (var i = 0; i < liArr.length; i++) {
                liArr[i].setAttribute("data-state", "0");
            }
        }

        $('#choose li')[0].onclick = function () {
            if (this.getAttribute('data-li') == '0') {
                $('#choose li:nth-of-type(1)').css('background', 'url("../img/lightCtrl/quanbuxuan.png") no-repeat center/100%').next('span').text('取消');
                this.setAttribute('data-li', '1');
                all();
            } else {
                $(this).css('background', 'url("../img/lightCtrl/quanxuan.png") no-repeat center/100%').data('li', '1').next('span').text('全选');
                this.setAttribute('data-li', '0');
                cancelAll();
            }
        };
        //获取空调设备状态
        getDevice1()
        function getDevice1() {
            $.ajax({
                url: url + "airconditioner/state",
                async: true,
                dataType: 'json',
                success: function (res) {
                    change(res);
                    setTimeout(getDevice1, 1000);
                },
                error: function () {
                }
            });
        };
        // 初始化
        function change(res) {
            for (var k in res) {
                if (res[k].state == 1) {
                    $('#site-' + k).css('background', 'url("../../img/airCtrl/yunxing.png") no-repeat center/100%').children('i').text(res[k].indoorTemperature);
                } else if (res[k].state == 0) {
                    $('#site-' + k).css('background', 'url("../../img/airCtrl/guanbi.png") no-repeat center/100%').children('i').text(res[k].indoorTemperature);
                } else if (res[k].state == 2) {
                    $('#site-' + k).css('background', 'url("../../img/airCtrl/lixian.png") no-repeat center/100%').children('i').text('0');
                }
            }
        };
        //选中设备
        for (var i = 0; i < liArr.length; i++) {
            liArr[i].onclick = function () {
                if (this.getAttribute('data-state') == '0') {
                    $(this).addClass('blockAfter');
                    this.setAttribute("data-state", '1');
                } else {
                    $(this).removeClass('blockAfter');
                    this.setAttribute("data-state", '0');
                }
            }
        }
        function strKT() {
            var str = '';
            for (var i = 0; i < liArr.length; i++) {
                if (liArr[i].getAttribute("data-state") == 1) {
                    str += liArr[i].id.split('-')[1] + "-";
                }
            }
            str = str.substring(0, str.length - 1);
            return str;
        }

        // 开关
        $('#kaiguan span:nth-of-type(2)').on('click', function () {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {

                // 取消选中状态
                cancelAll();

                this.setAttribute('data-toggle', '1');
                $.ajax({
                    type: "get",
                    async: true,
                    url: url + "airconditioner/switch0/" + str + '/1',
                    dataType: "json",
                    success: function (res) {
                        if (res == 1) {
                            layer.msg('打开成功')
                            // str=str.split('-');
                            // for(var i=0;i<str.length;i++){
                            //     $('#site-'+str[i]).css('background', 'url("../../img/airCtrl/yunxing.png") no-repeat center/100%');
                            // }
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        });
        $('#kaiguan span:nth-of-type(1)').on('click', function () {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                $.ajax({
                    type: "get",
                    async: true,
                    url: url + "airconditioner/switch0/" + str + '/0',
                    dataType: "json",
                    success: function (res) {
                        if (res == 1) {
                            layer.msg('关闭成功')
                            // str=str.split('-');
                            // for(var i=0;i<str.length;i++){
                            //     $('#site-'+str[i]).css('background', 'url("../../img/airCtrl/guanbi.png") no-repeat center/100%');
                            // }
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        });
        // 模式
        $('#mode span').on('click', function (e) {
            var str = strKT();
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                var e = e || window.event;
                if (e.target == $('#cold')[0]) {
                    $('#cold').addClass('bgSpan').css('background-image', 'url("../../img/airCtrl/zhilengG.png")').siblings('#hot').removeClass('bgSpan').css('background-image', 'url("../../img/airCtrl/zhire.png")');
                    this.setAttribute('data-mode', '0');
                } else if (e.target == $('#hot')[0]) {
                    $('#hot').addClass('bgSpan').css('background-image', 'url("../../img/airCtrl/zhireG.png")').siblings('#cold').removeClass('bgSpan').css('background-image', 'url("../../img/airCtrl/zhileng.png")');
                    this.setAttribute('data-mode', '1');
                }
                if (str == '') {
                    layer.msg('请选择设备或者分组');
                } else {
                    $.ajax({
                        type: "get",
                        async: true,
                        url: url + "airconditioner/mode/" + str + '/' + this.getAttribute('data-mode'),
                        dataType: "json",
                        success: function (res) {
                            layer.msg('调节成功')
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
                }
            }
        });
        // 风速
        $('#btnFS span').on('click', function () {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                $('#btnFS span').removeClass('bgSpan');
                $('#btnFS span#manfeng').css('backgroundImage', 'url(../../img/airCtrl/manfeng.png)');
                $('#zhongfeng').css('backgroundImage', 'url(../../img/airCtrl/zhongfeng.png)');
                $('#dafeng').css('backgroundImage', 'url(../../img/airCtrl/dafeng.png)');
                $('#zidong').css('backgroundImage', 'url(../../img/airCtrl/zidong.png)');
                $(this).addClass('bgSpan').css('backgroundImage', 'url(../../img/airCtrl/' + this.id + 'G.png)');
                if (str == '') {
                    layer.msg('请选择设备或者分组');
                } else {
                    $.ajax({
                        type: "get",
                        async: true,
                        url: url + "airconditioner/wind/" + str + '/' + this.getAttribute('data-btn'),
                        dataType: "json",
                        success: function (res) {
                            layer.msg('调节成功')
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
                }
            }
        });
        // 温度设定
        $('#temp .jia').on('click', function () {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                var temp = $('#temp .bgSpan').text();
                console.log(temp)
                if (temp < 30) {
                    temp++;
                    $('#temp .bgSpan').text(temp);
                    setTpl(temp);
                } else if (temp >= 30) {
                    layer.msg('最大温度30度。')
                }
            }
        });
        $('#temp .jian').on('click', function () {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                var temp = $('#temp .bgSpan').text();
                if (temp > 10) {
                    temp--;
                    $('#temp .bgSpan').text(temp);
                    setTpl(temp);
                } else if (temp <= 10) {
                    layer.msg('最小温度10度。')
                }
            }
        });
        function setTpl(temp) {
            var str = strKT();
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                $.ajax({
                    type: "get",
                    async: true,
                    url: url + "airconditioner/temperature/" + str + '/' + temp,
                    dataType: "json",
                    success: function (res) {
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        }
    };
});
