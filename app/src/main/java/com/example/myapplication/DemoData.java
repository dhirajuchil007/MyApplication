package com.example.myapplication;

public class DemoData {
    String date,sensex;
    int equity;
    String point;

    public DemoData(String date, String sensex, int equity, String point) {
        this.date = date;
        this.sensex = sensex;
        this.equity = equity;
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSensex() {
        return sensex;
    }

    public void setSensex(String sensex) {
        this.sensex = sensex;
    }

    public int getEquity() {
        return equity;
    }

    public void setEquity(int equity) {
        this.equity = equity;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
