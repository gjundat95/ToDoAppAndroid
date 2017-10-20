package com.jundat95.todoapp.view.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jundat95.todoapp.R;

public class DetailToDoActivity extends AppCompatActivity {

    private TextView tvTitle, tvContent, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_to_do);

        init();
    }

    private void init() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvDate = (TextView) findViewById(R.id.tvDate);

        // lấy dữ liệu từ bên MainActivity truyền sang
        Intent intent = getIntent();
        // dữ liệu trong intent lấy ra và gán vào textview
        tvTitle.setText(intent.getStringExtra("title"));
        tvContent.setText(intent.getStringExtra("content"));
        tvDate.setText(intent.getStringExtra("date"));
    }
}
