package com.hhxplaying.neteasedemo.netease.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.util.NeteaseURLParse;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;

/**
 * Created by HHX on 15/9/11.
 */
public class ImageDisplayActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImagePagerAdapter imagePagerAdapter;
    PhotoSet photoSet;
    int selectedIndex;
    static int defaultImage = R.drawable.load_fail_small;
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

        viewPager = (ViewPager) findViewById(R.id.vp_imagepager);

        photoSet = (PhotoSet) getIntent().getSerializableExtra("photoSet");
        selectedIndex = getIntent().getIntExtra("imageIndex", 0);

        initPageInf();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_image, menu);
        return true;
    }

    private void initPageInf() {
        if (photoSet == null)  {
            return ;
        }

        int size = photoSet.getPhotos().size();
        String[] imageURLs = new String[size];
        String[] imageTexts = new String[size];
         for (int i = 0; i < size; i++) {
             imageURLs[i] = NeteaseURLParse.parseWebpImageForImageDetaiPage(photoSet.getPhotos().get(i).getImgurl());
             imageTexts[i] = photoSet.getPhotos().get(i).getImgtitle();
        }
        imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageURLs, imageTexts);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setCurrentItem(selectedIndex);
    }


    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        String[] imageUrls;
        String[] imageText;
        public ImagePagerAdapter(FragmentManager fm, String[] imageUrls, String[] imageText) {
            super(fm);
            this.imageUrls = imageUrls;
            this.imageText = imageText;
        }
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new OneImageFragment();
            Bundle args = new Bundle();
            args.putInt(OneImageFragment.IMAGE_INDEX, i);
            args.putString(OneImageFragment.IMAGE_URL, imageUrls[i]);
            args.putString(OneImageFragment.IMAGE_TEXT, imageText[i]);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "Image" + (position + 1);
        }
    }

    public static class OneImageFragment extends Fragment {

        public static final String IMAGE_INDEX = "ID";
        public static final String IMAGE_URL = "URL";
        public static final String IMAGE_TEXT = "TEXT";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_one_image, container, false);
            Bundle args = getArguments();
            final ImageView oneImage = (ImageView) rootView.findViewById(R.id.iv_one_image);

            MySingleton.getInstance(getActivity().getApplicationContext()).getImageLoader().get(args.getString(IMAGE_URL), ImageLoader.getImageListener(oneImage,
                    defaultImage, defaultImage));
            return rootView;
        }
    }

}
