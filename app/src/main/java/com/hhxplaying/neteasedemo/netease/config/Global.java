package com.hhxplaying.neteasedemo.netease.config;

import com.google.gson.reflect.TypeToken;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HHX on 15/8/21.
 */
public class Global {
    public static Type NewsItemType = new TypeToken<ArrayList<OneNewsItemBean>>() {
    }.getType();


    HashMap<String,OneNewsList> oneNewsListBeanHashMap = new HashMap<>();

    public OneNewsList getNewsListByKey(String key) {
        return oneNewsListBeanHashMap.get(key);
    }

    public HashMap<String, OneNewsList> getOneNewsListBeanHashMap() {
        return oneNewsListBeanHashMap;
    }

    public void setOneNewsListBeanHashMap(HashMap<String, OneNewsList> oneNewsListBeanHashMap) {
        this.oneNewsListBeanHashMap = oneNewsListBeanHashMap;
    }
}
