package com.cookandroid.with.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.R;
import com.cookandroid.with.SeniorHomeActivity;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.login.LoginActivity;
import com.cookandroid.with.login.LoginRequest;

import org.json.JSONObject;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cookie cookie=Cookie.getCookie();
                cookie.checkCookie();
                //cookie.clearCookie();//자동로그인 방지용
                cookie.readCookie();
                String userID = cookie.getID();
                String userPassword = cookie.getPW();
                String date = cookie.getDate();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String role = jsonResponse.getString("role");
                                if(role.equals("돌보미")){
                                    Toast.makeText(getApplicationContext(), role+" 마지막 로그인: "+date, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HelperHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(role.equals("시니어")){
                                    Toast.makeText(getApplicationContext(), role+" 마지막 로그인: "+date, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                //Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();//거슬림
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
                queue.add(loginRequest);
            }
        }, 2000);
    }
}