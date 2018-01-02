package com.ifi.chart.action;

import com.ifi.chart.model.SeriesData;
import com.ifi.chart.service.ExelDataService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.List;

@Result(type = "json")
@ParentPackage("json-default")
public class ExelDataByHourAction extends ActionSupport{

    private List<List<List<SeriesData>>> seriesDataByHour;
    private List<String> chartXAxisCate;
    private String chartTitle;
    private String chartYAxisTitleText;

    public ExelDataByHourAction() {
        String filePath = "/home/phanquan/IdeaProjects/HighChartProject/tmp/dataByHour.xls";
        chartTitle = "Hourly Chart";
        chartYAxisTitleText = "Puissance Appelee (kW)";
        chartXAxisCate = ExelDataByHourAction.genCategories();
        seriesDataByHour = ExelDataService.getSeriesDataByHour(filePath);
    }

    public static List<String> genCategories() {
        List<String> cate = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            cate.add(Integer.toString(i + 1));
        }
        return cate;
    }

    @Action(value = "/getExelDataByHour")
    public String execute() {

        return SUCCESS;
    }

    public List<List<List<SeriesData>>> getSeriesDataByHour() {
        return seriesDataByHour;
    }

    public void setSeriesDataByHour(List<List<List<SeriesData>>> seriesDataByHour) {
        this.seriesDataByHour = seriesDataByHour;
    }

    public List<String> getChartXAxisCate() {
        return chartXAxisCate;
    }

    public void setChartXAxisCate(List<String> chartXAxisCate) {
        this.chartXAxisCate = chartXAxisCate;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getChartYAxisTitleText() {
        return chartYAxisTitleText;
    }

    public void setChartYAxisTitleText(String chartYAxisTitleText) {
        this.chartYAxisTitleText = chartYAxisTitleText;
    }
}
