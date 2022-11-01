package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.login.LoginActivity;
import com.cookandroid.with.login.LoginRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/*written by 병훈*/

public class HelperProfileActivity extends AppCompatActivity {

    //user 테이블에서 받아올 데이터
    TextView name;
    TextView sex;
    TextView birth;
    TextView address;
    TextView license1;
    TextView license2;
    TextView needs1;
    TextView needs2;

    //Matching 테이블에서 받아올 데이터
    TextView StartTime;
    RatingBar AfterStar;
    TextView Aftercontent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        //위젯 연결
        name = findViewById(R.id.name);
        sex= findViewById(R.id.sex);
        birth= findViewById(R.id.birth);
        address= findViewById(R.id.address);
        license1= findViewById(R.id.license1);
        license2= findViewById(R.id.license2);
        needs1= findViewById(R.id.needs1);
        needs2= findViewById(R.id.needs2);
        StartTime= findViewById(R.id.StartTime);
        AfterStar= findViewById(R.id.AfterStar);
        Aftercontent= findViewById(R.id.Aftercontent);


        String nameString ="";
        String sexString ="";
        String birthString ="";
        String addressString ="";
        String license1String ="";
        String license2String ="";
        String needs1String ="";
        String needs2String ="";
        String StartTimeString ="";
        String AfterStarString ="";
        String AftercontentString ="";


        //응답 코드
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    //응답 JSON으로 받기
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        //데이터 받기
                        String name = jsonResponse.getString("name");
                        String sex = jsonResponse.getString("sex");
                        String birth = jsonResponse.getString("birth");
                        String address = jsonResponse.getString("address");
                        String license1 = jsonResponse.getString("license1");
                        String license2 = jsonResponse.getString("license2");
                        String needs1 = jsonResponse.getString("needs1");
                        String needs2 = jsonResponse.getString("needs2");
                        String StartTime = jsonResponse.getString("StartTime");
                        String AfterStar = jsonResponse.getString("AfterStar");
                        String Aftercontent = jsonResponse.getString("Aftercontent");

                    } else {
                        return;
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        //요청 호출
        HelperProfileRequest helperProfileRequest = new HelperProfileRequest(name, sex, birth, address, license1, license2,needs1, needs2, StartTime, AfterStar, Aftercontent, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HelperProfileActivity.this);
        queue.add(helperProfileRequest);

        name.setText(nameString);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_helperProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        

        //버튼 클릭 이벤트 - 수정 버튼
        ImageView image1 = findViewById(R.id.btn_revise);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //프로필 수정 화면으로 전환
                Intent intent1 = new Intent(getApplicationContext(), HelperProfileReviseActivity.class);
                startActivity(intent1);
            }
        });

        //버튼 클릭 이벤트 - 설정 버튼
        ImageView image2 = findViewById(R.id.btn_setting);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //세팅화면으로 전환
                Intent intent2 = new Intent(getApplicationContext(), HelperPrivacySettingActivity.class);
                startActivity(intent2);
            }
        });



        //PHP 파일에서 데이터 가져오기

        
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                //메인화면으로 전환
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}