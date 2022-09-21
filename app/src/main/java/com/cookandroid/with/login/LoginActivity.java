package com.cookandroid.with.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.MainActivity;
import com.cookandroid.with.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername=(EditText)findViewById(R.id.username);
        edtPassword=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.login);
        btnRegister=(Button)findViewById(R.id.register);
        Cookie cookie=Cookie.getCookie();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userID = edtUsername.getText().toString();
                String userPassword = edtPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();

                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                Cookie cookie=Cookie.getCookie();
                                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                                String date=sdf.format(new Date(System.currentTimeMillis()));
                                cookie.writeCookie(userID,userPassword,date);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                // 로그인 하면서 사용자 정보 넘기기
                                //intent.putExtra("userID", userID);
                                //intent.putExtra("userPassword", userPassword);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


