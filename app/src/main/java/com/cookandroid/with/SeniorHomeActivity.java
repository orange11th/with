package com.cookandroid.with;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.with.databinding.ActivitySeniorHomeBinding;
import com.cookandroid.with.profile.SeniorProfileActivity;
import com.cookandroid.with.simple.NoxActivity;
import com.cookandroid.with.simple.SimpleActivity;

public class SeniorHomeActivity extends AppCompatActivity {
    private ActivitySeniorHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeniorHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  WITH");

        //알림 아이콘 버튼
        binding.alarmbtn.setOnClickListener( v -> {
            Intent intent = new Intent(SeniorHomeActivity.this, SeniorAlarmActivity.class);
            startActivity(intent);
            finish();
        });

        //프로필 아이콘 버튼
        binding.profilebtn.setOnClickListener( v -> {
            Intent intent = new Intent(SeniorHomeActivity.this, SeniorProfileActivity.class);
            startActivity(intent);
            finish();
        });

        //간편 도움 신청 버튼
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimpleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Spannable span2 = (Spannable) binding.button2.getText();
        //글자 크기 다르게
        span2.setSpan(new AbsoluteSizeSpan(44), 11,40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //글자 색 다르게
        span2.setSpan(new ForegroundColorSpan(Color.DKGRAY), 11, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //보호자 도움 신청 버튼
        binding.button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
                startActivity(intent);
            }
        });

        Spannable span3 = (Spannable) binding.button3.getText();
        span3.setSpan(new AbsoluteSizeSpan(44), 13,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span3.setSpan(new ForegroundColorSpan(Color.DKGRAY), 13, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //도움 내역 버튼
        binding.button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        Spannable span4 = (Spannable) binding.button4.getText();
        //글자 크기 다르게
        span4.setSpan(new AbsoluteSizeSpan(44), 6,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //글자 색 다르게
        span4.setSpan(new ForegroundColorSpan(Color.DKGRAY), 6, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}