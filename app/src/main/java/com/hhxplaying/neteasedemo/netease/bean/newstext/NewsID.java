package com.hhxplaying.neteasedemo.netease.bean.newstext;

/**
 * Created by netease on 16/2/19.
 */
import java.util.List;
public class NewsID {
    private String body;

    private List<String> users ;

    private int replyCount;

    private List<String> ydbaike ;

//    private List<String> link ;

    private List<String> votes ;

    private List<Img> img ;

    private String digest;

    private List<Topiclist_news> topiclist_news ;

    private String dkeys;

    private String ec;

    private List<Topiclist> topiclist ;

    private String docid;

    private boolean picnews;

    private String title;

    private String tid;

    private String template;

    private int threadVote;

    private int threadAgainst;

    private List<String> boboList ;

    private String replyBoard;

    private String source;

    private boolean hasNext;

    private String voicecomment;

//    private List<String> relative_sys ; 暂时不解析该属性，不是String类型

    private List<String> apps ;

    private String ptime;

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
    public void setUsers(List<String> users){
        this.users = users;
    }
    public List<String> getUsers(){
        return this.users;
    }
    public void setReplyCount(int replyCount){
        this.replyCount = replyCount;
    }
    public int getReplyCount(){
        return this.replyCount;
    }
    public void setYdbaike(List<String> ydbaike){
        this.ydbaike = ydbaike;
    }
    public List<String> getYdbaike(){
        return this.ydbaike;
    }
//    public void setLink(List<String> link){
//        this.link = link;
//   }
//    public List<String> getLink(){
//        return this.link;
//    }
    public void setVotes(List<String> votes){
        this.votes = votes;
    }
    public List<String> getVotes(){
        return this.votes;
    }
    public void setImg(List<Img> img){
        this.img = img;
    }
    public List<Img> getImg(){
        return this.img;
    }
    public void setDigest(String digest){
        this.digest = digest;
    }
    public String getDigest(){
        return this.digest;
    }
    public void setTopiclist_news(List<Topiclist_news> topiclist_news){
        this.topiclist_news = topiclist_news;
    }
    public List<Topiclist_news> getTopiclist_news(){
        return this.topiclist_news;
    }
    public void setDkeys(String dkeys){
        this.dkeys = dkeys;
    }
    public String getDkeys(){
        return this.dkeys;
    }
    public void setEc(String ec){
        this.ec = ec;
    }
    public String getEc(){
        return this.ec;
    }
    public void setTopiclist(List<Topiclist> topiclist){
        this.topiclist = topiclist;
    }
    public List<Topiclist> getTopiclist(){
        return this.topiclist;
    }
    public void setDocid(String docid){
        this.docid = docid;
    }
    public String getDocid(){
        return this.docid;
    }
    public void setPicnews(boolean picnews){
        this.picnews = picnews;
    }
    public boolean getPicnews(){
        return this.picnews;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTid(String tid){
        this.tid = tid;
    }
    public String getTid(){
        return this.tid;
    }
    public void setTemplate(String template){
        this.template = template;
    }
    public String getTemplate(){
        return this.template;
    }
    public void setThreadVote(int threadVote){
        this.threadVote = threadVote;
    }
    public int getThreadVote(){
        return this.threadVote;
    }
    public void setThreadAgainst(int threadAgainst){
        this.threadAgainst = threadAgainst;
    }
    public int getThreadAgainst(){
        return this.threadAgainst;
    }
    public void setBoboList(List<String> boboList){
        this.boboList = boboList;
    }
    public List<String> getBoboList(){
        return this.boboList;
    }
    public void setReplyBoard(String replyBoard){
        this.replyBoard = replyBoard;
    }
    public String getReplyBoard(){
        return this.replyBoard;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setHasNext(boolean hasNext){
        this.hasNext = hasNext;
    }
    public boolean getHasNext(){
        return this.hasNext;
    }
    public void setVoicecomment(String voicecomment){
        this.voicecomment = voicecomment;
    }
    public String getVoicecomment(){
        return this.voicecomment;
    }
//    public void setRelative_sys(List<String> relative_sys){
//        this.relative_sys = relative_sys;
//    }
//    public List<String> getRelative_sys(){
//        return this.relative_sys;
//    }
    public void setApps(List<String> apps){
        this.apps = apps;
    }
    public List<String> getApps(){
        return this.apps;
    }
    public void setPtime(String ptime){
        this.ptime = ptime;
    }
    public String getPtime(){
        return this.ptime;
    }

    @Override
    public String toString() {
        return "NewsID{" +

                ", users=" + users +
                ", replyCount=" + replyCount +
                ", ydbaike=" + ydbaike +
                ", votes=" + votes +
                ", img=" + img +
                ", digest='" + digest + '\'' +
                ", topiclist_news=" + topiclist_news +
                ", dkeys='" + dkeys + '\'' +
                ", ec='" + ec + '\'' +
                ", topiclist=" + topiclist +
                ", docid='" + docid + '\'' +
                ", picnews=" + picnews +
                ", title='" + title + '\'' +
                ", tid='" + tid + '\'' +
                ", template='" + template + '\'' +
                ", threadVote=" + threadVote +
                ", threadAgainst=" + threadAgainst +
                ", boboList=" + boboList +
                ", replyBoard='" + replyBoard + '\'' +
                ", source='" + source + '\'' +
                ", hasNext=" + hasNext +
                ", voicecomment='" + voicecomment + '\'' +
                ", apps=" + apps +
                ", ptime='" + ptime + '\'' +
                '}';
    }
}