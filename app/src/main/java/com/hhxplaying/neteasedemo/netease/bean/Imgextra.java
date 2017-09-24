package com.hhxplaying.neteasedemo.netease.bean;

/**
 * Created by HHX on 15/9/9.
 */
public class Imgextra {
    private static final long serialVersionUID = 3L;
    private String imgsrc = "";

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getImgsrc() {
        return this.imgsrc;
    }

    @Override
    public String toString() {
        return "imgextra{" +
                "imgsrc='" + imgsrc + '\'' +
                '}';
    }
}
