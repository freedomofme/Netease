package com.hhxplaying.neteasedemo.netease.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.hhxplaying.neteasedemo.netease.adapter.HorizontalImageRecyclerViewAdapter;

/**
 * Created by HHX on 15/9/14.
 */
public class MyRecyclerView extends RecyclerView {
    boolean firstTime = true;
    float x = -1;
    float y = -1;

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean temp = super.onInterceptTouchEvent(event);
        Log.d("HorizontalImage", "parent onIntercept ACTION_MOVE:" + temp);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                firstTime = true;
                x = event.getX();
                y = event.getY();
                break;
        }

        boolean isIntercept = ((HorizontalImageRecyclerViewAdapter)getAdapter()).isIntercept;

        Log.d("HorizontalImage", "parent onIntercept isIntercept:" + isIntercept);

        return temp || isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("HorizontalImage", "parent onTouchEvent" + event.getAction());
        switch (event.getAction()) {
           /* 这段代码理论是不要的，因为onInterceptTouchEvent已经处理了所有情况，这里有少数情况会触发。
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                firstTime = true;
                x = event.getX();
                y = event.getY();
                break;*/
            case MotionEvent.ACTION_MOVE:
                if (firstTime) {
                    float deltaX = Math.abs(x - event.getX());
                    float deltaY = Math.abs(y - event.getY());

                    if (deltaX > 1 || deltaY > 1) {
                        // 水平滑动距离大于两倍的垂直距离才判断为水平
                        if (deltaX > deltaY * 2) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        firstTime = false;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
                getParent().requestDisallowInterceptTouchEvent(false);
                ((HorizontalImageRecyclerViewAdapter)getAdapter()).isIntercept = false;
                break;
        }
        return super.onTouchEvent(event);

    }
}
