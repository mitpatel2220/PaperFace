package com.meet.paperface.model;
public class Story_Model {
    
    String pictures;
    String thesis;
    String name;

    public Story_Model(String pictures, String thesis,String name) {
        this.pictures = pictures;
        this.thesis = thesis;
        this.name = name;


    }
    
    public Story_Model(){
        
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
