'use strict';
angular.module('AmazonApp')
    .service('filesService', function ($q, $http) {

        this.getFiles = function(bucketName, success, error){
            $http({
                method: 'GET',
                url: './api/v1/buckets/' + bucketName + '/files'
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

        this.upload = function(bucketName, fd, success, error){

            $http.post('./api/v1/buckets/' + bucketName + '/files', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
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

        this.download = function(bucketName, fileName, success, error){
            $http({
                method: 'GET',
                url: './api/v1/buckets/' + bucketName + '/files/' + fileName + '/?redirect=false'
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

        this.delete = function(bucketName, fileName, success, error){
            $http({
                method: 'DELETE',
                url: './api/v1/buckets/' + bucketName + '/files/' + fileName + '/'
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