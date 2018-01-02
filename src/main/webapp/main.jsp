<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>High Chart</title>
    <link rel="stylesheet" href="common/lib/bootstrap/dist/css/bootstrap.css">
    <script src="common/lib/highcharts/highcharts.js"></script>
    <script src="common/lib/highcharts/exporting.js"></script>
</head>

<body ng-app="exelApp">


<div class="container">
    <div ng-controller="chartCtrl" ng-init="getFullChart()">
        <div class="row">
            <h2>Hello Chart!</h2>
        </div>

    </div>
    <div class="row">
        <div class="col-12" id="ch-container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    </div>

    <div ng-controller="btnCtrl">
        <div class="row" ng-if="data" ng-repeat="sd in data">
            <button class="btn btn-primary" ng-click="toggleExelData(sd.id,sd.visible)">{{sd.name}}</button>
        </div>
        <div class="row">
            <span>
                {{data | json}}
            </span>
        </div>
    </div>

    <div class="row">
        <div class="col-12" id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    </div>

</div>


<script src="common/lib/jquery/dist/jquery.js"></script>
<script src="common/lib/angular/angular.js"></script>
<script src="common/js/default-chart.js"></script>
<script src="common/js/js.js"></script>
</body>
</html>
