package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cookandroid.with.databinding.ActivitySimpleBinding;

import java.util.Calendar;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySimpleBinding bd;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivitySimpleBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        /* 라디오 버튼 처리 */
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        //radioGroup1의 버튼 체크 상태를 풂
        radioGroup1.clearCheck();
        //라디오버튼의 상태가 변할 때 listener1 실행
        radioGroup1.setOnCheckedChangeListener(listener1);

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        radioGroup2.setOnCheckedChangeListener(listener2);

        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup3.clearCheck();
        radioGroup3.setOnCheckedChangeListener(listener3);


        /* 누르는 도움 종류에 따라 위치 레이아웃 다르게 보이도록 설정 */

        //출발지, 목적지 레이아웃만
        //병원동행
        bd.radioBtn1.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.GONE);
            bd.ectLayout.setVisibility(View.GONE);
            bd.destLayout.setVisibility((View.VISIBLE));
            bd.locationLayout.setVisibility(View.GONE);
        });

        //이동보조
        bd.radioBtn2.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.GONE);
            bd.ectLayout.setVisibility(View.GONE);
            bd.destLayout.setVisibility((View.VISIBLE));
            bd.locationLayout.setVisibility(View.GONE);
        });

        //기존 주소지 버튼을 누르면 existingLayout만 보이도록 설정
        bd.existingBtn.setOnClickListener( v -> {
            bd.existingLayout.setVisibility(View.VISIBLE);
            bd.newLayout.setVisibility(View.GONE);
        });

        //신규 주소지 버튼을 누르면 newLayout만 보이도록 설정
        bd.newBtn.setOnClickListener( v -> {
            bd.existingLayout.setVisibility(View.GONE);
            bd.newLayout.setVisibility(View.VISIBLE);
        });


        //장소 레이아웃만
        //가사돌봄
        bd.radioBtn3.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.GONE);
            bd.ectLayout.setVisibility(View.GONE);
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //말동무
        bd.radioBtn4.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.GONE);
            bd.ectLayout.setVisibility(View.GONE);
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //취미
        bd.radioBtn5.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.VISIBLE);
            bd.ectLayout.setVisibility(View.GONE);
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //기타
        bd.radioBtn6.setOnClickListener( v -> {
            bd.hobbyLayout.setVisibility(View.GONE);
            bd.ectLayout.setVisibility(View.VISIBLE);
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //기존 주소지 버튼을 누르면 existingLayout2만 보이도록 설정
        bd.existingBtn2.setOnClickListener( v-> {
            bd.existingLayout2.setVisibility(View.VISIBLE);
            bd.newLayout2.setVisibility(View.GONE);
        });

        //신규 주소지 버튼을 누르면 newLayout2만 보이도록 설정
        bd.newBtn2.setOnClickListener( v -> {
            bd.existingLayout2.setVisibility(View.GONE);
            bd.newLayout2.setVisibility(View.VISIBLE);
        });


        //날짜 제한 변수
        Calendar minDate = Calendar.getInstance();

        //버튼 클릭 이벤트
        bd.dateBtn.setOnClickListener(new View.OnClickListener() {
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

                                bd.dateTextView.setText(date);
                            }
                        }, Year, Month, Day);

                //시작 날짜 제한
                minDate.set(Year,Month,Day);
                datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());

                datePickerDialog.show();
            }
        });


        //등록 버튼
        bd.enrollBtn.setOnClickListener( v-> {
            Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
            startActivity(intent);
        });

    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);

                radioGroup3.setOnCheckedChangeListener(null);
                radioGroup3.clearCheck();
                radioGroup3.setOnCheckedChangeListener(listener3);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup1.setOnCheckedChangeListener(null);
                radioGroup1.clearCheck();
                radioGroup1.setOnCheckedChangeListener(listener1);

                radioGroup3.setOnCheckedChangeListener(null);
                radioGroup3.clearCheck();
                radioGroup3.setOnCheckedChangeListener(listener3);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup1.setOnCheckedChangeListener(null);
                radioGroup1.clearCheck();
                radioGroup1.setOnCheckedChangeListener(listener1);

                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);
            }
        }
    };

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enrollBtn) {

            //이해x
            if (radioGroup1.getCheckedRadioButtonId() > 0) {
                View radioButton = radioGroup1.findViewById(radioGroup1.getCheckedRadioButtonId());
                int radioId = radioGroup1.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup1.getChildAt(radioId);
            } else if (radioGroup2.getCheckedRadioButtonId() > 0) {
                View radioButton = radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
                int radioId = radioGroup2.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup2.getChildAt(radioId);
            } else if (radioGroup3.getCheckedRadioButtonId() > 0) {
                View radioButton = radioGroup3.findViewById(radioGroup3.getCheckedRadioButtonId());
                int radioId = radioGroup3.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup3.getChildAt(radioId);
            }
        }
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