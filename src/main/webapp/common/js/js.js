let app = angular.module('exelApp', []);

app.controller('chartCtrl', function ($scope, $rootScope, $http) {
    $scope.getFullChart = function () {
        $http.get("getExelDataByDay")
            .then(function (res) {
                $scope.data = res.data;
                $scope.data.seriesData.forEach(function(item){
                    item.visible = true;
                });
                let newOptions = setOptions($scope.data);
                chart = Highcharts.chart(newOptions);
                $rootScope.$broadcast("emitChartData", $scope.data.seriesData);
            })
    }

    $rootScope.$on("emitHideData", function (e, data) {
        data.visible
            ? chart.series[data.id].hide()
            : chart.series[data.id].show();
    })
})

app.controller('btnCtrl', function ($scope, $rootScope) {
    $scope.data = [];

    $rootScope.$on("emitChartData", function (e, data) {
        $scope.data = [...data];
    });

    $scope.toggleExelData = function (id,visible) {
        let data = { id: id,visible: visible }
        $rootScope.$broadcast("emitHideData", data);
    }

})

function setOptions(data) {
    let options = opts;
    options.title.text = data.chartTitle;
    options.subtitle.text = data.chartSubTitle;
    options.xAxis.categories = data.chartXAxisCate;
    options.yAxis.title.text = data.chartYAxisTitleText;
    options.series = data.seriesData;
    return options;
}
