package com.cookandroid.with.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.CashHistoryActivity;
import com.cookandroid.with.R;
import com.cookandroid.with.SeniorHomeActivity;
import com.cookandroid.with.SeniorProfileReviseActivity;
import com.cookandroid.with.SeniorProfileSettingActivity;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.login.HelperLoginActivity;
import com.cookandroid.with.topic.GetRequest;
import com.cookandroid.with.topic.GetTopic;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/*written by 병훈*/

/* 필요 기능
* 1. 결제 버튼(완료)
* 2. 수정 버튼(완료)
* 3. 설정 버튼(완료)
* 4. 데이터 띄우기(50%) - 특이사항, 지병 쪽 수정해야함.
* */

public class SeniorProfileActivity extends AppCompatActivity {

    ImageView btn_cash;// 결제 버튼 선언
    ImageView btn_revise; //수정 버튼 선언
    ImageView btn_setting; //세팅 버튼 선언

    private TextView txtName,txtSex,txtAge,txtAddress; // 이름, 성별, 이름, 주소
    private String ID, address; // 아이디, 주소
    private int age; // 나이

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_profile);

        //툴바
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("시니어 프로필");

        //위젯 연결
        btn_cash = (ImageView) findViewById(R.id.btn_cash);
        btn_revise = (ImageView) findViewById(R.id.btn_revise);
        btn_setting = (ImageView) findViewById(R.id.btn_setting);
        txtName=(TextView)findViewById(R.id.txtName);
        txtAge=(TextView)findViewById(R.id.txtAge);
        txtSex=(TextView)findViewById(R.id.txtSex);
        txtAddress=(TextView)findViewById(R.id.txtAddress);

        //쿠키 가져옴
        Cookie cookie=Cookie.getCookie();
        cookie.readCookie();
        ID = cookie.getID();

        // 데이터 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){ // 회원가입이 가능한다면
                        txtName.setText(jsonResponse.getString("name"));
                        txtSex.setText(jsonResponse.getString("sex"));
                        txtAge.setText(Integer.toString(2022-Integer.parseInt(jsonResponse.getString("birth").substring(0,4))));
                        address=jsonResponse.getString("address")+" ";
                        address+=jsonResponse.getString("address_detail");
                        txtAddress.setText(address);
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
        RequestQueue queue = Volley.newRequestQueue(SeniorProfileActivity.this);
        queue.add(getRequest);

        //결제 버튼 기능
        btn_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CashHistoryActivity.class);
                startActivity(intent);
            }
        });

        //프로필 수정 버튼 기능
        btn_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeniorProfileReviseActivity.class);
                startActivity(intent);
            }
        });

        //개인정보 세팅 버튼 기능
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeniorProfileSettingActivity.class);//변경 예정
                startActivity(intent);
            }
        });
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
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