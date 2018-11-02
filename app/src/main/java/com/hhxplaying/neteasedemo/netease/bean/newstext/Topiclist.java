package com.hhxplaying.neteasedemo.netease.bean.newstext;

/**
 * Created by HHX on 17/1/12.
 */

public class Topiclist {
    private boolean hasCover;

    private String subnum;

    private String alias;

    private String tname;

    private String ename;

    private String tid;

    private String cid;

    public void setHasCover(boolean hasCover){
        this.hasCover = hasCover;
    }
    public boolean getHasCover(){
        return this.hasCover;
    }
    public void setSubnum(String subnum){
        this.subnum = subnum;
    }
    public String getSubnum(){
        return this.subnum;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    public String getAlias(){
        return this.alias;
    }
    public void setTname(String tname){
        this.tname = tname;
    }
    public String getTname(){
        return this.tname;
    }
    public void setEname(String ename){
        this.ename = ename;
    }
    public String getEname(){
        return this.ename;
    }
    public void setTid(String tid){
        this.tid = tid;
    }
    public String getTid(){
        return this.tid;
    }
    public void setCid(String cid){
        this.cid = cid;
    }
    public String getCid(){
        return this.cid;
    }

}