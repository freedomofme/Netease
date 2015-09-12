package com.hhxplaying.neteasedemo.netease.util;

import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.config.URLs;

/**
 * Created by HHX on 15/9/12.
 */
public class NeteaseURLParse {

//    "54GI0096|76110"  transform to http://c.3g.163.com/photo/api/set/0096/76110.json
    public static String parseJSONUrlOFPhotoset(OneNewsItemBean oneNewsItemBean) {
        if (oneNewsItemBean == null) return "";
        String photoset = oneNewsItemBean.getSkipID();
        if (photoset == null) return "";

        int index = photoset.lastIndexOf("|");
        String realUrl = "";
        if (index - 4 >= 0  && index + 6 <= photoset.length()) {
            realUrl = photoset.substring(index - 4, index + 6).replace("|", "/");
            realUrl = URLs.IMAGE_JSON_URL +  realUrl + ".json";
        }
        return realUrl;
    }


}
