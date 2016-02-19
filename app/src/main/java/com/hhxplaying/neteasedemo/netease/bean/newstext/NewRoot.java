package com.hhxplaying.neteasedemo.netease.bean.newstext;

import com.hhxplaying.neteasedemo.netease.bean.newsDetails.NewsRoot;

/**
 * Created by netease on 16/2/19.
 */
public class NewRoot {
    private NewsID newsID;

    public void setNewsID(NewsID newsID){
        this.newsID = newsID;
    }
    public NewsID getNewsID(){
        return this.newsID;
    }

    @Override
    public String toString() {
        return "NewRoot{" +
                "newsID=" + newsID +
                '}';
    }
}