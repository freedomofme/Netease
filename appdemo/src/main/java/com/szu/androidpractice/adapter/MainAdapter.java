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
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> mItemList;
    public MainAdapter(List<String> itemList) {
        this.mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        holder.setItemText(mItemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0: mItemList.size();
    }

}
