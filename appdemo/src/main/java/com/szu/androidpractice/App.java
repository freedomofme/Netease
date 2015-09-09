package com.szu.androidpractice;

import android.app.Application;

/**
 * Created by lgp on 2015/4/6.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
