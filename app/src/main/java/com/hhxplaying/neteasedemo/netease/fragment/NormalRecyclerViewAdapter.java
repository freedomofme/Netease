package com.hhxplaying.neteasedemo.netease.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;
import com.hhxplaying.neteasedemo.netease.widget.SwitchImage;

import java.util.ArrayList;

/**
 * Created by HHX on 15/9/9.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private ArrayList<OneNewsItemBean> listItem;
    int defaultImage = R.mipmap.new01;
    int failImage = R.mipmap.new01;
    private int[] defaultImages = new int[]{defaultImage};

    public static enum ITEM_TYPE {
        ITEM_TYPE_BANNER,
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    public NormalRecyclerViewAdapter(Context context, ArrayList<OneNewsItemBean> listItem) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        this.listItem = listItem;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ITEM_TYPE.ITEM_TYPE_BANNER.ordinal();
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        } else {
            return new BannerViewHold(mLayoutInflater.inflate(R.layout.item_banner, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);
        } else if (holder instanceof BannerViewHold) {
            //无数据时显示默认值
            if (listItem.size() == 0) {
                String[] urlsStrings = new String[]{"0"};
                ((BannerViewHold) holder).mSwitchImage.initPager(defaultImages, urlsStrings, urlsStrings);
                ((BannerViewHold) holder).mSwitchImage.setMove(true, 4000);
            }
            //正常加载数据
            else {
                if (listItem.size() >= 1 && listItem.get(0).getOrder() == 1) {
                    OneNewsItemBean hold = listItem.get(0);
                    int size = hold.getAdss().size() + 1;
                    //memory may leak!
                    int[] defaultImages2 = new int[size];
                    String[] urlsStrings = new String[size];
                    String[] textsStrings = new String[size];

                    for (int i = 0; i < defaultImages2.length; i++) {
                        defaultImages2[i] = defaultImage;
                    }

                    urlsStrings[0] = hold.getImgsrc();
                    for (int i = 1; i < urlsStrings.length; i++) {
                        //由size的值保证不越界
                        urlsStrings[i] = hold.getAdss().get(i - 1).getImgsrc();
                    }

                    textsStrings[0] = hold.getTitle();
                    for (int i = 1; i < textsStrings.length; i++) {
                        //由size的值保证不越界
                        textsStrings[i] = hold.getAdss().get(i - 1).getTitle();
                    }

                    ((BannerViewHold) holder).mSwitchImage.initPager(defaultImages2, urlsStrings, textsStrings);
                    //从网络加载数据

                    ((BannerViewHold) holder).mSwitchImage.setAndLoadImage(new SwitchImage.DisplayImageView() {
                        @Override
                        public void displayImageFromURL(ImageView view, String url) {
                            ImageLoader.ImageListener listener = ImageLoader.getImageListener(view,
                                    defaultImage, failImage);
                            MySingleton.getInstance(mContext).getImageLoader().get(url,
                                    listener, 1000, 500);
                        }
                    });
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class BannerViewHold extends RecyclerView.ViewHolder {
        SwitchImage mSwitchImage;

        public BannerViewHold(View itemView) {
            super(itemView);
            mSwitchImage = (SwitchImage) itemView.findViewById(R.id.si_banner_image);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;

        ImageViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_title);
            imageView1 = (ImageView) view.findViewById(R.id.iv_img1);
            imageView2 = (ImageView) view.findViewById(R.id.iv_img2);
            imageView3 = (ImageView) view.findViewById(R.id.iv_img3);
            imageView1.setImageResource(R.mipmap.a);
            imageView2.setImageResource(R.mipmap.a);
            imageView3.setImageResource(R.mipmap.a);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
