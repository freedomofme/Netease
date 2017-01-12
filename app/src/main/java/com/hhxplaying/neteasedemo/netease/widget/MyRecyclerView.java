package com.hhxplaying.neteasedemo.netease.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean temp = super.onInterceptTouchEvent(ev);
        return temp;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                firstTime = true;
                x = -1;
                y = -1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (firstTime) {
                    if (x == -1) {
                        x = event.getX();
                        y = event.getY();
                    } else {
                        float deltaX = Math.abs(x - event.getX());
                        float deltaY = Math.abs(y - event.getY());

                        if (deltaX > 1 || deltaY > 1) {
                            // 水平滑动距离大于五倍的垂直距离才判断为水平
                            if (deltaX > deltaY * 5) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            } else {
                                getParent().requestDisallowInterceptTouchEvent(false);
                            }
                            firstTime = false;
                        }

                    }

                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                firstTime = false;
                x = -1;
                y = -1;
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);

    }

    // 水平滑动时返回false
    class VerticalScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
