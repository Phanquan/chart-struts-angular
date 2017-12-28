package com.ifi.chart.action;


import com.ifi.chart.model.ChartSeries;
import com.ifi.chart.service.ExelDataService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.Arrays;
import java.util.List;

@Result(type = "json")
@ParentPackage("json-default")
public class ExelDataAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<ChartSeries> chartSeries;
    private String chartTitle;
    private String chartSubTitle;
    private List<String> chartXAxisCate;
    private String chartYAxisTitleText;


    public ExelDataAction() {
        String filePath = "/home/phanquan/IdeaProjects/HighChartProject/tmp/dataByDate.xls";
        chartTitle = "WeeklyPA";
        chartSubTitle = "(W43 24/10/2016 - 30/10/2016)";
        chartXAxisCate = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        chartYAxisTitleText = "Puissance Appelee (kW)";
        chartSeries = ExelDataService.getchartSeries(filePath);
    }


    @Action(value = "/getExelData")
    public String execute() {

        return SUCCESS;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getChartSubTitle() {
        return chartSubTitle;
    }

    public void setChartSubTitle(String chartSubTitle) {
        this.chartSubTitle = chartSubTitle;
    }

    public List<String> getChartXAxisCate() {
        return chartXAxisCate;
    }

    public void setChartXAxisCate(List<String> chartXAxisCate) {
        this.chartXAxisCate = chartXAxisCate;
    }

    public String getChartYAxisTitleText() {
        return chartYAxisTitleText;
    }

    public void setChartYAxisTitleText(String chartYAxisTitleText) {
        this.chartYAxisTitleText = chartYAxisTitleText;
    }

    public List<ChartSeries> getChartSeries() {
        return chartSeries;
    }

    public void setChartSeries(List<ChartSeries> chartSeries) {
        this.chartSeries = chartSeries;
    }
}
