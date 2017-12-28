package com.ifi.chart.model;

public class ExelData {
    private String datepa;
    private String pa;
    private String ps;

    public ExelData(String datepa, String pa, String ps) {
        this.datepa = datepa;
        this.pa = pa;
        this.ps = ps;
    }

    public String getDatepa() {
        return datepa;
    }

    public void setDatepa(String datepa) {
        this.datepa = datepa;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
