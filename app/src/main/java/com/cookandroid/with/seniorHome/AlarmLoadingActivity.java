package com.cookandroid.with.seniorHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.R;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.selectMatch.GetSelectMatchRequest;
import com.cookandroid.with.selectMatch.SelectLoadingActivity;
import com.cookandroid.with.selectMatch.SelectMatchList;

import org.json.JSONObject;

public class AlarmLoadingActivity extends AppCompatActivity {
    private String[] dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loading);

        Cookie cookie=Cookie.getCookie();
        cookie.checkCookie();
        //cookie.clearCookie();//자동로그인 방지용
        cookie.readCookie();
        String userID = cookie.getID();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        int length = jsonResponse.length();
                        dataList = new String[length];
                        for (int i = 0; i <= length; i++) {
                            String data = jsonResponse.getString(String.valueOf(i));
                            dataList[i] = data;
                        }
                    } else {
                        dataList = new String[0];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        SeniorAlarmRequest seniorAlarmRequest = new SeniorAlarmRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AlarmLoadingActivity.this);
        queue.add(seniorAlarmRequest);

        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AlarmLoadingActivity.this, SeniorAlarmActivity.class);
                intent.putExtra("dataList", dataList);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}