package com.cookandroid.with.confirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.Complete1Activity;
import com.cookandroid.with.Complete2Activity;
import com.cookandroid.with.R;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.databinding.ActivityNoxConfirmBinding;
import com.cookandroid.with.simple.NoxActivity;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class NoxConfirmActivity extends AppCompatActivity {
    private ActivityNoxConfirmBinding binding;
    public String ID, dayText, dayText2, timeText, dateTime;
    static String way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoxConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ") ;

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
                        dayText2 = dayText.replaceFirst("-", "월 ") + "일 ";

                        timeText = jsonResponse.getString("StartTime").substring(11,16);
                        timeText = timeText.replaceFirst(":", "시 ") + "분";

                        dateTime = dayText2 + timeText;

                        way = jsonResponse.getString("Way");

                        binding.titleText.setText(jsonResponse.getString("title"));
                        binding.tag.setText(jsonResponse.getString("Needs"));
                        binding.startAddText.setText(jsonResponse.getString("StartDes"));
                        binding.endAddText.setText(jsonResponse.getString("EndDes"));
                        binding.dateText.setText(dateTime);
                        binding.wayText.setText(way);
                        binding.contentText.setText(jsonResponse.getString("content"));

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
        GetNoxConfirm getNoxConfirm = new GetNoxConfirm(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NoxConfirmActivity.this);
        queue.add(getNoxConfirm);

        //수정 버튼
        binding.modifyBtn.setOnClickListener(v -> {
            super.onBackPressed();
        });

        //확인 버튼
        binding.confirmBtn.setOnClickListener(v -> {
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
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}