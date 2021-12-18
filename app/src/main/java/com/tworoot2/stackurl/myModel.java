package com.tworoot2.stackurl;

public class myModel {

    private long ID;
    private String link;
    private int image;

    public myModel(){

    }

    public myModel(String link, int image) {
        this.link = link;
        this.image = image;
    }

    public myModel(String link) {
        this.link = link;
    }

    public myModel(long ID,String link, int image) {
        this.ID = ID;
        this.link = link;
        this.image = image;
    }

    public myModel(long ID, String link){
        this.ID = ID;
        this.link = link;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
