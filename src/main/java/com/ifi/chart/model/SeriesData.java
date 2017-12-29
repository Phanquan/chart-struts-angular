package com.ifi.chart.model;


import java.util.List;

public class SeriesData {
    private int id;
    private String name;
    private List<Double> data;

    public SeriesData() {
    }

    public SeriesData(int id, String name, List<Double> data) {
        this.name = name;
        this.data = data;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
