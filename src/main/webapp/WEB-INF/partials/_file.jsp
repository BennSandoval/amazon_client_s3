<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-lg-8 col-lg-offset-2">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <h2 class="col-md-10">
                        <span>Amazon Basic API {{ctrlName}}</span>
                        <br>
                        <a href="/" class="btn btn-info" role="button">Back to menu</a>
                        <a href="/bucket" class="btn btn-info" role="button">Manage bucket</a>
                    </h2>
                    <div class="col-xs-6">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">List of buckets </h4>
                                </div>
                                <div class="panel-body">
                                    <div ng-repeat="bucket in buckets">
                                        <br>
                                        <a href="" ng-click="select(bucket)">{{ bucket.name }}</a>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">List of files in {{ bucketName }}</h4>
                                </div>
                                <div class="panel-body">
                                    <div ng-repeat="file in files">
                                        <br>
                                        <a href="" ng-click="selectFile(file)">{{ file }}</a>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">Upload file</h4>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="bucketNameUpload">Bucket:</label>
                                        <input type="text" class="form-control" id="bucketNameUpload" ng-model="bucketName">
                                    </div>
                                   <span class="btn-md btn-default btn-file">
                                        Browse <input type="file" file-model="file">
                                    </span>
                                    <br>
                                    <button type="button" ng-click="upload()" class="btn btn-default">Upload</button>
                                    <br>
                                    <br>
                                    {{ errorUpload }}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">Download File</h4>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="bucketNameDownload">Bucket:</label>
                                        <input type="text" class="form-control" id="bucketNameDownload" ng-model="bucketName">
                                    </div>
                                    <div class="form-group">
                                        <label for="fileNameDownload">File:</label>
                                        <input type="text" class="form-control" id="fileNameDownload" ng-model="fileNameDownload">
                                    </div>
                                    <button type="button" ng-click="download()" class="btn btn-default">Download</button>
                                    <br>
                                    <br>
                                    {{ errorDownload }}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">Delete file</h4>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="bucketNameDelete">Bucket:</label>
                                        <input type="text" class="form-control" id="bucketNameDelete" ng-model="bucketName">
                                    </div>
                                    <div class="form-group">
                                        <label for="fileNameDelete">File:</label>
                                        <input type="text" class="form-control" id="fileNameDelete" ng-model="fileNameDelete">
                                    </div>
                                    <button type="button" ng-click="delete()" class="btn btn-default">Delete</button>
                                    <br>
                                    <br>
                                    {{ errorDelete }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>