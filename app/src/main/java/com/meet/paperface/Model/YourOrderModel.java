package com.meet.paperface.Model;

public class YourOrderModel {

    private String date,page,rs;
    public YourOrderModel() {

    }
    public YourOrderModel(String date, String page, String rs) {
        this.date = date;
        this.page = page;
        this.rs = rs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
}
