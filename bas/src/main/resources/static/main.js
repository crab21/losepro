var config = {
    baseUrl: './',           //依赖相对路径
    paths: {                    //如果某个前缀的依赖不是按照baseUrl拼接这么简单，就需要在这里指出
        'angular': 'lib/angular',
        'app':'app',
        'angular-route':'lib/angular-route',
        'jquery':'lib/jquery',
        'echarts':'lib/echarts',
        'common':'module/common',
        'laydate':'lib/laydateNew/laydate/laydate',
        'layer':'lib/layer/layer',
        'sockjs':'lib/sockjs',
        'stomp':'lib/stomp',
        'layer':'lib/layer'

    },
    shim: {
        'angular': {
            exports: 'angular'
        },//引入没有使用requirejs模块写法的类库。例如underscore这个类库，本来会有一个全局变量'_'。这里shim等于快速定义一个模块，把原来的全局变量'_'封装在局部，并导出为一个exports，变成跟普通requirejs模块一样
        'angular-route': {
            deps:['angular'],
            exports: 'angular-route'   //依赖什么模块
        },
        'jquery': {
            exports: 'jquery'
        },
        'echarts': {
            exports: 'echarts'
        },
        'common': {
            deps:['jquery'],
            exports: 'common'
        },
        'laydate':{
            deps:['jquery'],
            exports: 'laydate'
        },
        'layer':{
            deps:['jquery'],
            exports: 'layer'
        },
        'sockjs':{
            deps:['jquery'],
            exports: 'sockjs'
        },
        'stomp':{
            deps:['jquery'],
            exports: 'stomp'
        }
    }
};
require.config(config);
require(['angular','app'],function(angular){
    angular.bootstrap(document, ['webapp'])//这里会去执行app.js这个文件
})
