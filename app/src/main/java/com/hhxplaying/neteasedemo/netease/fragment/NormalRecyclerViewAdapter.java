package com.hhxplaying.neteasedemo.netease.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
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
    int defaultImage = R.drawable.load_fail;
    int failImage = R.drawable.load_fail;
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
        if (listItem.get(position).getOrder() == 1)
            return ITEM_TYPE.ITEM_TYPE_BANNER.ordinal();
        else if (listItem.get(position).getImgextra() != null && listItem.get(position).getImgextra().size() > 1 && listItem.get(position).getSkipType().equals("photoset"))
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        else
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        } else {
            return new BannerViewHold(mLayoutInflater.inflate(R.layout.item_banner, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            if (listItem.size() - 1 >= position) {
                ((TextViewHolder) holder).mTitle.setText(listItem.get(position).getTitle());
                ((TextViewHolder) holder).mSubTitle.setText(listItem.get(position).getDigest());
                ((TextViewHolder) holder).mVote.setText(listItem.get(position).getReplyCount() + "跟帖");
                setNetworkImageView(((TextViewHolder) holder).mImageView, listItem.get(position).getImgsrc());
            }

        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(listItem.get(position).getTitle());
            ((ImageViewHolder) holder).mVote.setText(listItem.get(position).getReplyCount() + "跟帖");

            setNetworkImageView(((ImageViewHolder) holder).imageView1, listItem.get(position).getImgsrc());
            setNetworkImageView(((ImageViewHolder) holder).imageView2, listItem.get(position).getImgextra().get(0).getImgsrc());
            setNetworkImageView(((ImageViewHolder) holder).imageView3, listItem.get(position).getImgextra().get(1).getImgsrc());


        } else if (holder instanceof BannerViewHold) {
            //无数据时显示默认值
            if (listItem.size() == 0) {
                String[] urlsStrings = new String[]{"0"};
                ((BannerViewHold) holder).mSwitchImage.initPager(defaultImages, urlsStrings, urlsStrings);
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
//                    设置是否滚动
//                  ((BannerViewHold) holder).mSwitchImage.setMove(true, 4000);
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
        return listItem == null ? 0 : listItem.size();
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
        NetworkImageView imageView1;
        NetworkImageView imageView2;
        NetworkImageView imageView3;
        TextView mVote;//跟帖

        ImageViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_title);
            mVote = (TextView) view.findViewById(R.id.tv_vote);
            imageView1 = (NetworkImageView) view.findViewById(R.id.iv_img1);
            imageView2 = (NetworkImageView) view.findViewById(R.id.iv_img2);
            imageView3 = (NetworkImageView) view.findViewById(R.id.iv_img3);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView mImageView;
        TextView mTitle;
        TextView mSubTitle;
        TextView mVote;//跟帖
        TextViewHolder(View view) {
            super(view);
            mImageView = (NetworkImageView) view.findViewById(R.id.iv_left_image);
            mTitle = (TextView) view.findViewById(R.id.list_item_news_title);
            mSubTitle = (TextView) view.findViewById(R.id.list_item_news_subtitle);
            mVote = (TextView) view.findViewById(R.id.list_item_vote);
        }
    }

    private void setNetworkImageView(NetworkImageView networkImageView, String url) {
        networkImageView.setDefaultImageResId(defaultImage);
        networkImageView.setErrorImageResId(defaultImage);
        networkImageView.setImageUrl(url,
                MySingleton.getInstance(mContext).getImageLoader());
    }
}
