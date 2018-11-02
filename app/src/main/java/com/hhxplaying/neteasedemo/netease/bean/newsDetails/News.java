package com.hhxplaying.neteasedemo.netease.bean.newsDetails;
import java.util.List;
public class News {
    private String body;

//    private List<users> users ;

//    private List<ydbaike> ydbaike ;

    private int replyCount;

//    private List<link> link ;

//    private List<votes> votes ;

    private List<Img> img ;

    private String digest;

    private List<Topiclist_news> topiclist_news ;

    private String dkeys;

    private String ec;

//    private List<topiclist> topiclist ;

    private String docid;

    private boolean picnews;

    private String title;

    private String tid;

    private String template;

    private int threadVote;

    private int threadAgainst;

//    private List<boboList> boboList ;

    private String replyBoard;

    private String source;

    private boolean hasNext;

    private String voicecomment;

//    private List<apps> apps ;

    private String ptime;

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
//    public void setUsers(List<users> users){
//        this.users = users;
////    }
//    public List<users> getUsers(){
//        return this.users;
//    }
//    public void setYdbaike(List<ydbaike> ydbaike){
//        this.ydbaike = ydbaike;
//    }
//    public List<ydbaike> getYdbaike(){
//        return this.ydbaike;
//    }
    public void setReplyCount(int replyCount){
        this.replyCount = replyCount;
    }
    public int getReplyCount(){
        return this.replyCount;
    }
//    public void setLink(List<link> link){
//        this.link = link;
//    }
//    public List<link> getLink(){
//        return this.link;
//    }
//    public void setVotes(List<votes> votes){
//        this.votes = votes;
//    }
//    public List<votes> getVotes(){
//        return this.votes;
//    }
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
//    public void setTopiclist_news(List<topiclist_news> topiclist_news){
//        this.topiclist_news = topiclist_news;
//    }
//    public List<topiclist_news> getTopiclist_news(){
//        return this.topiclist_news;
//    }
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
//    public void setTopiclist(List<topiclist> topiclist){
//        this.topiclist = topiclist;
//    }
//    public List<topiclist> getTopiclist(){
//        return this.topiclist;
//    }
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
//    public void setBoboList(List<boboList> boboList){
//        this.boboList = boboList;
//    }
//    public List<boboList> getBoboList(){
//        return this.boboList;
//    }
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
//    public void setApps(List<apps> apps){
//        this.apps = apps;
//    }
//    public List<apps> getApps(){
//        return this.apps;
//    }
    public void setPtime(String ptime){
        this.ptime = ptime;
    }
    public String getPtime(){
        return this.ptime;
    }

}