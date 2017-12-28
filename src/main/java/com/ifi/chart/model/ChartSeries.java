package com.ifi.chart.model;


import java.util.List;

public class ChartSeries {
    private String name;
    private List<Double> data;

    public ChartSeries(){}

    public ChartSeries(String name, List<Double> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
