package com.cookandroid.with.board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.R;
import com.cookandroid.with.login.Cookie;

import org.json.JSONObject;

public class BoardInsert extends AppCompatActivity {
    Button insert;
    String userID,subject,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_insert);

        insert=(Button) findViewById(R.id.button);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
                Cookie cookie=Cookie.getCookie();
                cookie.readCookie();
                userID = cookie.getID();
                subject="제목";
                content="내용";

                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                InsertRequest insertRequest = new InsertRequest(userID, subject, content, responseListener);
                RequestQueue queue = Volley.newRequestQueue(BoardInsert.this);
                queue.add(insertRequest);
            }
        });
    }
}