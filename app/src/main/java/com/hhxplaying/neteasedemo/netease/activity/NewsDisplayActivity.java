package com.hhxplaying.neteasedemo.netease.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
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
import org.w3c.dom.Text;


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

        content = (TextView)findViewById(R.id.tv_content);
        title = (TextView)findViewById(R.id.tv_newstitle);
        authorAndTime = (TextView)findViewById(R.id.tv_author_time);

        URLImageParser p = new URLImageParser(content, this);
        Spanned htmlSpan = Html.fromHtml(html, p, null);
        content.setText(htmlSpan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           link = extras.getString("NEWS_LINK");
        }
        getNews(link);

    }

    private void getNews(final String link) {
        MySingleton.getInstance(context).getRequestQueue().add(
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
                                    Log.i("RVA", response.toString());
                                    Log.i("RVA", newRoot.toString());

                                    updateViewFromJSON(newRoot);

                                } catch (JSONException e) {
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
        title.setText(hold.getTitle());

        int first = hold.getPtime().indexOf("-");
        int last = hold.getPtime().lastIndexOf(":");
        authorAndTime.setText(hold.getSource() + "    " + hold.getPtime().substring(first + 1, last));

        

    }
}
