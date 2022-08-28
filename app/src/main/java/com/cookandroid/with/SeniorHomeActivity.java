package com.cookandroid.with;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SeniorHomeActivity extends AppCompatActivity {
    private Button btn2, btn3, btn4;
    private ImageView alarmbtn, profilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_home);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  홈");

        //click alarm
        alarmbtn = findViewById(R.id.alarmbtn);
        alarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeniorHomeActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });
        //click profile
        profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeniorHomeActivity.this, SeniorProfileActivity.class);
                startActivity(intent);
            }
        });

        //간편 도움 신청 버튼
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimpleActivity.class);
                startActivity(intent);
            }
        });

        Spannable span2 = (Spannable) btn2.getText();
        //글자 크기 다르게
        span2.setSpan(new AbsoluteSizeSpan(47), 11,40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //글자 색 다르게
        span2.setSpan(new ForegroundColorSpan(Color.DKGRAY), 11, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //보호자 도움 신청 버튼
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
                startActivity(intent);
            }
        });

        Spannable span3 = (Spannable) btn3.getText();
        //글자 크기 다르게
        span3.setSpan(new AbsoluteSizeSpan(47), 13,45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //글자 색 다르게
        span3.setSpan(new ForegroundColorSpan(Color.DKGRAY), 13, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //도움 내역 버튼
        btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        Spannable span4 = (Spannable) btn4.getText();
        //글자 크기 다르게
        span4.setSpan(new AbsoluteSizeSpan(47), 6,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //글자 색 다르게
        span4.setSpan(new ForegroundColorSpan(Color.DKGRAY), 6, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}