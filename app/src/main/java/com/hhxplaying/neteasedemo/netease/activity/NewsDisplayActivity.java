package com.hhxplaying.neteasedemo.netease.activity;

import android.annotation.TargetApi;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.util.URLImageParser;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.w3c.dom.Text;


/**
 * Created by HHX on 15/9/10.
 */
public class NewsDisplayActivity extends AppCompatActivity implements Html.ImageGetter {
    private SystemBarTintManager tintManager;

    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_news_display);

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
        URLImageParser p = new URLImageParser(content, this);
        Spanned htmlSpan = Html.fromHtml(html, p, null);
        content.setText(htmlSpan);

    }

    @Override
    public Drawable getDrawable(String arg0) {
        // TODO Auto-generated method stub
        int id = 0;

        if(arg0.equals("addbutton.png")){
            id = R.drawable.load_fail_black;
        }

        if(arg0.equals("tu1.png")){
            id = R.drawable.load_fail_black;
        }
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(id);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        return d;
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
}
