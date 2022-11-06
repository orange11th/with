package com.cookandroid.with.selectMatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.MainActivity2;
import com.cookandroid.with.R;
import com.cookandroid.with.SeniorHomeActivity;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.loading.LoadingActivity;
import com.cookandroid.with.login.LoginActivity;
import com.cookandroid.with.login.LoginRequest;

import org.json.JSONObject;

public class SelectLoadingActivity extends AppCompatActivity {
    private String[] dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loading);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        int length = jsonResponse.length();
                        dataList = new String[length * 4];
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
        GetSelectMatchRequest getSelectMatchRequest = new GetSelectMatchRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelectLoadingActivity.this);
        queue.add(getSelectMatchRequest);

        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SelectLoadingActivity.this, SelectMatchList.class);
                intent.putExtra("dataList", dataList);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}