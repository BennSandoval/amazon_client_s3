'use strict';
var amazonApp = angular.module('AmazonApp', [
    'ngResource',
    'ngRoute'
]);

amazonApp.controller('IndexCtrl', ['$scope', function ($scope) {
    $scope.name = '';
}]);

amazonApp.config(
    function ($routeProvider, $locationProvider) {
        /**
         * TODO
         * Check RouterController.Java
         */
        $locationProvider.html5Mode(true);

        $routeProvider.
            when('/', {
                templateUrl: 'partials/_home',
                controller: 'HomeCtrl'
            }).
            when('/bucket', {
                templateUrl: 'partials/_bucket',
                controller: 'BucketCtrl'
            }).
            when('/files', {
                templateUrl: 'partials/_file',
                controller: 'FileCtrl'
            }).
            otherwise({
                redirectTo: '/'
            });
    }
);

amazonApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);