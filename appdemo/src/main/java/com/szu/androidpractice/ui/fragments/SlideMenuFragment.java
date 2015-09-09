package com.szu.androidpractice.ui.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.szu.androidpractice.R;
import com.szu.androidpractice.adapter.DrawerListAdapter;
import com.szu.androidpractice.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/4/13.
 */
public class SlideMenuFragment extends BaseFragment{

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.left_drawer)
    ListView mDrawerMenuListView;

    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private int mCurrentTitlePosition = 0;

    public static BaseFragment newInstance() {
        return new SlideMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity){
            mToolbar = ((BaseActivity)activity).getToolbar();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide_menu, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    private void init(){
        final List<String> list = new ArrayList<>();
        list.add("Spring");
        list.add("Summer");
        list.add("Autumn");
        list.add("Winter");
        DrawerListAdapter adapter = new DrawerListAdapter(getActivity(), list);
        mDrawerMenuListView.setAdapter(adapter);
        mDrawerMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentTitlePosition = position;
                mDrawerMenuListView.setItemChecked(position, true);
                mDrawerLayout.closeDrawer(parent);

            }
        });
        mDrawerMenuListView.setItemChecked(mCurrentTitlePosition, true);
        setTitle(list.get(mCurrentTitlePosition));
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
                setTitle(R.string.slide_menu);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                activity.invalidateOptionsMenu();
                setTitle(list.get(mCurrentTitlePosition));
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(getColor(R.color.window_background));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDrawerToggle.syncState();
        if (mToolbar != null){
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOrCloseDrawer();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_slide, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.slide_left:
                setMenuListViewGravity(Gravity.START);
                return true;
            case R.id.slide_right:
                setMenuListViewGravity(Gravity.END);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mDrawerLayout.isDrawerOpen(mDrawerMenuListView)){
            mDrawerLayout.closeDrawer(mDrawerMenuListView);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void openOrCloseDrawer() {
        if (mDrawerLayout.isDrawerOpen(mDrawerMenuListView)) {
            mDrawerLayout.closeDrawer(mDrawerMenuListView);
        } else {
            mDrawerLayout.openDrawer(mDrawerMenuListView);
        }
    }

    private void setTitle(int res){
        if (mToolbar != null){
            mToolbar.setTitle(res);
        }
    }

    private void setTitle(CharSequence title){
        if (mToolbar != null){
            mToolbar.setTitle(title);
        }
    }

    private void setMenuListViewGravity(int gravity){
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mDrawerMenuListView.getLayoutParams();
        params.gravity = gravity;
        mDrawerMenuListView.setLayoutParams(params);
    }
}
