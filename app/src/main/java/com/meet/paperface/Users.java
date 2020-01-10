package com.meet.paperface;

public class Users {

    private String hostelname,mobileno,name,other,payment,roomno,totalpage,totalrs;


    public Users() {
    }

    public Users(String hostelname, String mobileno, String name, String other, String payment, String roomno, String totalpage, String totalrs) {
        this.hostelname = hostelname;
        this.mobileno = mobileno;
        this.name = name;
        this.other = other;
        this.payment = payment;
        this.roomno = roomno;
        this.totalpage = totalpage;
        this.totalrs = totalrs;
    }


    public String getHostelname() {
        return hostelname;
    }

    public void setHostelname(String hostelname) {
        this.hostelname = hostelname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public String getTotalrs() {
        return totalrs;
    }

    public void setTotalrs(String totalrs) {
        this.totalrs = totalrs;
    }
}
