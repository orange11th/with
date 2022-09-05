package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity {
    private Button historybtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" 도움 내역");

        historybtn1 = (Button) findViewById(R.id.history_btn1);

        historybtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, SeniorCheck1Activity.class);
                startActivity(intent);
            }
        });

        Spannable span1 = (Spannable) historybtn1.getText();
        //글자 크기 다르게
        span1.setSpan(new AbsoluteSizeSpan(43), 0,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //색 다르게
        span1.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //스타일 다르게
        span1.setSpan(new StyleSpan(Typeface.BOLD), 11, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}