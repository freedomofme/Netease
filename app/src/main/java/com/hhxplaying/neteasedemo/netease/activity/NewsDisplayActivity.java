package com.hhxplaying.neteasedemo.netease.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.bean.newstext.Img;
import com.hhxplaying.neteasedemo.netease.bean.newstext.NewRoot;
import com.hhxplaying.neteasedemo.netease.bean.newstext.NewsID;
import com.hhxplaying.neteasedemo.netease.config.Global;
import com.hhxplaying.neteasedemo.netease.factory.RequestSingletonFactory;
import com.hhxplaying.neteasedemo.netease.util.NeteaseURLParse;
import com.hhxplaying.neteasedemo.netease.util.URLImageParser;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by HHX on 15/9/10.
 */
public class NewsDisplayActivity extends AppCompatActivity {
    private SystemBarTintManager tintManager;
    private Context context;
    private TextView content;
    private TextView title;
    private TextView authorAndTime;
    private String link;
    private final String template = "<p><img src='LINK'/></p>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_news_display);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(" ");
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);
        }


        String html = "Hello \n" +
                "<img src='http://ww1.sinaimg.cn/mw600/4dc7b570jw1drn1o8mrp0j.jpg' />" +
                " This is a test \n" +
                "<img src='http://att.bbs.duowan.com/forum/201311/01/0950172al0qkazlh20hh9n.png'/>";

        String htmlTest = "<p>　　在众多安卓手机中，Nexus系列一贯被视为Google的“亲儿子”，但其实只有设计来自Google，代工生产还是交给其他厂商，包括LG、HTC、三星、华为、摩托罗拉等等。<\\/p>" +
                "<p>　　不过有传闻称，Google打算完全自己玩儿了，因为一则iPhone在高端市场上不断蚕食市场份额，二则Nexus现在本身的表现也越来越不好：销售渠道过于狭窄，缺乏运营商合作，新的Nexus 6P/5X定位太高影响销售……<\\/p>" +
                "<p>　　Google CEO Sundar Pichai已经向员工和一些外部人士透露，计划将Nexus系列完全掌控在自己手中，从设计到生产都一手负责，不再依赖其他手机厂商，就像Pixel C笔记本那样变成纯粹的Google产品。<\\/p>" +
                "<p>　　这样一来，Nexus设备也不会再冠以其他厂商的牌子，只打Google自己的标识。<\\/p>" +
                "<p>　　虽然Google没有透露该计划的具体细节和执行时间，但是据了解，HTC内部人士对于Google的这种做法并不意外，HTC也可能成为最后一个代工Nexus的第三方厂商。<\\/p>" +
                "<p>　　此前有消息称，HTC今年将独自代工两款Nexus手机，分别为5.0英寸、5.5英寸。<\\/p><!--IMG#0-->";

        String body = htmlTest.replace("<!--IMG#0-->", template.replace("LINK", "http://img1.cache.netease.com/catchpic/5/59/59F9EB30B047D22DAD5F12B14DB4682E.jpg"));



        content = (TextView)findViewById(R.id.tv_content);
        title = (TextView)findViewById(R.id.tv_newstitle);
        authorAndTime = (TextView)findViewById(R.id.tv_author_time);

//        URLImageParser p = new URLImageParser(content, this);
//        Spanned htmlSpan = Html.fromHtml(body, p, null);
//        content.setText(htmlSpan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           link = extras.getString("NEWS_LINK");
        }
        getNews(link);

    }

    private void getNews(final String link) {
        MySingleton.getInstance(context.getApplicationContext()).getRequestQueue().add(
                RequestSingletonFactory.getInstance().getGETStringRequest(context, link,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                JSONObject obj;
                                try {
                                    String id = NeteaseURLParse.getNewsID(link);
                                    String hold = response.toString().replace(id, "newsID");
                                    obj = new JSONObject(hold.toString());

                                    NewRoot newRoot = new Gson().fromJson(obj.toString(), Global.NewRoot);

                                    Log.i("RVA", "response: " + response.toString());
                                    Log.i("RVA", "newRoot: " + newRoot.toString());

                                    updateViewFromJSON(newRoot);

                                } catch (JSONException | JsonParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));

    }


    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.tab_top_background));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateViewFromJSON(NewRoot newRoot) {
        NewsID hold = newRoot.getNewsID();
        //设置标题
        title.setText(hold.getTitle());

        //设置作者和时间
        int first = hold.getPtime().indexOf("-");
        int last = hold.getPtime().lastIndexOf(":");
        authorAndTime.setText(hold.getSource() + "    " + hold.getPtime().substring(first + 1, last));

        //设置正文
        String body = hold.getBody();
        for (Img img : hold.getImg()) {
            body = body.replace(img.getRef(), template.replace("LINK", img.getSrc()));
        }

        Log.i("RVA", "设置body： " + body);
        URLImageParser p = new URLImageParser(content, this);
        Spanned htmlSpan = Html.fromHtml(body, p, null);
        content.setText(htmlSpan);
        content.setTextSize(18);


    }
}
