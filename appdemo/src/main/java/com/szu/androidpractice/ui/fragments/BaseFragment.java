package com.szu.androidpractice.ui.fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.szu.androidpractice.ui.BaseActivity;

/**
 * Created by lgp on 2015/4/6.
 */
public abstract class BaseFragment extends Fragment{
    protected BaseActivity activity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity){
            this.activity = ((BaseActivity)activity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }

    protected void hideSystemUI(){
        if (activity != null)
            activity.hideSystemUI();
    }

    protected void showSystemUI() {
        if (activity != null)
            activity.showSystemUI();
    }

    protected Object getSystemService(String service){
        if (activity != null){
            return activity.getSystemService(service);
        }
        return null;
    }

    protected ComponentName getComponentName(){
        if (activity != null){
            return activity.getComponentName();
        }
        return null;
    }

    protected int getColor(int res){
        if (res <= 0)
            throw new IllegalArgumentException("resource id can not be less 0");
        return getResources().getColor(res);
    }
}
