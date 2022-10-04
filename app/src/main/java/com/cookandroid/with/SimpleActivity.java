package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cookandroid.with.databinding.ActivityNoxBinding;
import com.cookandroid.with.databinding.ActivitySimpleBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class SimpleActivity extends AppCompatActivity {
    ActivitySimpleBinding binding;
    private Button datePickerBtn;

    TextView dateText;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySimpleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        //병원 동행 버튼
        binding.checkbox1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        Spannable span1 = (Spannable) binding.checkbox1.getText();
        //글자 크기 다르게
        span1.setSpan(new AbsoluteSizeSpan(80), 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //색 다르게
        span1.setSpan(new ForegroundColorSpan(Color.rgb(17,17,17)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //스타일 다르게
        span1.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //이동 보조 버튼
        binding.checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Spannable span2 = (Spannable) binding.checkbox2.getText();
        //글자 크기 다르게
        span2.setSpan(new AbsoluteSizeSpan(80), 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //색 다르게
        span2.setSpan(new ForegroundColorSpan(Color.rgb(17,17,17)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //스타일 다르게
        span2.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //산책 동반 버튼
        binding.checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Spannable span3 = (Spannable) binding.checkbox3.getText();
        //글자 크기 다르게
        span3.setSpan(new AbsoluteSizeSpan(80), 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //색 다르게
        span3.setSpan(new ForegroundColorSpan(Color.rgb(17,17,17)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //스타일 다르게
        span3.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //기타 버튼
        binding.checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Spannable span4 = (Spannable) binding.checkbox4.getText();
        span4.setSpan(new AbsoluteSizeSpan(80), 0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //색 다르게
        span4.setSpan(new ForegroundColorSpan(Color.rgb(17,17,17)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //스타일 다르게
        span4.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        // 주소지 설정
        //기존 주소지 버튼을 누르면 existingLayout만 보이도록 설정
        binding.existingBtn.setOnClickListener( v -> {
            binding.existingLayout.setVisibility(View.VISIBLE);
            binding.newLayout.setVisibility(View.GONE);
        });

        //신규 주소지 버튼을 누르면 newLayout만 보이도록 설정
        binding.newBtn.setOnClickListener( v -> {
            binding.existingLayout.setVisibility(View.GONE);
            binding.newLayout.setVisibility(View.VISIBLE);
        });


        //main 등록 버튼 activity
        binding.enrollBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                startActivity(intent);
            }
        });


        //calendar 버튼 설정
        dateText = (TextView) findViewById(R.id.date_textView);
        datePickerBtn = (Button) findViewById(R.id.date_btn);

        //날짜 제한 변수
        Calendar minDate = Calendar.getInstance();

        //버튼 클릭 이벤트
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int Year = cal.get(Calendar.YEAR); //년
                int Month = cal.get(Calendar.MONTH);//월
                int Day = cal.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(SimpleActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month + 1;
                        String date = year + "년 " + month + "월 " + day + "일";

                        dateText.setText(date);
                    }
                    }, Year, Month, Day);

                //시작 날짜 제한
                minDate.set(Year,Month,Day);
                datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());

                datePickerDialog.show();
            }
        });
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}