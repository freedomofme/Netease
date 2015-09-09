package com.szu.androidpractice.callback;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lgp on 2015/4/6.
 */
public abstract class RecyclerStateListener extends RecyclerView.OnScrollListener {

    private static final int HIDE_THRESHOLD = 100;

    private int mScrolledDistance = 0;
    private boolean mControlsVisible = true;
    private boolean isFirstVisible;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItem == 0) {
            isFirstVisible = true;
            if(!mControlsVisible) {
                onShow();
                mControlsVisible = true;
            }
        } else {
            isFirstVisible = false;
            if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) {
                onHide();
                mControlsVisible = false;
                mScrolledDistance = 0;
            } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible) {
                onShow();
                mControlsVisible = true;
                mScrolledDistance = 0;
            }
        }
        if((mControlsVisible && dy>0) || (!mControlsVisible && dy<0)) {
            mScrolledDistance += dy;
        }
    }

    public boolean isFirstVisible() {
        return isFirstVisible;
    }

    public abstract void onHide();
    public abstract void onShow();
}
