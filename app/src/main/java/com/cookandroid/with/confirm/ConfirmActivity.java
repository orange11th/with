package com.cookandroid.with.confirm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.Complete1Activity;
import com.cookandroid.with.Complete2Activity;
import com.cookandroid.with.R;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.databinding.ActivityConfirmBinding;
import com.cookandroid.with.simple.SimpleActivity;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;


public class ConfirmActivity extends AppCompatActivity {
    private ActivityConfirmBinding binding;
    private String ID, dayText, dayText2, timeText;
    static String way;
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

        Cookie cookie = Cookie.getCookie();
        cookie.readCookie();
        ID = cookie.getID();

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        dayText = jsonResponse.getString("StartTime").substring(0,10);
                        dayText = dayText.replaceFirst("-", "년 ");
                        dayText2 = dayText.replaceFirst("-", "월 ") +"일";

                        timeText = jsonResponse.getString("StartTime").substring(11,16);
                        timeText = timeText.replaceFirst(":", "시 ") + "분";

                        way = jsonResponse.getString("Way");

                        binding.needsText.setText(jsonResponse.getString("Needs"));
                        binding.startAddText.setText(jsonResponse.getString("StartDes"));
                        binding.endAddText.setText(jsonResponse.getString("EndDes"));
                        binding.dateText.setText(dayText2);
                        binding.timeText.setText(timeText);
                        binding.wayText.setText(way);

                        Toast.makeText(getApplicationContext(), "가져오기 성공", Toast.LENGTH_SHORT).show();
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
        GetConfirm getConfirm = new GetConfirm(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ConfirmActivity.this);
        queue.add(getConfirm);


        //수정 버튼
        binding.modifyBtn.setOnClickListener(v->{
            super.onBackPressed();
        });

        //등록 버튼
        binding.confirmBtn.setOnClickListener( v -> {
            if (Objects.equals(way, "빠르게 돌보미 구하기")) {
                Intent intent = new Intent(getApplicationContext(), Complete1Activity.class);
                startActivity(intent);
            } else if (Objects.equals(way, "지원한 돌보미 중 선택하기")){
                Intent intent = new Intent(getApplicationContext(), Complete2Activity.class);
                startActivity(intent);
            }
        });

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