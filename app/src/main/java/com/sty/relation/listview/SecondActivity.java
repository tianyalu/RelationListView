package com.sty.relation.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sty.relation.listview.adapter.ListViewAdapter;
import com.sty.relation.listview.model.AnalyseEntity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView mListView;
    private LinearLayout listViewHeader;

    private List<AnalyseEntity> dataList;
    private ListViewAdapter adapter;

    private List<AnalyseEntity> createDataList() {
        List<AnalyseEntity> dataList = new ArrayList<>();
        String str = "";
        for (int i = 1; i <= 100; i++) {
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
        setContentView(R.layout.activity_second);

        setActionBar();
        initView();
    }

    private void setActionBar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        mListView = findViewById(R.id.lv_list_view);
        listViewHeader = findViewById(R.id.head_layout);

        listViewHeader.setFocusable(true);
        listViewHeader.setClickable(true);

        setListView();
    }

    private void setListView(){
        dataList = createDataList();
        adapter = new ListViewAdapter(this, dataList, listViewHeader);
        mListView.setAdapter(adapter);
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
