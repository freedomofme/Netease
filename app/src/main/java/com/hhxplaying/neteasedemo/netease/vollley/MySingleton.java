package com.hhxplaying.neteasedemo.netease.vollley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;

        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                //默认缓存20个单位的图片
                /*new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });*/
                new MyLrnCache(mCtx), 1 * 3600 * 1000, 15 * 24 * 3600 * 1000, true);
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    //30M的硬盘缓存
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), null, 30 * 1024 * 1024);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void cancelAllPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter(){
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null && tag != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}