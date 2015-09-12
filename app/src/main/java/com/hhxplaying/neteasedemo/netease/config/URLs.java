package com.hhxplaying.neteasedemo.netease.config;

import java.util.HashMap;

/**
 * Created by HHX on 15/8/20.
 * 下次心情好用反射做个解耦，哎，现在突然想到前女友了，纠结
 */
public class URLs {
    public static final String INDEX_URL = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
//    public static final String INDEX_TAG = "T1348647909107";
    public static final String IMAGE_JSON_URL = "http://c.3g.163.com/photo/api/set/";

    public static final String host = "http://c.m.163.com/";
    public static final String PRE_URL = host + "nc/article/headline/";
    public static final String POS_URL = "/0-20.html";
    public static final HashMap<String, String> hashMap = new HashMap<String, String>();

    //广州
    public static final String GuangZhouId = "http://c.m.163.com/nc/article/local/5bm/5bee/0-20.html";
    //头条
    public static final String toutiaoId = "T1348647909107";
    // 足球
    public static final String FootId = "T1399700447917";
    // 娱乐
    public static final String YuLeId = "T1348648517839";
    // 体育
    public static final String TiYuId = "T1348649079062";
    // 财经
    public static final String CaiJingId = "T1348648756099";
    // 科技
    public static final String KeJiId = "T1348649580692";
    // 电影
    public static final String DianYingId = "T1348648650048";
    // 汽车
    public static final String QiChiId = "T1348654060988";
    // 笑话
    public static final String XiaoHuaId = "T1350383429665";
    // 笑话
    public static final String YouXiId = "T1348654151579";
    // 时尚
    public static final String ShiShangId = "T1348650593803";
    // 情感
    public static final String QingGanId = "T1348650839000";
    // 精选
    public static final String JingXuanId = "T1370583240249";
    // 电台
    public static final String DianTaiId = "T1379038288239";
    // nba
    public static final String NBAId = "T1348649145984";
    // 数码
    public static final String ShuMaId = "T1348649776727";
    // 数码
    public static final String YiDongId = "T1351233117091";
    // 彩票
    public static final String CaiPiaoId = "T1356600029035";
    // 教育
    public static final String JiaoYuId = "T1348654225495";
    // 论坛
    public static final String LunTanId = "T1349837670307";
    // 旅游
    public static final String LvYouId = "T1348654204705";
    // 手机
    public static final String ShouJiId = "T1348649654285";
    // 博客
    public static final String BoKeId = "T1349837698345";
    // 社会
    public static final String SheHuiId = "T1348648037603";
    // 家居
    public static final String JiaJuId = "T1348654105308";
    // 暴雪游戏
    public static final String BaoXueId = "T1397016069906";
    // 亲子
    public static final String QinZiId = "T1397116135282";
    // CBA
    public static final String CBAId = "T1348649475931";
    // 消息
    public static final String MsgId = "T1371543208049";
    //评论
    public static final String CommonUrl = host + "nc/article/list/";

    static{
        hashMap.put("0", toutiaoId);
        hashMap.put("1", KeJiId);
        hashMap.put("2", TiYuId);
        hashMap.put("3", KeJiId);
        hashMap.put("4", CaiJingId);
    }

    public static String getUrl(int key) {
        return PRE_URL + hashMap.get(key + "") + POS_URL;
    }

    //截取T字母开始的一段
    public static String getUrlTag(int key) {
        return hashMap.get(key + "");
    }

}
