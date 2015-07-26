<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>

    <base href="/"/>
    <title>Amazon App</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

</head>
<body class="ng-cloak" ng-app="AmazonApp">
<!-- Top navigation -->
<div class="navbar-wrapper" ng-controller="IndexCtrl">

</div>
<%-- Content --%>
<div id="main-content" class="container-fluid padding-0">
    <section class="container-fluid">
        <div class="col-xs-12">
            <div class="" ng-view=""></div>
        </div>
    </section>
</div>

<%-- External Libs --%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-resource.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-route.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<%-- Libs --%>
<script rel="script" type="text/javascript" src="js/app.js"></script>
<%-- Controllers --%>
<script rel="script" type="text/javascript" src="js/controller/HomeCtrl.js"></script>
<script rel="script" type="text/javascript" src="js/controller/BucketCtrl.js"></script>
<script rel="script" type="text/javascript" src="js/controller/FileCtrl.js"></script>
<%-- Services --%>
<script rel="script" type="text/javascript" src="js/service/BucketServ.js"></script>
<script rel="script" type="text/javascript" src="js/service/FileServ.js"></script>

</body>
</html>