package com.hhxplaying.neteasedemo.netease.bean;

import java.util.List;

/**
 * Created by HHX on 15/9/9.
 */
public class OneNewsItemBean {
    private static final long serialVersionUID = 3L;
    private String url = "";//手机版

    private String url_3w = "";//电脑版

    private String template = "";

    private boolean hasCover = false;

    private int hasHead = -1;

    private String skipID = "";

    private int replyCount = -1;

    private String alias = "";

    private int hasImg = -1;

    private String digest = "";

    private String source = "";

    private boolean hasIcon = false;

    private String skipType = "";

    private String cid = "";

    private String docid = "";

    private String title = "";

    private int hasAD = -1;

    private int order = -1;

    private List<Imgextra> imgextra;

    private int priority = -1;

    private String lmodify = "";

    private String ename = "";

    private String tname = "";

    private String imgsrc = "";

    private List<Ads> ads;

    private String photosetID = "";

    private String ptime = "";

    private String postid = "";

    public List<Ads> getAdss() {
        return ads;
    }

    public void setAdss(List<Ads> adss) {
        this.ads = adss;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }


    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public List<Imgextra> getImgextra() {
        return imgextra;
    }

    public void setImgextras(List<Imgextra> imgextra) {
        this.imgextra = imgextra;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostid(){
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    @Override
    public String toString() {
        return "OneNewsItemBean{" +
                "adss=" + ads +
                ", template='" + template + '\'' +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", skipID='" + skipID + '\'' +
                ", replyCount=" + replyCount +
                ", alias='" + alias + '\'' +
                ", hasImg=" + hasImg +
                ", digest='" + digest + '\'' +
                ", source='" + source + '\'' +
                ", hasIcon=" + hasIcon +
                ", skipType='" + skipType + '\'' +
                ", cid='" + cid + '\'' +
                ", docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", hasAD=" + hasAD +
                ", order=" + order +
                ", imgextra=" + imgextra +
                ", priority=" + priority +
                ", lmodify='" + lmodify + '\'' +
                ", ename='" + ename + '\'' +
                ", tname='" + tname + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", photosetID='" + photosetID + '\'' +
                ", ptime='" + ptime + '\'' +
                ", postid='" + postid + '\'' +
                '}';
    }

    //    "template":"manual",
//            "hasCover":false,
//            "hasHead":1,
//            "skipID":"54GI0096|75907",
//            "replyCount":4747,
//            "alias":"Top News",
//            "hasImg":1,
//            "digest":"",
//            "hasIcon":false,
//            "skipType":"photoset",
//            "cid":"C1348646712614",
//            "docid":"9IG74V5H00963VRO_B32A2BKVzhouyitingupdateDoc",
//            "title":"泰国爆炸案嫌犯进行犯罪情景重现",
//            "hasAD":1,
//            "order":1,
//            "imgextra":[
//            {
//                "imgsrc":"http://img1.cache.netease.com/3g/2015/9/9/2015090907474963b16.jpg"
//            },
//            Object{...}
//            ],
//                    "priority":254,
//                    "lmodify":"2015-09-09 07:46:59",
//                    "ename":"androidnews",
//                    "tname":"头条",
//                    "imgsrc":"http://img2.cache.netease.com/3g/2015/9/9/20150909111304e35e4.jpg",
//                    "ads":[
//            {
//                "title":"英国约克郡绝美北极光 天空被染色",
//                    "tag":"photoset",
//                    "imgsrc":"http://img6.cache.netease.com/3g/2015/9/9/20150909080012f7575.jpg",
//                    "subtitle":"",
//                    "url":"54GI0096|75908"
//            },
//            Object{...},
//            Object{...},
//            Object{...},
//            Object{...}
}
