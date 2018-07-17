package com.sty.relation.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tian on 2018/7/17.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDataList;
    protected LayoutInflater mInflater;
    private int layoutId;
    private boolean mIsHolder = true;

    public CommonAdapter(Context context, List<T> mDataList, int layoutId){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDataList = mDataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, layoutId,
                position, mIsHolder);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(CommonViewHolder holder, T t);

    public List<T> getmDataList() {
        return mDataList;
    }

    public void setmDataList(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    public boolean ismIsHolder() {
        return mIsHolder;
    }

    public void setmIsHolder(boolean mIsHolder) {
        this.mIsHolder = mIsHolder;
    }
}
