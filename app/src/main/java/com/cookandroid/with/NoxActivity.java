package com.cookandroid.with;

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

public class NoxActivity extends AppCompatActivity {
    ActivityNoxBinding binding;
    public static String btnValue;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityNoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbarNox);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        //날짜 제한 변수
        Calendar minDate = Calendar.getInstance();

        //날짜 버튼 클릭 이벤트
        binding.dateBtn.setOnClickListener(new View.OnClickListener() {
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

                                binding.dateTextView.setText(date);
                            }
                        }, Year, Month, Day);

                //시작 날짜 제한
                minDate.set(Year,Month,Day);
                datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());

                datePickerDialog.show();
            }
        });

        //라디오 버튼 값 가져오기
        binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 선택한 항목에 따라 다른 값 입력 받도록 설정
                btnValue = ((RadioButton) findViewById(checkedId)).toString();
            }
        });

        //등록 버튼을 누르면 NoxConfirm으로 라디오 버튼 값 보냄
        binding.enrollBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NoxConfirmActivity.class);
            Log.d("Tag", "data:" + btnValue);
            //데이터 값 전달
            intent.putExtra("HOW", btnValue);
            setResult(RESULT_OK, intent);
            startActivity(intent); //안 넣으면 액티비티 종료됨
            finish();
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
