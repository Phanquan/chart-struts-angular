let app = angular.module('exelApp', []);
let opts = {
    chart: {
        renderTo: 'ch-container',
        type: 'column'
    },
    title: {},
    subtitle: {},
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b>{point.y:.1f} kW</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    xAxis: {
        crosshair: true
    },
    yAxis: {
        min: 0
    },
    series: [{}]
};
let hc = Highcharts;

app.controller('chartCtrl', function ($scope, $rootScope, $http) {
    $scope.checkClick = true;
    $scope.getFullChart = function () {
        $http.get("getExelData")
            .then(function (res) {
                $scope.data = res.data;
                $scope.chartSeriesData = res.data.chartSeries;
                console.log($scope.chartSeriesData)

                opts.title.text = $scope.data.chartTitle;
                opts.subtitle.text = $scope.data.chartSubtitle;
                opts.xAxis.categories = $scope.data.chartXAxisCate;
                opts.yAxis.title = $scope.data.chartYAxisTitleText;
                opts.series = $scope.data.chartSeries;

                let chart = hc.chart(opts);
                // chart.series[1].hide();

                ($scope.emitData = function () {
                    $rootScope.$broadcast("emitChartData", $scope.chartSeriesData);
                })();


                $rootScope.$on("emitHideData", function (e, id) {
                    if($scope.checkClick){
                        chart.series[id].hide();
                        $scope.checkClick = !$scope.checkClick;
                    } else {
                        chart.series[id].show();
                        $scope.checkClick = !$scope.checkClick;
                    }
                })
            })
    }
})

app.controller('btnCtrl', function ($scope, $rootScope) {
    $scope.data = [];
    $rootScope.$on("emitChartData", function (e, data) {
        $scope.data = [...data];
    })

    $scope.toggleExelData = function(id){
        console.log(id);
        ($scope.hideData = function(){
            $rootScope.$broadcast("emitHideData",id);
        })();
    }

})
