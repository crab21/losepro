define(['angular', 'require', 'angular-route', 'jquery', 'echarts',  'laydate','sockjs','stomp'], function(angular, require,laydate,sockjs,stomp) {
    var app = angular.module('webapp', ['ngRoute']);
    app.config(['$routeProvider', '$controllerProvider',
        function($routeProvider, $controllerProvider) {
            $routeProvider.otherwise('lightCtrl'); //如果没有指定路由,会默认访问module1
            $routeProvider.
            when('/office', {
                controller: 'office', //这里如果写了controller,那么在加载module1.js时,js文件里面的console.log()会运行两次
                templateUrl: 'module/office/office.html',
                resolve: {
                    keyName: function($q) {
                        var deferred = $q.defer();
                        require(['module/office/office.js'], function(controller) {
                            $controllerProvider.register('office', controller); //由于是动态加载的controller，所以要先注册，再使用
                            deferred.resolve();
                        });
                        return deferred.promise;
                    }
                }
            })
                .when('/lightCtrl', {
                    controller: 'lightCtrl',
                    templateUrl: 'module/lightCtrl/lightCtrl.html',
                    resolve: {
                        keyName: function($route, $q) {
                            var deferred = $q.defer();
                            require(['module/lightCtrl/lightCtrl.js'], function(controller) {
                                $controllerProvider.register('lightCtrl', controller);
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    }
                })
                .when('/airCtrl', {
                    controller: 'airCtrl',
                    templateUrl: 'module/airCtrl/airCtrl.html',
                    resolve: {
                        keyName: function($route, $q) {
                            var deferred = $q.defer();
                            require(['module/airCtrl/airCtrl.js'], function(controller) {
                                $controllerProvider.register('airCtrl', controller);
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    }
                })
                .when('/videoCtrl', {
                    controller: 'videoCtrl',
                    templateUrl: 'module/videoCtrl/videoCtrl.html',
                    resolve: {
                        keyName: function($route, $q) {
                            var deferred = $q.defer();
                            require(['module/videoCtrl/videoCtrl.js'], function(controller) {
                                $controllerProvider.register('videoCtrl', controller);
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    }
                })
                .when('/conputerCtrl', {
                    controller: 'conputerCtrl',
                    templateUrl: 'module/conputerCtrl/conputerCtrl.html',
                    resolve: {
                        keyName: function($route, $q) {
                            var deferred = $q.defer();
                            require(['module/conputerCtrl/conputerCtrl.js'], function(controller) {
                                $controllerProvider.register('conputerCtrl', controller);
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    }
                })
        }
    ])
        .run(['$rootScope', '$location', function($rootScope, $location) {
			/* 监听路由的状态变化 */
            $rootScope.$on('$routeChangeSuccess', function(evt, current, previous) {
                // console.log('route have already changed ：'+$location.path());


            });
        }])

    app.config(function($controllerProvider, $compileProvider, $filterProvider, $provide) {
        app.register = {
            //得到$controllerProvider的引用
            controller: $controllerProvider.register,
            //同样的，这里也可以保存directive／filter／service的引用
            directive: $compileProvider.directive,
            filter: $compileProvider.register,
            service: $provide.service
        };
    })

    return app;
});