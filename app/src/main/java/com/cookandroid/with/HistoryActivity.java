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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cookandroid.with.databinding.ActivityHistoryBinding;
import com.cookandroid.with.seniorHome.SeniorHomeActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ActivityHistoryBinding binding;
    Button historyButton;
    LayoutInflater layoutInflater;
    ArrayList<String> historyList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" 도움 내역");

        historyList = new ArrayList();

        historyList.add("2022.09.30\n병원 동행");
        historyList.add("2022.09.27\n병원 동행");
        historyList.add("2022.09.16\n취미/여가");

        layoutInflater = LayoutInflater.from(this);

        for(int i = 0; i < (historyList.size()); i++) {
            View view = layoutInflater.inflate(R.layout.layout_history_contents, null, false);

            //버튼 생성
            historyButton = view.findViewById(R.id.historyBtn1);
            historyButton.setText(historyList.get(i));

            Spannable span = (Spannable) historyButton.getText();
            span.setSpan(new AbsoluteSizeSpan(43), 0,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new StyleSpan(Typeface.NORMAL), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            binding.viewLayout.addView(view);

            historyButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HistoryActivity.this, SeniorCheck1Activity.class);
                    startActivity(intent);
                }
            });
        }


        Spannable span1 = (Spannable) binding.text1.getText();
        span1.setSpan(new ForegroundColorSpan(Color.rgb(34,34,34)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span1.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable span2 = (Spannable) binding.text2.getText();
        span2.setSpan(new ForegroundColorSpan(Color.rgb(34,34,34)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span2.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable span3 = (Spannable) binding.text3.getText();
        span3.setSpan(new ForegroundColorSpan(Color.rgb(34,34,34)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span3.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

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