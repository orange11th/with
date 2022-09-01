package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class SimpleActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, datePickerBtn;

    TextView dateText;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        //버튼 글자 크기 다르게
        ArrayList<Button> btnLIst=new ArrayList<Button>();

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn1.setSelected(true);
            }
        });

        Spannable span1 = (Spannable) btn1.getText();
        span1.setSpan(new AbsoluteSizeSpan(43), 6,33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn2.setSelected(true);
            }
        });

        Spannable span2 = (Spannable) btn2.getText();
        span2.setSpan(new AbsoluteSizeSpan(43), 6,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn3.setSelected(true);
            }
        });

        Spannable span3 = (Spannable) btn3.getText();
        span3.setSpan(new AbsoluteSizeSpan(44), 6,46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn4.setSelected(true);
            }
        });

        Spannable span4 = (Spannable) btn4.getText();
        span4.setSpan(new AbsoluteSizeSpan(44), 3,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //main 등록 버튼 activity
        Button enroll_btn = (Button) findViewById(R.id.enroll_btn);
        enroll_btn.setOnClickListener(new View.OnClickListener(){

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
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}