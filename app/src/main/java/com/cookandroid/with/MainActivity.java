package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    private Button button3;
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //this.getSupportActionBar().hide();// 홈 페이지 상단바 삭제

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  홈");

        //click 선착순 마감 도움 리스트
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //button2 text size
        Spannable span1 = (Spannable)button2.getText();
        span1.setSpan(new AbsoluteSizeSpan(36), 14, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        button3 = findViewById(R.id.button3);
        //button3 text size
        Spannable span2 = (Spannable)button3.getText();
        span2.setSpan(new AbsoluteSizeSpan(36), 13, 66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        button4 = findViewById(R.id.button4);
        //button4 text size
        Spannable span3 = (Spannable)button4.getText();
        span3.setSpan(new AbsoluteSizeSpan(36), 7, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
}