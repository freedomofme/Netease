package com.hhxplaying.neteasedemo.netease.config;

import com.hhxplaying.neteasedemo.netease.util.PinYinUtil;

import java.lang.reflect.Field;

/**
 * Created by HHX on 15/8/20.
 *
 */
public class URLs {
    public static final String INDEX_URL = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
//    public static final String INDEX_TAG = "T1348647909107";
    public static final String IMAGE_JSON_URL = "http://c.3g.163.com/photo/api/set/";

    public static final String WEBP_POS_URL = ".720x2147483647.75.auto.webp";
    public static final String WEBP_PRE_URL = "http://s.cimg.163.com/pi/";

    public static final String WEBP_POS_URL2 = "x2147483647&quality=75&type=webp";
    public static final String WEBP_PRE_URL2 = "http://nimg.ws.126.net/?url=";

    public static final String host = "http://c.m.163.com/";
    public static final String PRE_URL = host + "nc/article/headline/";
    public static final String POS_URL = ".html";

    public static String tabName[] = {"头条", "科技", "体育", "广州", "财经", "足球", "娱乐", "电影", "汽车", "博客", "社会", "旅游"};

    //广州
    public static final String GuangZhouId = "http://c.m.163.com/nc/article/local/5bm/5bee/0-20.html";
    //头条
    public static final String TouTiaoId = "T1348647909107";
    // 足球
    public static final String ZuQiuId = "T1399700447917";
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
    public static final String QiCheId = "T1348654060988";
    // 笑话
    public static final String XiaoHuaId = "T1350383429665";
    // 游戏
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
    // 移动
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
    public static final String BaoXueYouXiId = "T1397016069906";
    // 亲子
    public static final String QinZiId = "T1397116135282";
    // CBA
    public static final String CBAId = "T1348649475931";
    // 消息
    public static final String XiaoXiId = "T1371543208049";
    //评论
    public static final String CommonUrl = host + "nc/article/list/";


    /**
     *
     * @param key tabName字段，即新闻的标签名称
     * @param startEndIndex 起始终止索引, ex 10-20
     * @return 将要请求的url
     */
    public static String concatNewsListURL(String key, String startEndIndex) {
        return PRE_URL + getUrlTag(key) + "/" + startEndIndex +  POS_URL;
    }

    //截取T字母开始的一段
    public static String getUrlTag(String name) {
        String pinYinName = PinYinUtil.getInstance().convertAll(name);
        Object object = getFieldValue(URLs.class, pinYinName);
        if (object instanceof String) {
            return (String)object;
        }
        return "";
    }

    private static Object getFieldValue(Class aClazz, String fieldName) {
        Field field = getClassField(aClazz, fieldName);
        if (field != null) {
            try {
                return field.get(URLs.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    //反射field
    private static Field getClassField(Class aClazz, String aFieldName) {
        Field[] declaredFields = aClazz.getDeclaredFields();
        for (Field field : declaredFields) {
            // 注意：这里判断的方式，是用字符串的比较。很傻瓜，但能跑。要直接返回Field。我试验中，尝试返回Class，然后用getDeclaredField(String fieldName)，但是，失败了
            if (field.getName().toLowerCase().equals(aFieldName + "id")) {
                return field;// define in this class
            }
        }

//        Class superclass = aClazz.getSuperclass();
//        if (superclass != null) {
//            return getClassField(superclass, aFieldName);
//        }
        return null;
    }

}
