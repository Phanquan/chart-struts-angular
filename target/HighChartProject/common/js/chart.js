(function (angular) {
    "use strict";
    const myApp = angular.module('myApp', []);
    myApp.controller('myCtrl', function ($scope, $http) {

        $scope.seriesData;

        $scope.getAllChartData = function () {
            $http.get("getExelData")
                .then(function (res) {
                    $scope.seriesData = res.data.chartSeries;
                    genChart(res.data.chartTitle, res.data.chartSubTitle, res.data.chartXAxisCate, res.data.chartYAxisTitleText, res.data.chartSeries);
                })
        }

        $scope.toggleExelData = function (id) {
            console.log(id);

        }
    })
})(window.angular);


function genFullChart(chartTitle, chartSubtitle, chartXAxisCate, chartYAxisTitleText, chartSeries) {

    Highcharts.chart("ch-container", {
        chart: {
            type: "column"
        },
        title: {
            text: chartTitle
        },
        subtitle: {
            text: chartSubtitle
        },
        xAxis: {
            categories: chartXAxisCate,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: chartYAxisTitleText
            }
        },
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
        series: chartSeries
    })
}

function genChart(chartTitle, chartSubtitle, chartXAxisCate, chartYAxisTitleText, chartSeries) {
    let options = {
        chart: {
            renderTo: 'ch-container',
            type: 'column'
        },
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
        title: {
            text: chartTitle
        },
        subtitle: {
            text: chartSubtitle
        },
        xAxis: {
            categories: chartXAxisCate,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: chartYAxisTitleText
            }
        },
        series:chartSeries
    }

    let chart = new Highcharts.chart(options);
}

