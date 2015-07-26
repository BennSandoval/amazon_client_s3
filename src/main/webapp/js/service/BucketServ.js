'use strict';
angular.module('AmazonApp')
    .service('bucketService', function ($q, $http) {

        this.getBuckets = function(success, error){
            $http({
                method: 'GET',
                url: './api/v1/buckets'
            }).success(function (data) {
                if(success != undefined){
                    success(data)
                }
            }).error(function (data) {
                if(error != undefined){
                    error(data)
                }
            });
        }

        this.createBucket = function(bucketName, success, error){
            $http({
                method: 'POST',
                url: './api/v1/buckets/' + bucketName
            }).success(function (data) {
                if(success != undefined){
                    success(data)
                }
            }).error(function (data) {
                if(error != undefined){
                    error(data)
                }
            });
        }

        this.deleteBucket = function(bucketName, success, error){
            $http({
                method: 'DELETE',
                url: './api/v1/buckets/' + bucketName
            }).success(function (data) {
                if(success != undefined){
                    success(data)
                }
            }).error(function (data) {
                if(error != undefined){
                    error(data)
                }
            });
        }

    });