package com.hhxplaying.neteasedemo.netease.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.hhxplaying.neteasedemo.netease.MyApplication;
import com.hhxplaying.neteasedemo.netease.R;
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

    public static enum ITEM_TYPE {
        ITEM_FIRST,
        ITEM_NOT_FIRST
    }

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
        System.out.println(imageWeight);
        System.out.println(imageHeight);
    }

    public void setPhotoSet(PhotoSet photoSet) {
        this.photoSet = photoSet;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_FIRST.ordinal() : ITEM_TYPE.ITEM_NOT_FIRST.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View hold = mLayoutInflater.inflate(R.layout.item_sub_image, parent, false);
        return new ImageViewHolder(hold, imageWeight, imageHeight, recyclerView, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (photoSet == null || photoSet.getPhotos() == null) {
           setNetworkImageView(((ImageViewHolder) holder).imageView, "http://img4.cache.netease.com/photo/0096/2015-09-12/t_B39ABCER54GI0096.jpg");
        } else {
            setNetworkImageView(((ImageViewHolder) holder).imageView, photoSet.getPhotos().get(position).getTimgurl());
        }
    }

    @Override
    public int getItemCount() {
        return (photoSet == null || photoSet.getPhotos() == null) ? 3 : photoSet.getPhotos().size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageView;

        ImageViewHolder(View view , int weight, int height, RecyclerView recyclerView, int index) {
            super(view);

            if (index == ITEM_TYPE.ITEM_FIRST.ordinal()) {
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
                MySingleton.getInstance(mContext).getImageLoader());
    }

}
