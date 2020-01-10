package com.meet.paperface;
public class Story_Model {
    
    String pictures;
    String thesis;

    public Story_Model(String pictures, String thesis) {
        this.pictures = pictures;
        this.thesis = thesis;
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
}
