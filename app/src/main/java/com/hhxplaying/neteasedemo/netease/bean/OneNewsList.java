package com.hhxplaying.neteasedemo.netease.bean;

import java.util.ArrayList;

/**
 * Created by HHX on 15/9/9.
 */
public class OneNewsList {
    String newsType;
    OneNewsItemBean switchBannerBean;
    ArrayList<OneNewsItemBean> newsList;

    public ArrayList<OneNewsItemBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<OneNewsItemBean> newsList) {
        this.newsList = newsList;
    }

    public OneNewsItemBean getSwitchBannerBean() {
        return switchBannerBean;
    }

    public void setSwitchBannerBean(OneNewsItemBean switchBannerBean) {
        this.switchBannerBean = switchBannerBean;
    }
}
