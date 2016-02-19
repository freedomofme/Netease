package com.hhxplaying.neteasedemo.netease.util;

import android.util.Log;

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

    private static final String originLink = "http://c.3g.163.com/nc/article/NEEDTOBEREPLACED/full.html";
    /*
    * 将从JSON中获取的新闻详情链接转换成一个包含完整新闻信息的JSON地址
    *@param 从Json中获取的链接
    */
    public static String webURLToMobileJSONLink(String url) {
        int firstIndex = url.lastIndexOf("/");
        int lastIndex = url.lastIndexOf(".");
        Log.i("RVA", "webURLToMobileJSONLink" + url);
        return originLink.replace("NEEDTOBEREPLACED", url.substring(firstIndex + 1, lastIndex));
    }


    /*
     * 从新闻完整链接中提取出新闻的ID
     * 从http://c.3g.163.com/nc/article/BG6RN12A00963VRO/full.html提取BG6RN12A00963VRO
     */
    public static String getNewsID(String originLink) {
        int end = originLink.lastIndexOf("/");
        int start = originLink.substring(0, end).lastIndexOf("/");
        return originLink.substring(start + 1, end);

    }


}
