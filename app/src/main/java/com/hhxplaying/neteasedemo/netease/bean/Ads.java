package com.hhxplaying.neteasedemo.netease.bean;

/**
 * Created by HHX on 15/9/9.
 */
public class Ads {
    private static final long serialVersionUID = 3L;
    private String title = "";

    private String tag = "";

    private String imgsrc = "";

    private String subtitle = "";

    private String url = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getImgsrc() {
        return this.imgsrc;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
