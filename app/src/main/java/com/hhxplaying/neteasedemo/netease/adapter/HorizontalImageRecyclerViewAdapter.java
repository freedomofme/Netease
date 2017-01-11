package com.hhxplaying.neteasedemo.netease.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.hhxplaying.neteasedemo.netease.MyApplication;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.activity.ImageDisplayActivity;
import com.hhxplaying.neteasedemo.netease.adapter.HorizontalImageRecyclerViewAdapter.ImageViewHolder.IMyViewHolderClicks;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;

/**
 * Created by HHX on 15/9/9.
 */
public class HorizontalImageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

//    public static enum ITEM_TYPE {
//        ITEM_FIRST,
//        ITEM_NOT_FIRST
//    }

    public HorizontalImageRecyclerViewAdapter(Context context, PhotoSet photoSet, RecyclerView recyclerView) {
        mContext = context;
        this.recyclerView = recyclerView;
        mLayoutInflater = LayoutInflater.from(context);
        this.photoSet = photoSet;

        Resources r = context.getResources();
        float leftAndRightMargin = r.getDimension(R.dimen.list_margin_left_and_right);//14px
        float imageMargin = r.getDimension(R.dimen.list_margin_top_and_bottom);//20px

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View hold = mLayoutInflater.inflate(R.layout.item_sub_image, parent, false);
        return new ImageViewHolder(hold, imageWeight, imageHeight, recyclerView, viewType, new IMyViewHolderClicks(){
            @Override
            public void onViewPageTouch(NetworkImageView callerImage, int index) {
                Intent i = new Intent(mContext, ImageDisplayActivity.class);
                //传递此参数给ViewPage显示
                i.putExtra("photoSet", photoSet);
                //传递被点击的image的index
                i.putExtra("imageIndex", index);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (photoSet == null || photoSet.getPhotos() == null) {
           setNetworkImageView(((ImageViewHolder) holder).imageView, "");
        } else {
            setNetworkImageView(((ImageViewHolder) holder).imageView, photoSet.getPhotos().get(position).getTimgurl());
        }
    }

    @Override
    public int getItemCount() {
        return (photoSet == null || photoSet.getPhotos() == null) ? 3 : photoSet.getPhotos().size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        IMyViewHolderClicks mListener;
        int index;
        boolean isMoved = false;
        ImageViewHolder(View view , int weight, int height, RecyclerView recyclerView, int i, IMyViewHolderClicks myViewHoldeTouch) {
            super(view);
            mListener = myViewHoldeTouch;
            index = i;
            isMoved = false;
            //第一张图不要边距
            if (index == 0) {
                RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                RelativeLayout rv = (RelativeLayout) view.findViewById(R.id.rlContainer);
                rl.setMargins(0, 0, 0, 0);
                rv.setLayoutParams(rl);
            }
            imageView = (NetworkImageView) view.findViewById(R.id.iv_sub_image);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(weight, height));
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i(TAG, "onTouch");
                    if (event.getAction() == MotionEvent.ACTION_UP && !isMoved)
                        mListener.onViewPageTouch((NetworkImageView) v, index);
                    else {
                        isMoved = false;
                    }
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        isMoved = true;
                    }
                    return true;
                }
            });
        }

        public interface IMyViewHolderClicks {
            void onViewPageTouch(NetworkImageView callerImage, int index);
        }
    }

    private void setNetworkImageView(NetworkImageView networkImageView, String url) {
        networkImageView.setDefaultImageResId(defaultImage);
        networkImageView.setErrorImageResId(defaultImage);
        networkImageView.setImageUrl(url,
                MySingleton.getInstance(mContext).getImageLoader());
    }

}
