package com.hhxplaying.neteasedemo.netease.config;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsList;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.bean.newstext.NewRoot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HHX on 15/8/21.
 */
public class Global {
    //解析新闻元素
    public static Type NewsItemType = new TypeToken<ArrayList<OneNewsItemBean>>() {
    }.getType();
    //解析image item中的（No.>3）图片JSON的URL
    public static Type NewsImageItemType = new TypeToken<PhotoSet>() {
    }.getType();

    //解析具体的新闻标题和正文
    public static Type NewRoot = new TypeToken<NewRoot>() {
    }.getType();

    public static ConcurrentHashMap<String, PhotoSet> extraImageHashMap = new ConcurrentHashMap<>();

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


    public static void longLog(String sb, String TAG) {
        if (sb.length() > 4000) {
            Log.v(TAG, "sb.length = " + sb.length());
            int chunkCount = sb.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    Log.v(TAG,sb.substring(4000 * i));
                } else {
                    Log.v(TAG,sb.substring(4000 * i, max));
                }
            }
        } else {
            Log.v(TAG, sb.toString());
        }

    }
}
