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
        var lightNum = document.querySelector('#lightNum');
        var ran = document.querySelector('#ran');
        var liArr = document.querySelectorAll(".map li");
        var val;
        common.seitchLight();
        ran.oninput = function () {
            lightNum.innerText = this.value + "%";
            val = this.value;
            this.style.backgroundSize = "" + val + "% 100%";
        };
        $('#choose li')[0].onclick = function () {
            if (this.getAttribute('data-li') == '0') {
                $('#choose li:nth-of-type(1)').css('background', 'url("../img/lightCtrl/quanbuxuan.png") no-repeat center/100%').next('span').text('取消');
                this.setAttribute('data-li', '1');
                common.all();
            } else {
                $(this).css('background', 'url("../img/lightCtrl/quanxuan.png") no-repeat center/100%').data('li', '1').next('span').text('全选');
                this.setAttribute('data-li', '0');
                common.cancelAll();
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
        //开关
        $('#kaiguan').on('click', function (e) {
            var ev = e || window.event;
            var target1 = ev.target || ev.srcElement;
            if (target1.nodeName.toLowerCase() == "span") {
                var str = '';
                for (var i = 0; i < liArr.length; i++) {
                    if (liArr[i].getAttribute("data-state") == 1) {
                        str += liArr[i].id.split('-')[1] + "-";
                    }
                }
                if (str == '') {
                    layer.msg('请选择设备或者分组');
                } else {

                    // 取消选中状态
                    common.cancelAll();

                    //灯光开
                    if (target1.getAttribute('name') == 'kai') {
                        this.setAttribute('data-kaiguan', '1');
                        var str1 = str.substring(0, str.length - 1);
                        $.ajax({
                            url: url + "light/switch/" + str1 + "/1",
                            async: true,
                            dataType: 'json',
                            success: function (result) {
                                if (result == 1) {
                                    layer.msg('打开成功!');
                                    // str1=str1.split('-');
                                    // for(var i=0;i<str1.length;i++){
                                    //     $('#site-'+str1[i]).css('background', 'rgb(85, 177, 255)');
                                    // }
                                } else {
                                    layer.msg('打开失败，请重试');
                                }
                            },
                            error: function () {
                                layer.msg('请求错误');
                            }
                        });
                        // }else {
                    } else if (target1.getAttribute('name') == 'guan') {
                        this.setAttribute('data-kaiguan', '0');
                        //灯光关
                        str1 = str.substring(0, str.length - 1);
                        $.ajax({
                            url: url + "light/switch/" + str1 + "/0",
                            async: true,
                            dataType: 'json',
                            success: function (result) {
                                console.log(result)
                                if (result == 1) {
                                    layer.msg('关闭成功!');
                                    // str1 = str1.split('-');
                                    // for (var i = 0; i < str1.length; i++) {
                                    //     $('#site-' + str1[i]).css('background', 'rgb(0, 0, 0)');
                                    // }
                                } else {
                                    layer.msg('操作失败');
                                }
                            },
                            error: function () {
                                layer.msg('请求失败');
                            }
                        });
                    }
                }
            }
        })

        //人感
        $('#rengan').on('click', function (e) {
            var ev = e || window.event;
            var target1 = ev.target || ev.srcElement;
            if (target1.nodeName.toLowerCase() == "span") {
                var str = '';
                for (var i = 0; i < liArr.length; i++) {
                    if (liArr[i].getAttribute("data-state") == 1) {
                        str += liArr[i].id.split('-')[1] + "-";
                    }
                }
                if (str == '') {
                    layer.msg('请选择设备或者分组');
                } else {

                    // 取消选中状态
                    common.cancelAll();

                    if (target1.getAttribute('name') == 'kai') {
                        //灯光开
                        this.setAttribute('data-kaiguan', '1');
                        var str1 = str.substring(0, str.length - 1);
                        $.ajax({
                            url: url + "light/induction/" + str1 + "/1",
                            async: true,
                            dataType: 'json',
                            success: function (result) {
                                if (result == 1) {
                                    layer.msg('打开成功!');
                                    // str1=str1.split('-');
                                    // for(var i=0;i<str1.length;i++){
                                    //     $('#site-'+str1[i]).css('background', 'rgb(85, 177, 255)');
                                    // }
                                } else {
                                    layer.msg('打开失败，请重试');
                                }
                            },
                            error: function () {
                                layer.msg('请求错误');
                            }
                        });
                        // }else {
                    } else if (target1.getAttribute('name') == 'guan') {
                        this.setAttribute('data-kaiguan', '0');
                        //灯光关
                        str1 = str.substring(0, str.length - 1);
                        $.ajax({
                            url: url + "light/induction/" + str1 + "/0",
                            async: true,
                            dataType: 'json',
                            success: function (result) {
                                console.log(result)
                                if (result == 1) {
                                    layer.msg('关闭成功!');
                                    // str1 = str1.split('-');
                                    // for (var i = 0; i < str1.length; i++) {
                                    //     $('#site-' + str1[i]).css('background', 'rgb(0, 0, 0)');
                                    // }
                                } else {
                                    layer.msg('操作失败');
                                }
                            },
                            error: function () {
                                layer.msg('请求失败');
                            }
                        });
                    }
                }
            }
        })

        //调节亮度
        ran.onchange = function () {
            var str = '';
            for (var i = 0; i < liArr.length; i++) {
                if (liArr[i].getAttribute("data-state") == 1) {
                    str += liArr[i].id.split('-')[1] + "-";
                }
            }
            if (str == '') {
                layer.msg('请选择设备或者分组');
            } else {
                var str1 = str.substring(0, str.length - 1);
                $.ajax({
                    url: url + "light/brightness/" + str1 + "/" + ran.value,
                    async: true,
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            layer.msg('调节成功');
                        } else {
                            layer.msg('调节失败，请稍后重试');
                        }
                    },
                    error: function () {
                        layer.msg('请求错误');
                    }
                });
            }

        };
        //快速调节亮度
        var shortcut = document.querySelector(".shortcut");
        shortcut.onclick = function (e) {
            var ev = e || window.event;
            var target1 = ev.target || ev.srcElement;
            if (target1.nodeName.toLowerCase() == "img") {
                var str = '';
                for (var i = 0; i < liArr.length; i++) {
                    if (liArr[i].getAttribute("data-state") == 1) {
                        str += liArr[i].id.split('-')[1] + "-";
                    }
                }
                if (str == '') {
                    layer.msg('请选择设备或者分组');
                } else {
                    var str1 = str.substring(0, str.length - 1);
                    $.ajax({
                        url: url + "light/brightness/" + str1 + "/" + target1.alt,
                        async: true,
                        dataType: 'json',
                        success: function (result) {
                            if (result) {
                                layer.msg('当前亮度为' + target1.alt);
                            } else {
                                layer.msg('调节失败，请稍后重试');
                            }
                        },
                        error: function () {
                            layer.msg('请求错误');
                        }
                    });
                }
            }
        };
        //获取所有灯的状态
        getDevice1()
        function getDevice1() {
            $.ajax({
                url: url + "light/state",
                async: true,
                dataType: 'json',
                success: function (result) {
                    if (result) {
                        for (var k in result) {
                            if (result[k] == 0) {
                                // console.log(k,result[k])
                                $('#site-' + k).css('background', 'rgb(0, 0, 0)');
                            } else if (result[k] == 1) {
                                // console.log(k,result[k])
                                $('#site-' + k).css('background', 'rgb(85, 177, 255)');
                            } else if (result[k] == 2) {
                                $('#site-' + k).css('background', 'rgb(207, 207, 207)');
                            }
                        }
                        setTimeout(getDevice1, 2000);
                    } else {
                        layer.msg('设备状态数据获取失败，请稍后重试');
                    }
                },
                error: function () {

                }
            });
        };


    };
});
