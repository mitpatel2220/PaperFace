package com.meet.paperface.model;

public class Cart_model {
    private String page,rs,date,type;

    public Cart_model() {

    }
    public Cart_model(String date, String page, String rs, String type) {
        this.date = date;
        this.page = page;
        this.rs = rs;
        this.type = type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
