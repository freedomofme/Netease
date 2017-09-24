package com.hhxplaying.neteasedemo.netease.bean.imageextra;

import java.io.Serializable;

/**
 * Created by HHX on 15/9/12.
 */
public class Photos implements Serializable {
    private String timgurl = "";

    private String photohtml = "";

    private String newsurl = "";

    private String squareimgurl = "";

    private String cimgurl = "";

    private String imgtitle = "";

    private String simgurl = "";

    private String note = "";

    private String photoid = "";

    private String imgurl = "";

    public void setTimgurl(String timgurl){
        this.timgurl = timgurl;
    }
    public String getTimgurl(){
        return this.timgurl;
    }
    public void setPhotohtml(String photohtml){
        this.photohtml = photohtml;
    }
    public String getPhotohtml(){
        return this.photohtml;
    }
    public void setNewsurl(String newsurl){
        this.newsurl = newsurl;
    }
    public String getNewsurl(){
        return this.newsurl;
    }
    public void setSquareimgurl(String squareimgurl){
        this.squareimgurl = squareimgurl;
    }
    public String getSquareimgurl(){
        return this.squareimgurl;
    }
    public void setCimgurl(String cimgurl){
        this.cimgurl = cimgurl;
    }
    public String getCimgurl(){
        return this.cimgurl;
    }
    public void setImgtitle(String imgtitle){
        this.imgtitle = imgtitle;
    }
    public String getImgtitle(){
        return this.imgtitle;
    }
    public void setSimgurl(String simgurl){
        this.simgurl = simgurl;
    }
    public String getSimgurl(){
        return this.simgurl;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getNote(){
        return this.note;
    }
    public void setPhotoid(String photoid){
        this.photoid = photoid;
    }
    public String getPhotoid(){
        return this.photoid;
    }
    public void setImgurl(String imgurl){
        this.imgurl = imgurl;
    }
    public String getImgurl(){
        return this.imgurl;
    }

    @Override
    public String toString() {
        return "photos{" +
                "cimgurl='" + cimgurl + '\'' +
                ", timgurl='" + timgurl + '\'' +
                ", photohtml='" + photohtml + '\'' +
                ", newsurl='" + newsurl + '\'' +
                ", squareimgurl='" + squareimgurl + '\'' +
                ", imgtitle='" + imgtitle + '\'' +
                ", simgurl='" + simgurl + '\'' +
                ", note='" + note + '\'' +
                ", photoid='" + photoid + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
