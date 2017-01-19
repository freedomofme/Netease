package com.hhxplaying.neteasedemo.netease.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.hhxplaying.neteasedemo.netease.MyApplication;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.activity.ImageDisplayActivity;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.util.NeteaseURLParse;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;

/**
 * Created by HHX on 15/9/9.
 */
public class HorizontalImageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnTouchListener{
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private RecyclerView recyclerView;
    int defaultImage = R.drawable.load_fail_small;
    int failImage = R.drawable.load_fail_small;
    private int[] defaultImages = new int[]{defaultImage};
    PhotoSet photoSet;
    int imageWeight;
    int imageHeight;
    static final String TAG = "HorizontalImageRecycler";
    private long pressStartTime;
    private float pressedX;
    private float pressedY;
    private ViewConfiguration viewConfiguration;
    private ViewGroup parent;
    public boolean isIntercept = false;

    public HorizontalImageRecyclerViewAdapter(Context context, PhotoSet photoSet, RecyclerView recyclerView) {
        mContext = context;
        this.recyclerView = recyclerView;
        mLayoutInflater = LayoutInflater.from(context);
        this.photoSet = photoSet;

        Resources r = context.getResources();
        float leftAndRightMargin = r.getDimension(R.dimen.list_margin_left_and_right);//14px
        float imageMargin = r.getDimension(R.dimen.list_margin_top_and_bottom);//20px

        viewConfiguration = ViewConfiguration.get(context);
        imageHeight = (int)r.getDimension(R.dimen.list_big_image_item_height);
        imageWeight = (int)(MyApplication.width - (leftAndRightMargin + imageMargin) * 2) / 3;
    }

    public void setPhotoSet(PhotoSet photoSet) {
        this.photoSet = photoSet;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View hold = mLayoutInflater.inflate(R.layout.item_sub_image, parent, false);
        this.parent = parent;

        ImageViewHolder holder = new ImageViewHolder(hold, imageWeight, imageHeight, viewType);
        holder.imageView.setTag(viewType);
        holder.imageView.setOnTouchListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (photoSet == null || photoSet.getPhotos() == null) {
            setNetworkImageView(((ImageViewHolder) holder).imageView, "");
        } else {
            setNetworkImageView(((ImageViewHolder) holder).imageView, NeteaseURLParse.parseWebpImageForTextAndImageType(photoSet.getPhotos().get(position).getTimgurl(), imageWeight));
        }
    }

    @Override
    public int getItemCount() {
        return (photoSet == null || photoSet.getPhotos() == null) ? 3 : photoSet.getPhotos().size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("HorizontalImage", "child onTouchEvent" + event.getAction());
        long pressDuration = System.currentTimeMillis() - pressStartTime;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                pressStartTime = System.currentTimeMillis();
                pressedX = event.getX();
                pressedY = event.getY();
                isIntercept = false;
                break;
            }
            case MotionEvent.ACTION_MOVE:
                if (pressDuration <= ViewConfiguration.getJumpTapTimeout()
                        && distance(pressedX, pressedY, event.getX(), event.getY()) <= viewConfiguration.getScaledTouchSlop()) {
                } else {
                    //此标志位相当重要，不能用requestDisallowInterceptTouchEvent()方法代替.
                    //@link https://developer.android.com/guide/topics/ui/ui-events.html#TouchMode
                    // 也可根据ViewGroup.java的onInterceptTouchEvent()源码查看这个方法逻辑。
                    isIntercept = true;
                }
                break;
            case MotionEvent.ACTION_UP: {
                if (pressDuration <= ViewConfiguration.getJumpTapTimeout()
                        && distance(pressedX, pressedY, event.getX(), event.getY()) <= viewConfiguration.getScaledTouchSlop()) {
                    sendIntent(v);
                } else {
                    isIntercept = true;
                }
                break;
            }
        }

        return true;
    }

    private void sendIntent(View v) {
        Intent i = new Intent(mContext, ImageDisplayActivity.class);
        //传递此参数给ViewPage显示
        i.putExtra("photoSet", photoSet);
        //传递被点击的image的index
        i.putExtra("imageIndex", (Integer) v.getTag());
        mContext.startActivity(i);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        int index;
        ImageViewHolder(View view , int weight, int height, int i) {
            super(view);
            index = i;

            //第一张图不要边距
            if (index == 0) {
                RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                RelativeLayout rv = (RelativeLayout) view.findViewById(R.id.rlContainer);
                rl.setMargins(0, 0, 0, 0);
                rv.setLayoutParams(rl);
            }
            imageView = (NetworkImageView) view.findViewById(R.id.iv_sub_image);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(weight, height));
        }
    }

    private void setNetworkImageView(NetworkImageView networkImageView, String url) {
        networkImageView.setDefaultImageResId(defaultImage);
        networkImageView.setErrorImageResId(defaultImage);
        networkImageView.setImageUrl(url,
                MySingleton.getInstance(mContext.getApplicationContext()).getImageLoader());
    }

    private static float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

}
