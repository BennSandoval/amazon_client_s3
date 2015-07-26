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
                        <a href="/files" class="btn btn-info" role="button">Manage files</a>
                    </h2>
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">List of buckets</h4>
                                </div>
                                <div class="panel-body">
                                    <b>Get list of bucket available</b>
                                    <br>
                                    <div ng-repeat="bucket in buckets">
                                        <br>
                                        <a href="" ng-click="select(bucket)">{{ bucket.name }}</a>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">Create bucket</h4>
                                </div>
                                <div class="panel-body">
                                    <b>Create a bucket</b>
                                    <br>
                                    <br>
                                    <div class="form-group">
                                        <label for="bucket">Bucket name:</label>
                                        <input type="text" class="form-control" id="bucket" ng-model="bucketNameCreate">
                                    </div>
                                    <button type="button" ng-click="create()" class="btn btn-default">Create</button>
                                    <br>
                                    <br>
                                    {{ errorCreate }}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="clearfix">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="uppercase h4 uppercase text-bolder">Delete bucket</h4>
                                </div>
                                <div class="panel-body">
                                    <b>Delete a bucket</b>
                                    <br>
                                    <br>
                                    <div class="form-group">
                                        <label for="bucket_delete">Bucket name:</label>
                                        <input type="text" class="form-control" id="bucket_delete" ng-model="bucketName">
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