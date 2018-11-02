package com.hhxplaying.neteasedemo.netease.bean.imageextra;
import java.io.Serializable;
import java.util.List;
/**
 * Created by HHX on 15/9/12.
 */

public class PhotoSet implements Serializable{
    private String postid;

    private String series;

    private String clientadurl;

    private String desc;

    private String datatime;

    private String createdate;

//    private List<relatedids> relatedidss ;

    private String scover;

    private String autoid;

    private String url;

    private String creator;

    private String reporter;

    private List<Photos> photos;

    private String setname;

    private String cover;

    private String commenturl;

    private String source;

    private String settag;

    private String boardid;

    private String tcover;

    private String imgsum;

    public void setPostid(String postid){
        this.postid = postid;
    }
    public String getPostid(){
        return this.postid;
    }
    public void setSeries(String series){
        this.series = series;
    }
    public String getSeries(){
        return this.series;
    }
    public void setClientadurl(String clientadurl){
        this.clientadurl = clientadurl;
    }
    public String getClientadurl(){
        return this.clientadurl;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDatatime(String datatime){
        this.datatime = datatime;
    }
    public String getDatatime(){
        return this.datatime;
    }
    public void setCreatedate(String createdate){
        this.createdate = createdate;
    }
    public String getCreatedate(){
        return this.createdate;
    }
//    public void setRelatedids(List<relatedids> relatedids){
//        this.relatedidss = relatedids;
//    }
//    public List<relatedids> getRelatedids(){
//        return this.relatedidss;
//    }
    public void setScover(String scover){
        this.scover = scover;
    }
    public String getScover(){
        return this.scover;
    }
    public void setAutoid(String autoid){
        this.autoid = autoid;
    }
    public String getAutoid(){
        return this.autoid;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }
    public String getCreator(){
        return this.creator;
    }
    public void setReporter(String reporter){
        this.reporter = reporter;
    }
    public String getReporter(){
        return this.reporter;
    }
    public void setPhotos(List<Photos> photos){
        this.photos = photos;
    }
    public List<Photos> getPhotos(){
        return this.photos;
    }
    public void setSetname(String setname){
        this.setname = setname;
    }
    public String getSetname(){
        return this.setname;
    }
    public void setCover(String cover){
        this.cover = cover;
    }
    public String getCover(){
        return this.cover;
    }
    public void setCommenturl(String commenturl){
        this.commenturl = commenturl;
    }
    public String getCommenturl(){
        return this.commenturl;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setSettag(String settag){
        this.settag = settag;
    }
    public String getSettag(){
        return this.settag;
    }
    public void setBoardid(String boardid){
        this.boardid = boardid;
    }
    public String getBoardid(){
        return this.boardid;
    }
    public void setTcover(String tcover){
        this.tcover = tcover;
    }
    public String getTcover(){
        return this.tcover;
    }
    public void setImgsum(String imgsum){
        this.imgsum = imgsum;
    }
    public String getImgsum(){
        return this.imgsum;
    }

    @Override
    public String toString() {
        return "PhotoSet{" +
                "autoid='" + autoid + '\'' +
                ", postid='" + postid + '\'' +
                ", series='" + series + '\'' +
                ", clientadurl='" + clientadurl + '\'' +
                ", desc='" + desc + '\'' +
                ", datatime='" + datatime + '\'' +
                ", createdate='" + createdate + '\'' +
                ", scover='" + scover + '\'' +
                ", url='" + url + '\'' +
                ", creator='" + creator + '\'' +
                ", reporter='" + reporter + '\'' +
                ", photos=" + photos +
                ", setname='" + setname + '\'' +
                ", cover='" + cover + '\'' +
                ", commenturl='" + commenturl + '\'' +
                ", source='" + source + '\'' +
                ", settag='" + settag + '\'' +
                ", boardid='" + boardid + '\'' +
                ", tcover='" + tcover + '\'' +
                ", imgsum='" + imgsum + '\'' +
                '}';
    }
}