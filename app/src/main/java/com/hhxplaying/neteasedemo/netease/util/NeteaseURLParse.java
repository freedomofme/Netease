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
        if (index - 4 >= 0  && index + 1 <= photoset.length()) {
            realUrl = photoset.substring(index - 4, photoset.length()).replace("|", "/");
            realUrl = URLs.IMAGE_JSON_URL +  realUrl + ".json";
        }
        return realUrl;
    }

//  http://img4.cache.netease.com/photo/0009/2015-09-13/B3CNU3VE0AI20009.jpg  transform to  http://s.cimg.163.com/pi/img4.cache.netease.com/photo/0096/2015-09-10/B35S2FCU54GI0096.jpg.720x2147483647.75.auto.webp
    public static String parseWebpImageForImageDetaiPage(String imageSrc) {
        if (imageSrc.contains("http://")) {
            System.out.println("hahahhahahahhahhahhahahaah");
            return URLs.WEBP_PRE_URL + imageSrc.substring(7) + URLs.WEBP_POS_URL;
        } else {
            return imageSrc;
        }
    }


}
