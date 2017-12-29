let chart;
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
        },
        series: {
            cursor: 'pointer',
            events: {
                click: function (event) {
                    console.log(this.userOptions.id)
                }
            }
        }
    },
    xAxis: {
        crosshair: true
    },
    yAxis: {
        min: 0,
        title: {}
    },
    series: [{}]
};

chart = new Highcharts.chart(opts);




