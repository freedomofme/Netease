package com.szu.androidpractice.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.szu.androidpractice.R;
import com.szu.androidpractice.ui.fragments.BaseFragment;
import com.szu.androidpractice.ui.fragments.HideToolbarOnScrollFragment;
import com.szu.androidpractice.ui.fragments.SlideMenuFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/4/6.
 */
public class SampleActivity extends BaseActivity{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private BaseFragment mFragment;
    private int mClickPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseBundle(getIntent().getExtras());
        setContentView(R.layout.activity_sample);
        ButterKnife.inject(this);
        initToolbar();
        initFragment();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    private void initToolbar(){
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getColor(R.color.action_bar_title_color));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseBundle(Bundle bundle){
        if (bundle != null){
            mClickPosition = bundle.getInt(MainActivity.ITEM_CLICK_KEY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mFragment != null){
            if (mFragment.onKeyDown(keyCode, event))
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initFragment(){
        switch (mClickPosition){
            case 0:
                mFragment = HideToolbarOnScrollFragment.newInstance();
                break;
            case 1:
                mFragment = SlideMenuFragment.newInstance();
                break;
            default:
                break;
        }
        if (mFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, mFragment).commit();
        }
    }
}
