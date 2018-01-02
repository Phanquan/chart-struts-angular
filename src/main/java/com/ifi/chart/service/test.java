package com.ifi.chart.service;

import com.ifi.chart.model.SeriesData;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static final String filePath = "/home/phanquan/IdeaProjects/HighChartProject/tmp/dataByHour.xls";

    public static void main(String[] args) {
        List<List<List<SeriesData>>> abc = ExelDataService.getSeriesDataByHour(filePath);
        System.out.print(abc);

    }
}
