package com.sty.relation.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnFirst;
    private Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst = findViewById(R.id.btn_first);
        btnSecond = findViewById(R.id.btn_second);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_first:
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_second:
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
