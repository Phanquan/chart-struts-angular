<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>High Chart</title>
    <link rel="stylesheet" href="common/lib/bootstrap/dist/css/bootstrap.css">
    <script src="common/lib/highcharts/highcharts.js"></script>
    <script src="common/lib/highcharts/exporting.js"></script>
</head>

<body ng-app="myApp">


<div class="container" ng-controller="myCtrl" ng-init="getAllChartData()">
    <div class="row">
        <h2>Hello Chart!</h2>
    </div>

    <div class="row">
        <button class="btn btn-primary">Tokyo</button>
        <button class="btn btn-primary">New York</button>
        <button class="btn btn-primary">London</button>
        <button class="btn btn-primary">Berlin</button>
    </div>

    <div class="row">
        <div class="col-12" id="ch-container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    </div>

    <div class="row">
        <span>
            {{data | json}}
        </span>
    </div>
</div>


<script src="common/lib/jquery/dist/jquery.js"></script>
<script src="common/lib/angular/angular.js"></script>
<script src="common/js/default-chart.js"></script>
<script src="common/js/chart.js"></script>
</body>
</html>
