package com.hhxplaying.neteasedemo.netease.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhxplaying.neteasedemo.netease.R;

/**
 * Created by HHX on 15/9/9.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        } else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;

        ImageViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_title);
            imageView1 = (ImageView)view.findViewById(R.id.iv_img1);
            imageView2 = (ImageView)view.findViewById(R.id.iv_img2);
            imageView3 = (ImageView)view.findViewById(R.id.iv_img3);
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
