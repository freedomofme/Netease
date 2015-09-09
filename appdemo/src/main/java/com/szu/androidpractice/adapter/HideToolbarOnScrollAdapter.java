package com.szu.androidpractice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szu.androidpractice.R;

import java.util.List;

/**
 * Created by lgp on 2015/4/6.
 */

public class HideToolbarOnScrollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<String> mItemList;

    public HideToolbarOnScrollAdapter(List<String> itemList) {
        this.mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout, parent, false);
            return new RecyclerItemViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_head_layout, parent, false);
            return new RecyclerHeadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position >= 1){
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            holder.setItemText(mItemList.get(position - 1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return (mItemList == null ? 0 : mItemList.size()) + 1;
    }
}