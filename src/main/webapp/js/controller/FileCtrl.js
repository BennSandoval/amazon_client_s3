'use strict';
angular.module('AmazonApp')
    .controller('FileCtrl', ['$scope', '$window', 'bucketService', 'filesService', function ($scope, $window ,bucketService, filesService) {
        $scope.ctrlName = 'File';

        bucketService.getBuckets(function(data){
            $scope.buckets = angular.copy(data);
            console.log(data)
        }, function(error){
            console.log(error)
        });

        $scope.select = function(bucket) {

            $scope.bucketName = bucket.name;
            $scope.refreshFiles();

        }

        $scope.selectFile = function(file) {
            $scope.fileNameDelete = file;
            $scope.fileNameDownload = file;
        }

        $scope.refreshFiles = function() {
            filesService.getFiles($scope.bucketName, function(data){
                $scope.files = angular.copy(data);
            }, function(error){
                console.log(error)
            });
        }

        var fd = new FormData();
        $scope.upload = function() {

            var file = $scope.file;
            var fd = new FormData();
            fd.append('file', file);

            filesService.upload($scope.bucketName, fd, function(data){
                console.log(data);
                $scope.errorUpload = '';
                $scope.fileNameDownload = data.file;
                $scope.fileNameDelete = data.file;
                $scope.refreshFiles();

            }, function(error){
                console.log(error)
                $scope.fileNameDelete = '';
                $scope.errorUpload = error.text;
            });

        };

        $scope.download = function() {
            filesService.download($scope.bucketName, $scope.fileNameDownload ,function(data){
                console.log(data);
                $scope.createResult = angular.copy(data);
                $scope.fileNameDownload = '';
                $scope.errorDownload = '';
                $window.location.href = $scope.createResult.url;
            }, function(error){
                console.log(error)
                $scope.fileNameDownload = '';
                $scope.errorDownload = error.text;
            });
        };

        $scope.delete = function() {
            filesService.delete($scope.bucketName, $scope.fileNameDelete ,function(data){
                console.log(data);
                $scope.fileNameDelete = '';
                $scope.fileNameDownload = '';
                $scope.errorDelete = '';
                $scope.refreshFiles();
            }, function(error){
                console.log(error)
                $scope.fileNameDelete = '';
                $scope.errorDelete = error.text;
            });
        };

    }]);