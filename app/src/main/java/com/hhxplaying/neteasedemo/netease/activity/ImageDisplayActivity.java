package com.hhxplaying.neteasedemo.netease.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.util.NeteaseURLParse;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;
import com.hhxplaying.neteasedemo.netease.widget.SwitchImage;

/**
 * Created by HHX on 15/9/11.
 */
public class ImageDisplayActivity extends AppCompatActivity {
    SwitchImage viewPager;
    PhotoSet photoSet;
    int selectedIndex;
    int defaultImage = R.drawable.load_fail_small;
    int failImage = R.drawable.load_fail_black;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_icon);//设置导航按钮

        viewPager = (SwitchImage) findViewById(R.id.fragment_tabmain_viewPager);

        photoSet = (PhotoSet) getIntent().getSerializableExtra("photoSet");
        selectedIndex = (int) getIntent().getIntExtra("imageIndex", 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        initPageInf();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_image, menu);
        return true;
    }

    private void initPageInf() {
        if (photoSet == null)  {
            viewPager.initPager(new int[]{failImage}, new String[]{"0"}, new String[]{"0"});
            return ;
        }
        int size = photoSet.getPhotos().size();
        int[] defaultImages = new int[size];
        String[] imageURLs = new String[size];
        String[] imageTexts = new String[size];
         for (int i = 0; i < size; i++) {
             defaultImages[i] = defaultImage;
             imageURLs[i] = NeteaseURLParse.parseWebpImageForImageDetaiPage(photoSet.getPhotos().get(i).getImgurl());
             imageTexts[i] = photoSet.getPhotos().get(i).getImgtitle();
        }
        viewPager.initPager(defaultImages, imageURLs, imageTexts);

        viewPager.setAndLoadImage(new SwitchImage.DisplayImageView() {
            @Override
            public void displayImageFromURL(ImageView view, String url) {
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(view,
                        defaultImage, failImage);
                MySingleton.getInstance(mContext).getImageLoader().get(url,
                        listener, 1000, 500);
            }
        });
        viewPager.setPagePosition(selectedIndex);
    }


}
