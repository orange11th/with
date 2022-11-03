package com.cookandroid.with.confirm;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.Complete1Activity;
import com.cookandroid.with.R;
import com.cookandroid.with.databinding.ActivityConfirmBinding;
import com.cookandroid.with.simple.SimpleActivity;
import com.cookandroid.with.topic.GetRequest;
import com.cookandroid.with.topic.GetTopic;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ConfirmActivity extends AppCompatActivity {
    private ActivityConfirmBinding binding;
    List<String> mSelectedItems;
    AlertDialog.Builder builder;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    String matchingID, needs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_confirm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        needs="";

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        needs = jsonResponse.getString("needs");
                        binding.needsText.setText(needs);
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                catch(Exception e){
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    Toast.makeText(getApplicationContext(), errors.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        GetConfirm getConfirm = new GetConfirm(matchingID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ConfirmActivity.this);
        queue.add(getConfirm);


        /* 선착순, 지원에 따라 완료 화면 다르게 뜨도록 수정 필요 */
        //등록 버튼
        binding.confirmBtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), Complete1Activity.class);
            startActivity(intent);
        });

        binding.changeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });

        //도움 장소 변경하기
        binding.changeBtn2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                //클릭 이벤트
                //다이얼로그로 EditText를 띄우고, 넣은 값을 textview로 만든다.
                final EditText editText = new EditText(ConfirmActivity.this);

                /* 주소 검색 기능 추가 필요 */

                AlertDialog.Builder dig = new AlertDialog.Builder(ConfirmActivity.this);
                dig.setTitle("장소가 어디인가요?");
                dig.setView(editText);
                dig.setIcon(R.drawable.ic_launcher_background);
                dig.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //입력 버튼이 눌리면, 텍스트를 바꿔주자.
                        binding.addressText.setText(editText.getText().toString());
                        //Toast.makeText(getApplicationContext(), editText.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                dig.show();
            }

        });

        //도움 시간 변경
        binding.changeBtn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //뷰는 스택 형태이기 때문에 showTime()을 먼저 호출한다.
                showTime();
                showDate();
            }
        });

        //구인 방법 변경
        binding.changeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });

    }

    //도움 종류 변경 메서드
    void showDialog1(){

        //리스트를 만들고
        mSelectedItems = new ArrayList<>();
        builder = new AlertDialog.Builder(ConfirmActivity.this);
        builder.setTitle("도움 종류를 선택해주세요.");

        //string.xml에 만들어둔 kindOfHelp를 가져와서 체크박스로 만듭니다.
        builder.setMultiChoiceItems(R.array.kindOfHelp, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                //눌리면 데이터를 리스트에 담습니다.
                String[]items = getResources().getStringArray(R.array.kindOfHelp);

                if(isChecked){
                    mSelectedItems.add(items[which]);
                }else if(mSelectedItems.contains(items[which])){
                    mSelectedItems.remove(items[which]);
                }
            }
        });

        //확인을 누르면, 리스트에 있는 데이터가 버튼으로 만들어지게 만듭니다.
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //리니어레이아웃을 가져와서 textview를 추가하는 방법을 상용한다.
                LinearLayout ll = findViewById(R.id.linearLayout1);
                ll.removeAllViews();

                for(String item : mSelectedItems) {
                    TextView tv = new TextView(getApplicationContext());

                    tv.setText(item);// 배열값을 가져와서 텍스트로 보이게하기
                    tv.setTextSize(20);

                    //레이아웃 설정
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    param.rightMargin = 625;
                    tv.setLayoutParams(param);

                    //텍스트뷰 배경, 정렬, 텍스트 색상 설정
                    tv.setBackground(getResources().getDrawable(R.drawable.confirm_btn_background));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextColor(Color.rgb(34,34,34));

                    //생성및 설정된 텍스트뷰 레이아웃에 적용하기
                    ll.addView(tv);
                }

                //여기서 잠시 멈춤 : 확인 버튼을 누르면 값을 어떻게 보여줄지 결정해야함.
                TextView text1 = findViewById(R.id.needsText);
            }
        });

        //취소 이벤트
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //날짜 변경 메서드
    void showDate() {

        Calendar cal = Calendar.getInstance();
        int Year = cal.get(Calendar.YEAR); //년
        int Month = cal.get(Calendar.MONTH);//월
        int Day = cal.get(Calendar.DAY_OF_MONTH);//일

        datePickerDialog = new DatePickerDialog(ConfirmActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "년 " + month + "월 " + day + "일";

                        binding.dateText.setText(date);
                    }
                }, Year, Month, Day);

        //다이얼로그 제목
        datePickerDialog.setMessage("날짜를 선택하세요");
        //시작 날짜 제한
        cal.set(Year,Month,Day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());

        datePickerDialog.show();
    }

    //시간 변경 메서드
    void showTime() {

        Calendar cal = Calendar.getInstance();
        int HOUR = cal.get(Calendar.HOUR); //년
        int MINUTE = cal.get(Calendar.MINUTE);//월
        //int style = AlertDialog.THEME_HOLO_LIGHT;

        timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String time = hourOfDay + "시 " + minute + "분";

                binding.timeText.setText(time);

            }
        }, HOUR, MINUTE, true); //현재 시간으로 변경하기
        timePickerDialog.setMessage("시간을 선택하세요.");

        timePickerDialog.show();//다이얼로그 띄우기
    }

    //구인방법 다이얼로그
    void showDialog4() {
        final CharSequence[] oltems = {"선착순", "선택"};

        AlertDialog.Builder oDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        oDialog.setTitle("구인 방법을 선택해주세요.")
                .setItems(oltems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //oltems[which]가 목록 중 선택한 값이 됩니다.
                //선택한 값으로 텍스트로 바꿔줍니다.
                binding.howText.setText(oltems[which]);
            }
        }).setCancelable(false).show();//setCancelable 메소드 - 백버튼을 사용하지 못하게 만든다.
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), SimpleActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}