package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.profile.GetSeniorRequest;
import com.cookandroid.with.profile.SeniorProfileActivity;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/*written by 병훈*/

/*개요
* 1. 데이터 띄우기(50%)(원하는 도움 종류 부분 미완)(지역 edittext 크기 늘릴 것.)(주소 검색으로 바꿀 것)
* 2. 원하는 도움 종류 수정(미완)
* 3. 저장 버튼 기능 - 수정된 데이터로 update 해야함 (미완)*/

public class SeniorProfileReviseActivity extends AppCompatActivity {

    //위젯 선언
    private Button btn_save; //저장 버튼

    private EditText edt_name; //이름
    private TextView tv_sex; //성별
    private TextView tv_age; //나이
    private EditText edt_address; //지역
    private EditText edt_sick; //특이사항

    private String ID, address;

    //모르겠음.//원하는 도움 종류


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_profile_revise);

        //toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_seniorproProfileRevise);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("프로필 수정");

        //위젯 연결
        btn_save = findViewById(R.id.btn_save_senior_profile);

        edt_name = findViewById(R.id.edt_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_age = findViewById(R.id.tv_age);
        edt_address = findViewById(R.id.edt_address);
        edt_sick = findViewById(R.id.edt_sick);

        //쿠키 가져옴
        Cookie cookie=Cookie.getCookie();
        cookie.readCookie();
        ID = cookie.getID();

        //데이터 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){ // 회원가입이 가능한다면
                        edt_name.setText(jsonResponse.getString("name"));
                        tv_sex.setText(jsonResponse.getString("sex"));
                        tv_age.setText(Integer.toString(2022-Integer.parseInt(jsonResponse.getString("birth").substring(0,4))));
                        address=jsonResponse.getString("address")+" ";
                        address+=jsonResponse.getString("address_detail");
                        edt_address.setText(address);
                        //Toast.makeText(getApplicationContext(), "프로필 가져오기 성공", Toast.LENGTH_SHORT).show();
                    }else{// 회원가입이 안된다면
                        Toast.makeText(getApplicationContext(), "프로필 가져오기 실패", Toast.LENGTH_SHORT).show();
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
        GetSeniorRequest getRequest = new GetSeniorRequest(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SeniorProfileReviseActivity.this);
        queue.add(getRequest);


    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), SeniorProfileActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}