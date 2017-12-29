package com.ifi.chart.action;


import com.ifi.chart.model.SeriesData;
import com.ifi.chart.service.ExelDataService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.Arrays;
import java.util.List;

@Result(type = "json")
@ParentPackage("json-default")
public class ExelDataByDayAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<SeriesData> seriesData;
    private String chartTitle;
    private String chartSubTitle;
    private List<String> chartXAxisCate;
    private String chartYAxisTitleText;


    public ExelDataByDayAction() {
        String filePath = "/home/phanquan/IdeaProjects/HighChartProject/tmp/dataByDate.xls";
        chartTitle = "WeeklyPA";
        chartSubTitle = "(W43 24/10/2016 - 30/10/2016)";
        chartXAxisCate = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        chartYAxisTitleText = "Puissance Appelee (kW)";
        seriesData = ExelDataService.getSeriesDataByDay(filePath);
    }


    @Action(value = "/getExelDataByDay")
    public String execute() {

        return SUCCESS;
    }

    public List<SeriesData> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<SeriesData> seriesData) {
        this.seriesData = seriesData;
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
}
