package com.hhxplaying.neteasedemo.netease.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.activity.ImageDisplayActivity;
import com.hhxplaying.neteasedemo.netease.activity.NewsDisplayActivity;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.bean.imageextra.PhotoSet;
import com.hhxplaying.neteasedemo.netease.config.Global;
import com.hhxplaying.neteasedemo.netease.factory.RequestSingletonFactory;
import com.hhxplaying.neteasedemo.netease.util.NeteaseURLParse;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;
import com.hhxplaying.neteasedemo.netease.widget.MyRecyclerView;
import com.hhxplaying.neteasedemo.netease.widget.SwitchImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HHX on 15/9/9.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private RecyclerView recyclerView;
    private String[] mTitles;
    private ArrayList<OneNewsItemBean> listItem;
    int defaultImage = R.drawable.load_fail;
    int failImage = R.drawable.load_fail;
    private int[] defaultImages = new int[]{defaultImage};
    private PhotoSet photoSet;
    ImageViewHolderListener mImageViewHolderListener = new ImageViewHolderListener();
    final String TAG = getClass().getSimpleName();

    public enum ITEM_TYPE {
        ITEM_TYPE_BANNER,
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    public NormalRecyclerViewAdapter(Context context, ArrayList<OneNewsItemBean> listItem, RecyclerView recyclerView) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        this.recyclerView = recyclerView;
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
            View hold = mLayoutInflater.inflate(R.layout.item_image, parent, false);
            hold.setOnClickListener(mImageViewHolderListener);
            return new ImageViewHolder(hold);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
            View hold = mLayoutInflater.inflate(R.layout.item_text, parent, false);
            return new TextViewHolder(hold);
        } else {
            return new BannerViewHold(mLayoutInflater.inflate(R.layout.item_banner, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            if (listItem.size() - 1 >= position) {
                ((TextViewHolder) holder).mTitle.setText(listItem.get(position).getTitle());
                ((TextViewHolder) holder).mSubTitle.setText(listItem.get(position).getSource());
                ((TextViewHolder) holder).mVote.setText(listItem.get(position).getReplyCount() + "跟帖");

                NetworkImageView tempImage = ((TextViewHolder) holder).mImageView;
                setNetworkImageView(tempImage, NeteaseURLParse.parseWebpImageForTextAndImageType(listItem.get(position).getImgsrc(), tempImage.getWidth()));
                Log.i(TAG, "onBindViewHolder TextViewHolder");
                ((TextViewHolder) holder).v.setOnClickListener(new TextViewHolderListener(position));
            }

        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(listItem.get(position).getTitle());
            ((ImageViewHolder) holder).mVote.setText(listItem.get(position).getReplyCount() + "跟帖");

//            setNetworkImageView(((ImageViewHolder) holder).imageView1, listItem.get(position).getImgsrc());
//            setNetworkImageView(((ImageViewHolder) holder).imageView2, listItem.get(position).getImgextra().get(0).getImgsrc());
//            setNetworkImageView(((ImageViewHolder) holder).imageView3, listItem.get(position).getImgextra().get(1).getImgsrc());
            String jsonString = NeteaseURLParse.parseJSONUrlOFPhotoset(listItem.get(position));

            MyRecyclerView hold = ((ImageViewHolder) holder).mRecyclerView;
            if (hold.getAdapter() != null && hold.getAdapter() instanceof HorizontalImageRecyclerViewAdapter) {
                //单纯设置数据
                Log.i(TAG, "单纯设置数据");
                getPhotosetImageJsonURl((HorizontalImageRecyclerViewAdapter) hold.getAdapter(), jsonString);
            } else {
                //设置水平适配器
                Log.i(TAG, "设置水平适配器");
                hold.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                HorizontalImageRecyclerViewAdapter horizontalImageRecyclerViewAdapter = new HorizontalImageRecyclerViewAdapter(mContext, null, hold);
                hold.setAdapter(horizontalImageRecyclerViewAdapter);
                getPhotosetImageJsonURl(horizontalImageRecyclerViewAdapter, jsonString);
            }

//            hold.setClickable(true);
//            hold.requestDisallowInterceptTouchEvent(true);

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

                    //此处为了防止有的栏目的banner只有一个图片的情况。
                    int size = 0;
                    if (hold.getAdss() != null)
                        size = hold.getAdss().size() + 1;
                    else
                        size = 1;
                    //memory may leak!
                    int[] defaultImages2 = new int[size];
                    String[] urlsStrings = new String[size];
                    String[] textsStrings = new String[size];

                    for (int i = 0; i < defaultImages2.length; i++) {
                        defaultImages2[i] = defaultImage;
                    }

                    urlsStrings[0] = NeteaseURLParse.parseWebpImageForTextAndImageType(hold.getImgsrc(), ((BannerViewHold)holder).mSwitchImage.getWidth());
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
                            MySingleton.getInstance(mContext.getApplicationContext()).getImageLoader().get(url,
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

    @Override
    public long getItemId(int position) {
        return position;
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
        //        NetworkImageView imageView1;
//        NetworkImageView imageView2;
//        NetworkImageView imageView3;
        MyRecyclerView mRecyclerView;
        TextView mVote;//跟帖

        ImageViewHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.tv_title);
            mVote = (TextView) view.findViewById(R.id.tv_vote);
//            imageView1 = (NetworkImageView) view.findViewById(R.id.iv_img1);
//            imageView2 = (NetworkImageView) view.findViewById(R.id.iv_img2);
//            imageView3 = (NetworkImageView) view.findViewById(R.id.iv_img3);

            mRecyclerView = (MyRecyclerView) view.findViewById(R.id.rv_subrecycleview);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView mImageView;
        TextView mTitle;
        TextView mSubTitle;
        TextView mVote;//跟帖
        View v;
        TextViewHolder(View view) {
            super(view);
            v = view;
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
                MySingleton.getInstance(mContext.getApplicationContext()).getImageLoader());
    }


    class TextViewHolderListener implements View.OnClickListener {
        int position;
        TextViewHolderListener(int i) {
            position = i;
        }
        @Override
        public void onClick(View v) {
            Log.d("RVA", "TextViewHolderListener :" + listItem.get(position) + "");
            String jsonLink = NeteaseURLParse.webURLToMobileJSONLink2(listItem.get(position).getPostid());
            if (TextUtils.isEmpty(jsonLink))
                jsonLink = NeteaseURLParse.webURLToMobileJSONLink(listItem.get(position).getUrl());
            Log.i("RVA", jsonLink);

            Intent i = new Intent(mContext, NewsDisplayActivity.class);
            i.putExtra("NEWS_LINK", jsonLink);
            mContext.startActivity(i);

//            //直接打开系统浏览器，来查看点击的新闻
//            Uri uri = Uri.parse(JsonLink);
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            mContext.startActivity(intent);
        }
    }

    class ImageViewHolderListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mContext, ImageDisplayActivity.class);
            mContext.startActivity(i);
        }
    }

    private void getPhotosetImageJsonURl(final HorizontalImageRecyclerViewAdapter adapter, final String url) {
        MySingleton.getInstance(mContext.getApplicationContext()).getRequestQueue().add(
                RequestSingletonFactory.getInstance().getGETStringRequest(mContext, url,
                        new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        JSONObject obj;
                        try {
                            obj = new JSONObject(response.toString());
                            PhotoSet photoSet = new Gson().fromJson(obj.toString(), Global.NewsImageItemType);
                            Global.extraImageHashMap.put(url, photoSet);
                            adapter.setPhotoSet(photoSet);
//                            System.out.println(photoSet);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }));
    }


}
