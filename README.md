# Netease

# 初步完成网易新闻Android端的阅读功能

差不多花了一周的时间，目前实现的了新闻下的包括头条、体育、娱乐的一系列的新闻展示，以及点击后进入的新闻详情展示。
###目前效果
目前效果请访问该网页：[http://www.cnblogs.com/FightForFreedom/p/4807083.html](http://www.cnblogs.com/FightForFreedom/p/4807083.html)


## 技术实现
### 框架实现 
APP总体底部的4个Fragment切换和在每个Fragment中的ViewPage切换, 采用的是LuckyJayce/ViewPagerIndicator的开源框架：地址是[https://github.com/LuckyJayce/ViewPagerIndicator](https://github.com/LuckyJayce/ViewPagerIndicator)

### 列表实现
新闻列表采用纵向RecyclerView，其中暂时划分为3种类型。

	public static enum ITEM_TYPE {
        ITEM_TYPE_BANNER,
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }
分别表示以下3种类型：
- BANNER：
![](http://images2015.cnblogs.com/blog/739642/201509/739642-20150914143937664-372953191.png)
- TEXT：
![](http://images2015.cnblogs.com/blog/739642/201509/739642-20150914143948320-1282950477.png)
- IMAGE：
![](http://images2015.cnblogs.com/blog/739642/201509/739642-20150914143954336-1896838803.png)
 
其中ITEM_TYPE_BANNER 采用SwitchImage单独控件形式（内部ViewPage实现
ITEM_TYPE_TEXT比较简单，
ITEM_TYPE_IMAGE的网易原版实现是3张图片。
### 列表改进
为了增加用户体验，决定替换掉ITEM_TYPE_IMAGE中三种图片，改为水平滑动形式。
现已实现了水平滑动和按钮点击响应：
![](http://images2015.cnblogs.com/blog/739642/201509/739642-20150914144420304-1934370299.png)


当点击任意一张图片后将调转到ImageDisplayActivity：
![](http://images2015.cnblogs.com/blog/739642/201509/739642-20150914152234539-499422592.png)

下面我将着重描述下ITEM_TYPE_IMAGE的水平滑动实现。

想到水平滑动，我想大家肯定会想到这些控件：ViewPage，Gallery，HorizontalScrollview等等。

不过，Google近来推出的RecyclerView也支持水平滑动，那就用它来试试。

首先，垂直RecyclerView嵌入水平RecyclerView比较顺利，没有出现子列表只显示一行之类的问题，同时对水平RecyclerView滑动也没有出现问题。

但是当我想对水平RecyclerView中的某张图片进行点击时，出现了onClick函数没有回调的问题，由于笔者水平和时间有限，暂时没有深究这个问题。

于是采用了onTouch函数做点击响应的回调, 做了如下简单的判断：

用户是点击图片还是水平滑动RecyclerIView

    imageView.setOnTouchListener(new View.OnTouchListener() {
      @Override
     public boolean onTouch(View v, MotionEvent event) {
      //抬起按钮时判断，之前是否滑动了,若没有滑动则响应点击事件
                    if (event.getAction() == MotionEvent.ACTION_UP && !isMoved)
                        mListener.onViewPageTouch((NetworkImageView) v, index);
                    else {
                        isMoved = false;
                    }
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        isMoved = true;
                    }
                    return true;
                }
            });

### 顶部沉浸式状态栏实现
参考了这篇文章：
[http://www.jianshu.com/p/f8374d6267ef](http://www.jianshu.com/p/f8374d6267ef)

### 数据网络请求
采用的是Volley框架，并封装了RequestSingletonFactory工厂类来方便请求。
URLs类中采用反射的方式来读取静态URL的数据。


### 接下来
- 完善新闻阅读的排版
- 增加查看新闻评论功能
- 增加用户设置界面

### 下载地址
本项目将在持续更新，更加完善，项目源码地址：
[https://github.com/freedomofme/Netease](https://github.com/freedomofme/Netease)

[APK安装包下载](http://files.cnblogs.com/files/FightForFreedom/app-release.apk)

本开源项目仅供学习，不得作为其他用途