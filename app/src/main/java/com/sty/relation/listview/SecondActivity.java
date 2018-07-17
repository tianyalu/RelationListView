package com.sty.relation.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.sty.relation.listview.view.RelationListView;

public class SecondActivity extends AppCompatActivity {
    private RelationListView mListView1;
    private RelationListView mListView2;

    private String[] mData1 = new String[]{"listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1", "listView1", "listView1", "listView1",
            "listView1", "listView1"};
    private String[] mData2 = new String[]{"ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2", "ListView2", "ListView2", "ListView2",
            "ListView2", "ListView2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mListView1 = (RelationListView) findViewById(R.id.list_view1);
        mListView2 = (RelationListView) findViewById(R.id.list_view2);

        mListView1.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mData1));
        mListView2.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mData2));
        mListView1.setRelatedListView(mListView2);
        mListView2.setRelatedListView(mListView1);
    }

}
