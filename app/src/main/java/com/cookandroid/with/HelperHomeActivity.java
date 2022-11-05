package com.cookandroid.with;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.with.profile.HelperProfileActivity;

public class HelperHomeActivity extends AppCompatActivity {

    private ImageView profilebtn, alarmbtn;
    private Button button2;
    private Button button3;
    private Button button4;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_home);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_helper_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("   WITH");

        //click alarm
        alarmbtn = findViewById(R.id.alarmbtn);
        alarmbtn.setOnClickListener(v ->{
            Intent intent = new Intent(HelperHomeActivity.this, HelperAlarmActivity.class);
            startActivity(intent);
            finish();
        });

        //click profile
        profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelperHomeActivity.this, HelperProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //click 선착순 마감 도움 리스트
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelperHomeActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        //click 선택 마감 도움 리스트
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelperHomeActivity.this, SelectMatchList.class);
                startActivity(intent);
                finish();
            }
        });


        //click 매칭 리스트
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelperHomeActivity.this, MatchingList.class);
                startActivity(intent);
                finish();
            }
        });

        //button2 text size
        Spannable span1 = (Spannable)button2.getText();
        span1.setSpan(new AbsoluteSizeSpan(47), 11, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span1.setSpan(new ForegroundColorSpan(Color.DKGRAY), 11, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        button3 = findViewById(R.id.button3);
        //button3 text size
        Spannable span2 = (Spannable)button3.getText();
        span2.setSpan(new AbsoluteSizeSpan(47), 7, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span2.setSpan(new ForegroundColorSpan(Color.DKGRAY), 7, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //button4 text size
        Spannable span3 = (Spannable)button4.getText();
        span3.setSpan(new AbsoluteSizeSpan(47), 6, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span3.setSpan(new ForegroundColorSpan(Color.DKGRAY), 6, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }
}