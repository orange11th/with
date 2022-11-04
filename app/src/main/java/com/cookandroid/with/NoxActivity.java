package com.cookandroid.with;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.databinding.ActivityNoxBinding;
import com.cookandroid.with.register.WebViewActivity;
import com.cookandroid.with.simple.SimpleActivity;

import org.json.JSONObject;

import java.util.Calendar;

public class NoxActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityNoxBinding bd;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private RadioGroup radioGroup1, radioGroup2;
    static String typeBtn, howBtn, titleValue, dateValue;
    private EditText titleText;
    private TextView dateTextView;
    private RadioButton rBtn;
    DatePickerDialog datePickerDialog;

    String ID, title, needs, startDes, endDes, time, way, content;

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
        radioGroup1 = findViewById(R.id.radioGroup1);
        //radioGroup1의 버튼 체크 상태를 풂
        radioGroup1.clearCheck();
        //라디오버튼의 상태가 변할 때 listener1 실행
        radioGroup1.setOnCheckedChangeListener(listener1);

        radioGroup2 = findViewById(R.id.radioGroup2);
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

        //주소 검색
        bd.searchBtn.setOnClickListener(v->{
            Intent i = new Intent(NoxActivity.this, WebViewActivity.class);
            startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
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


        //구인 방법 라디오 버튼 값 가져오기
        bd.radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                howBtn = ((RadioButton) findViewById(checkedId)).getText().toString();
                Log.d("Tag", "data:" + howBtn);
            }
        });


        titleText = ((EditText) findViewById(R.id.titleText));
        dateTextView = ((TextView) findViewById(R.id.dateTextView));;

        //등록 버튼을 누르면 NoxConfirm으로 값 보냄
        bd.enrollBtn.setOnClickListener(v -> {
            // 현재 입력된 정보를 string으로 가져오기

            rBtn = (RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId());

            //start 값
            RadioGroup radioGroup3 = (RadioGroup) findViewById( R.id.radioGroup3);
            RadioButton rBtn3 = (RadioButton) findViewById(radioGroup3.getCheckedRadioButtonId());

            //how 값
            RadioGroup radioGroup4 = (RadioGroup) findViewById( R.id.radioGroup4);
            RadioButton rBtn4 = (RadioButton) findViewById(radioGroup4.getCheckedRadioButtonId());

            //ID = "testNox"; /* 수정 필요 */
            title = bd.titleText.getText().toString();
            needs = "병원동행"; //rBtn.getText().toString();
            startDes = rBtn3.getText().toString();
            endDes = bd.addressText3.getText().toString() + bd.addressText4.getText().toString();
            time = bd.dateTextView.getText().toString() + bd.hourSpinner.getSelectedItem().toString() + bd.minuteSpinner.getSelectedItem().toString();
            way = rBtn4.getText().toString();
            content = bd.contentsText.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try{
                        // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                            Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
            InsertNox insertNox = new InsertNox(title, needs, startDes, endDes, time, way, content, responseListener);
            RequestQueue queue = Volley.newRequestQueue(NoxActivity.this);
            queue.add(insertNox);

            //titleText의 텍스트 가져오기
            titleValue = titleText.getText().toString();
            dateValue = dateTextView.getText().toString();

            Intent intent = new Intent(NoxActivity.this, NoxConfirmActivity.class);
            //Log.d("Tag", "data:" + typeBtn);
            //Log.d("Tag", "data:" + howBtn);
            //Log.d("Tag", "data:" + titleValue);

            //데이터 값 전달
            intent.putExtra("TITLE", titleValue);
            intent.putExtra("TYPE", typeBtn);
            intent.putExtra("DATE", dateValue);
            intent.putExtra("HOW", howBtn);
            setResult(RESULT_OK, intent);
            startActivity(intent); //안 넣으면 종료됨
            finish();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        bd.addressText3.setText(data);
                    }
                }
                break;
        }
    }

    //도움 종류 2x3 라디오 버튼 리스너
    private final RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);
            }
            //라디오 버튼 값 가져오기
            typeBtn = ((RadioButton) findViewById(checkedId)).getText().toString();
        }
    };

    private final RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup1.setOnCheckedChangeListener(null);
                radioGroup1.clearCheck();
                radioGroup1.setOnCheckedChangeListener(listener1);
            }
            typeBtn = ((RadioButton) findViewById(checkedId)).getText().toString();
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