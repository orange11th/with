package com.cookandroid.with;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.with.databinding.ActivityNoxBinding;

import java.util.Calendar;

public class NoxActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityNoxBinding bd;
    private RadioGroup radioGroup1, radioGroup2;
    public static String btnValue;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        bd = ActivityNoxBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbarNox);
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

        /* 누르는 도움 종류에 따라 위치 레이아웃 다르게 보이도록 설정 */

        //출발지, 목적지 레이아웃만
        //병원동행
        bd.radioBtn1.setOnClickListener( v -> {
            bd.destLayout.setVisibility((View.VISIBLE));
            bd.locationLayout.setVisibility(View.GONE);
        });

        //이동보조
        bd.radioBtn2.setOnClickListener( v -> {
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
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //말동무
        bd.radioBtn4.setOnClickListener( v -> {
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //취미
        bd.radioBtn5.setOnClickListener( v -> {
            bd.destLayout.setVisibility((View.GONE));
            bd.locationLayout.setVisibility(View.VISIBLE);
        });

        //기타
        bd.radioBtn6.setOnClickListener( v -> {
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

        //날짜 버튼 클릭 이벤트
        bd.dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int Year = cal.get(Calendar.YEAR); //년
                int Month = cal.get(Calendar.MONTH);//월
                int Day = cal.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(NoxActivity.this,
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

        //라디오 버튼 값 가져오기
        bd.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 선택한 항목에 따라 다른 값 입력 받도록 설정
                btnValue = ((RadioButton) findViewById(checkedId)).toString();
                Log.d("Tag123test", "data:" + btnValue);

            }
        });


        //등록 버튼을 누르면 NoxConfirm으로 라디오 버튼 값 보냄
        bd.enrollBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NoxConfirmActivity.class);
            Log.d("Tag123", "data:" + btnValue);
            //데이터 값 전달
            intent.putExtra("HOW", btnValue);
            setResult(RESULT_OK, intent);
            startActivity(intent); //안 넣으면 액티비티 종료됨
            finish();
        });
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);
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
            }
        }
    };

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enrollBtn) {
            if (radioGroup1.getCheckedRadioButtonId() > 0) {
                View radioButton = radioGroup1.findViewById(radioGroup1.getCheckedRadioButtonId());
                int radioId = radioGroup1.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup1.getChildAt(radioId);
            } else if (radioGroup2.getCheckedRadioButtonId() > 0) {
                View radioButton = radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
                int radioId = radioGroup2.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup2.getChildAt(radioId);
            }
        }
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