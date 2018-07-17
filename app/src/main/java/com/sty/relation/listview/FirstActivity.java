package com.sty.relation.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sty.relation.listview.adapter.CommonAdapter;
import com.sty.relation.listview.adapter.CommonViewHolder;
import com.sty.relation.listview.model.AnalyseEntity;
import com.sty.relation.listview.utils.ViewUtils;
import com.sty.relation.listview.view.CusHorizontalScrollView;
import com.sty.relation.listview.view.RelationListView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private ListView lvName;
    private ListView lvContent;
    private CusHorizontalScrollView hslHeader;
    private CusHorizontalScrollView hslContent;

    private CommonAdapter<AnalyseEntity> nameAdapter;
    private CommonAdapter<AnalyseEntity> contentAdapter;

    private List<AnalyseEntity> dataList;

    private List<AnalyseEntity> createDataList(){
        List<AnalyseEntity> dataList = new ArrayList<>();
        AnalyseEntity entity = new AnalyseEntity();
        String str = "";
        for(int i = 1000; i < 1060; i++){
            str = i + "";
            entity.setName(str);
            entity.setGrossProfit(str);
            entity.setProfit(str);
            entity.setProfitless(str);
            entity.setReceive(str);
            entity.setScrap(str);
            entity.setRetailAmount(str);
            dataList.add(entity);
        }
        return dataList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();
        setLvName();
        setLvContent();
    }

    private void initView(){
        lvName = findViewById(R.id.lv_name);
        lvContent = findViewById(R.id.lv_content);
        hslHeader = findViewById(R.id.hsl_header);
        hslContent = findViewById(R.id.hsl_content);

        dataList = createDataList();

        lvName.setTag(lvContent);
        lvContent.setTag(lvName);
        hslHeader.setSynHorizontalScrollView(hslContent);
        hslContent.setSynHorizontalScrollView(hslHeader);

        ViewUtils.setListViewOnTouchAndScrollListener(lvName, lvContent);
    }

    private void setLvName(){
        if(nameAdapter == null){
            nameAdapter = new CommonAdapter<AnalyseEntity>(this, dataList, R.layout.item_name) {
                @Override
                public void convert(final CommonViewHolder holder, AnalyseEntity analyseEntity) {
                    holder.setText(R.id.tv_position, (holder.getPosition() + 1) + ".");
                    holder.setText(R.id.tv_product_name, analyseEntity.getName());
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(FirstActivity.this, "item " + holder.getPosition() + " is clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            lvName.setAdapter(nameAdapter);
        }else {
            nameAdapter.setmDataList(dataList);
            nameAdapter.notifyDataSetChanged();
        }
    }

    private void setLvContent(){
        if(contentAdapter == null){
            contentAdapter = new CommonAdapter<AnalyseEntity>(this, dataList, R.layout.item_content) {
                @Override
                public void convert(final CommonViewHolder holder, AnalyseEntity analyseEntity) {
                    holder.setText(R.id.tv_retail_amount, analyseEntity.getRetailAmount());
                    holder.setText(R.id.tv_profit, analyseEntity.getProfit());
                    holder.setText(R.id.tv_gross_profit, analyseEntity.getGrossProfit());
                    holder.setText(R.id.tv_receive, analyseEntity.getReceive());
                    holder.setText(R.id.tv_scrap, analyseEntity.getScrap());
                    holder.setText(R.id.tv_profitless, analyseEntity.getProfitless());
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(FirstActivity.this, "item " + holder.getPosition() + " is clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            lvContent.setAdapter(contentAdapter);
        }else {
            contentAdapter.setmDataList(dataList);
            contentAdapter.notifyDataSetChanged();
        }
    }

}
