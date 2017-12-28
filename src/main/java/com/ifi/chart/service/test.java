package com.ifi.chart.service;

import com.ifi.chart.action.ExelDataAction;
import com.ifi.chart.model.ChartSeries;
import com.ifi.chart.model.ExelData;

import java.util.List;

public class test {
    public static final String filePath = "tmp/dataByDate.xls";

    public static void main(String[] args) {
        List<ChartSeries> test = ExelDataService.getchartSeries(filePath);

        System.out.print(test.get(0));
//        List<List<ExelData>> listXls = ExelDataService.readExel(filePath);
//        System.out.print(listXls);
    }
}
