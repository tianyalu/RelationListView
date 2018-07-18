package com.sty.relation.listview.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sty.relation.listview.MainActivity;
import com.sty.relation.listview.R;
import com.sty.relation.listview.model.AnalyseEntity;
import com.sty.relation.listview.view.CustomHScrollView;

import java.util.List;

/**
 * Created by tian on 2018/7/18.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<AnalyseEntity> dataList;
    private LayoutInflater inflater;
    private LinearLayout listHeader;

    public ListViewAdapter(Context context, List<AnalyseEntity> dataList, LinearLayout listHeader){
        this.mContext = context;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
        this.listHeader = listHeader;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList == null ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            CustomHScrollView scrollView = convertView.findViewById(R.id.hsl_scrollview);
            holder.scrollView = scrollView;
            holder.tvName = convertView.findViewById(R.id.tv_product_name);
            holder.tvRetailAmount = convertView.findViewById(R.id.tv_retail_amount);
            holder.tvProfit= convertView.findViewById(R.id.tv_profit);
            holder.tvGrossProfit = convertView.findViewById(R.id.tv_gross_profit);
            holder.tvReceive = convertView.findViewById(R.id.tv_receive);
            holder.tvScrap = convertView.findViewById(R.id.tv_scrap);
            holder.tvProfitless = convertView.findViewById(R.id.tv_profitless);
            holder.llItemList = convertView.findViewById(R.id.ll_item_list);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(dataList.get(position).getName());
        holder.tvRetailAmount.setText(dataList.get(position).getRetailAmount());
        holder.tvProfit.setText(dataList.get(position).getProfit());
        holder.tvGrossProfit.setText(dataList.get(position).getGrossProfit());
        holder.tvReceive.setText(dataList.get(position).getReceive());
        holder.tvScrap.setText(dataList.get(position).getScrap());
        holder.tvProfitless.setText(dataList.get(position).getProfitless());

        final CustomHScrollView headerScrollview = listHeader.findViewById(R.id.hsl_scrollview);
        headerScrollview.addOnScrollChangedListener(new OnScrollChangedListenerImpl(holder.scrollView));
        holder.scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                headerScrollview.smoothScrollTo(i, i1);
            }
        });
        holder.tvName.setOnClickListener(new MyOnClickListener(position));
        holder.llItemList.setOnClickListener(new MyOnClickListener(position));

        return convertView;
    }

    class OnScrollChangedListenerImpl implements CustomHScrollView.OnScrollChangedListener{
        CustomHScrollView mScrollViewArg;

        public OnScrollChangedListenerImpl(CustomHScrollView scrollViewArg){
            mScrollViewArg = scrollViewArg;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    }

    class MyOnClickListener implements View.OnClickListener{
        private int position;

        public MyOnClickListener(int position){
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "item " + position + " is clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder{
        TextView tvName;
        TextView tvRetailAmount;
        TextView tvProfit;
        TextView tvGrossProfit;
        TextView tvReceive;
        TextView tvScrap;
        TextView tvProfitless;
        CustomHScrollView scrollView;
        LinearLayout llItemList;
    }
}
