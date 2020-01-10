package com.meet.paperface;

public class PastorderModel {
    private String date,pages,rs;

    public PastorderModel() {

    }
    public PastorderModel(String date, String pages, String rs) {
        this.date = date;
        this.pages = pages;
        this.rs = rs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
}
