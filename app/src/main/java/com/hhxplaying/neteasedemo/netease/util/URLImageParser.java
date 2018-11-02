package com.hhxplaying.neteasedemo.netease.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.hhxplaying.neteasedemo.netease.MyApplication;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by netease on 16/2/18.
 */
public class URLImageParser implements Html.ImageGetter {
    Context c;
    TextView container;

    /***
     * Construct the URLImageParser which will execute AsyncTask and refresh the container
     * @param t
     * @param c
     */
    public URLImageParser(TextView t, Context c) {
        this.c = c;
        this.container = t;
    }

    @Override
    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();

        // get the actual source
        ImageGetterAsyncTask asyncTask =
                new ImageGetterAsyncTask( urlDrawable);

        asyncTask.execute(source);

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override

        protected void onPostExecute(Drawable result) {
            // set the correct bound according to the result from HTTP call
            Log.i("RVA","height"+result.getIntrinsicHeight());
            Log.i("RVA", "wight" + result.getIntrinsicWidth());
            //设置图片显示占据的高度
            int imageWight = MyApplication.width;
            int imageHeight = result.getIntrinsicHeight() * MyApplication.width / result.getIntrinsicWidth();
            urlDrawable.setBounds(0, 0,imageWight, imageHeight);

            // change the reference of the current drawable to the result
            // from the HTTP call
            urlDrawable.drawable = result;

            // redraw the image by invalidating the container
            URLImageParser.this.container.invalidate();

            // For ICS
            URLImageParser.this.container.setHeight((URLImageParser.this.container.getHeight() + imageHeight));

            // Pre ICS
            URLImageParser.this.container.setEllipsize(null);


            URLImageParser.this.container.requestLayout();

        }

        /***
         * Get the Drawable from URL
         * @param urlString
         * @return
         */
        public Drawable fetchDrawable(String urlString) {
            InputStream is = null;
            try {
                is = fetch(urlString);
                Drawable drawable = Drawable.createFromStream(is, "src");

                //设置图片的实际高低
                drawable.setBounds(0, 0, 0 + MyApplication.width, 0
                        + drawable.getIntrinsicHeight()* MyApplication.width / drawable.getIntrinsicWidth());
                return drawable;
            } catch (Exception e) {
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                return null;
            }
        }

        private InputStream fetch(String urlString) throws IOException {
            URL url = new URL(urlString);
            Log.i("RVA", urlString);
            URLConnection urlConnection = url.openConnection();
            return new BufferedInputStream(urlConnection.getInputStream());
        }
    }


    class URLDrawable extends BitmapDrawable {
        // the drawable that you need to set, you could set the initial drawing
        // with the loading image if you need to
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if(drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
}