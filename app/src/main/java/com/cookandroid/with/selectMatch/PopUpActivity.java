package com.cookandroid.with.selectMatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.R;
import com.cookandroid.with.SelectMatchNotice;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.login.LoginActivity;
import com.cookandroid.with.register.RegistRequest;
import com.cookandroid.with.register.RegisterActivity;

import org.json.JSONObject;

public class PopUpActivity extends AppCompatActivity {
    private Button btnAccept,btnRefuse;
    private TextView txtText;
    private String title,tag,region,contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up);
        btnAccept=(Button)findViewById(R.id.btnAccept);
        btnRefuse=(Button)findViewById(R.id.btnRefuse);
        txtText=(TextView)findViewById(R.id.txtText);

        Intent intent=getIntent();
        title= intent.getStringExtra("title");//StartTime
        tag= intent.getStringExtra("tag");//Needs
        region= intent.getStringExtra("region");//Matching_id DB입력할때 쓸예정
        contents= intent.getStringExtra("contents");//StartDes

        txtText.setText(tag+"\n\n시작 일시 : "+title+"\n\n출발지 주소\n[ "+contents+" ]");


        btnAccept.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // 회원가입 절차 시작
                Cookie cookie = Cookie.getCookie();
                cookie.readCookie();
                String Helper_id = cookie.getID();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "매칭 신청 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PopUpActivity.this, SelectMatchNotice.class);
                                startActivity(intent);
                                finish();
                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "매칭 신청 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                PopUpRequest popUpRequest = new PopUpRequest(region.substring(2),Helper_id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(PopUpActivity.this);
                queue.add(popUpRequest);
            }
        });
        btnRefuse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }
}