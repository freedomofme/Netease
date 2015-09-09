package com.szu.androidpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szu.androidpractice.R;

import java.util.List;

/**
 * Created by lgp on 2014/8/27.
 */
public class DrawerListAdapter extends BaseListAdapter<String> {

    public DrawerListAdapter(Context mContext, List<String> list) {
        super(mContext, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.simple_list_item_layout, null);
            holder = new Holder();
            holder.textView = (TextView)convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }

    static class Holder {
        TextView textView;
    }

}
