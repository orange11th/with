package com.cookandroid.with;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ImageView profilebtn;
    private Button button2;
    private Button button3;
    private Button button4;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  홈");

        //click alarm

        //click profile
        profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelperProfileActivity.class);
                startActivity(intent);
            }
        });

        //click 선착순 마감 도움 리스트
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //click 선택 마감 도움 리스트
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectMatchList.class);
                startActivity(intent);
            }
        });


        //click 매칭 리스트
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatchingList.class);
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


        //button4 text size
        Spannable span3 = (Spannable)button4.getText();
        span3.setSpan(new AbsoluteSizeSpan(36), 7, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
}