package com.szu.androidpractice.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.szu.androidpractice.R;
import com.szu.androidpractice.adapter.HideToolbarOnScrollAdapter;
import com.szu.androidpractice.callback.RecyclerStateListener;
import com.szu.androidpractice.ui.BaseActivity;
import com.szu.androidpractice.ui.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/4/6.
 */
public class HideToolbarOnScrollFragment extends BaseFragment{
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Toolbar mToolbar;
    private View rootView;

    public static BaseFragment newInstance() {
        return new HideToolbarOnScrollFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_hide_toolbar_onscroll, container, false);
        ButterKnife.inject(this, rootView);
        initRecyclerView();
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HideToolbarOnScrollAdapter recyclerAdapter = new HideToolbarOnScrollAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new RecyclerStateListener() {
            @Override
            public void onHide() {
                hideToolbarAndSystemUi();
            }

            @Override
            public void onShow() {
                showToolbarAndSystemUi();
            }
        });
    }

    private void initToolbar(){
        if (mToolbar != null){
            mToolbar.setTitle(R.string.hide_toolbar_onscroll);
        }
    }

    private void hideToolbarAndSystemUi() {
        if (mToolbar == null)
            return;

        ViewPropertyAnimator.animate(mToolbar).translationY(-mToolbar.getHeight())
                .setInterpolator(new AccelerateInterpolator(1))
                .setListener(new SimpleAnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        hideSystemUI();
                    }
                });

    }

    private void showToolbarAndSystemUi() {
        if (mToolbar == null)
            return;
        ViewPropertyAnimator.animate(mToolbar).translationY(0)
                .setInterpolator(new DecelerateInterpolator(1))
                .setListener(new SimpleAnimatorListener(){
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        showSystemUI();
                    }
                });
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for(int i=0; i<20; i++) {
            itemList.add("Item "+i);
        }
        return itemList;
    }

    private void setRecyclerViewPadding(int top){
        int left = recyclerView.getPaddingLeft();
        int bottom = recyclerView.getPaddingBottom();
        int right = recyclerView.getPaddingRight();
        recyclerView.setPadding(left, top, right, bottom);
    }

    private void setToolbarPadding(int top){
        int left = mToolbar.getPaddingLeft();
        int bottom = mToolbar.getPaddingBottom();
        int right = mToolbar.getPaddingRight();
        mToolbar.setPadding(left, top, right, bottom);
    }

    public static class SimpleAnimatorListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
