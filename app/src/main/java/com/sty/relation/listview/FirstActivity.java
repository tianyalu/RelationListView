package com.sty.relation.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.sty.relation.listview.adapter.CommonAdapter;
import com.sty.relation.listview.adapter.CommonViewHolder;
import com.sty.relation.listview.model.AnalyseEntity;
import com.sty.relation.listview.utils.ViewUtils;
import com.sty.relation.listview.view.CusHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lvName;
    private ListView lvContent;
    private CusHorizontalScrollView hslHeader;
    private CusHorizontalScrollView hslContent;

    private CommonAdapter<AnalyseEntity> nameAdapter;
    private CommonAdapter<AnalyseEntity> contentAdapter;

    private List<AnalyseEntity> dataList;

    private List<AnalyseEntity> createDataList(){
        List<AnalyseEntity> dataList = new ArrayList<>();
        String str = "";
        for(int i = 1; i <= 100; i++){
            str = "第" + i + "行";
            AnalyseEntity entity = new AnalyseEntity();
            entity.setName(str + "-0");
            entity.setRetailAmount(str + "-1");
            entity.setProfit(str + "-2");
            entity.setGrossProfit(str + "-3");
            entity.setReceive(str + "-4");
            entity.setScrap(str + "-5");
            entity.setProfitless(str + "-6");

            dataList.add(entity);
        }
        return dataList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        setActionBar();
        initView();
        setListView();
    }

    private void setActionBar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setListView(){
        dataList = createDataList();

        setLvName();
        setLvContent();
        lvName.setSelection(0);
        lvContent.setSelection(0);
    }

    private void initView(){
        lvName = findViewById(R.id.lv_name);
        lvContent = findViewById(R.id.lv_content);
        hslHeader = findViewById(R.id.hsl_header);
        hslContent = findViewById(R.id.hsl_content);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                setListView();  //刷新，重新加载数据
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
