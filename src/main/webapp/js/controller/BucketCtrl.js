'use strict';
angular.module('AmazonApp')
    .controller('BucketCtrl', ['$scope', 'bucketService', function ($scope, bucketService) {
        $scope.ctrlName = 'Bucket';


        $scope.select = function(bucket) {
            $scope.bucketName = bucket.name;
        }

        $scope.getBuckets = function() {
            bucketService.getBuckets(function(data){
                $scope.buckets = angular.copy(data);
                console.log(data)
            }, function(error){
                console.log(error)
            });
        };

        $scope.create = function() {
            bucketService.createBucket($scope.bucketNameCreate ,function(data){
                console.log(data);
                $scope.createResult = angular.copy(data);
                $scope.bucketName = angular.copy($scope.bucketNameCreate);
                $scope.bucketNameCreate = '';
                $scope.errorCreate = '';
                $scope.getBuckets();
            }, function(error){
                console.log(error)
                $scope.errorCreate = error.text;
                $scope.bucketName = '';
            });
        };

        $scope.delete = function() {
            bucketService.deleteBucket($scope.bucketName , function(data){
                console.log(data);
                $scope.deleteResult = angular.copy(data);
                $scope.bucketName = '';
                $scope.errorDelete = '';
                $scope.getBuckets();
            }, function(error){
                console.log(error)
                $scope.errorDelete = error.text;
                $scope.bucketName = '';
            });
        };

        $scope.getBuckets();

    }]);