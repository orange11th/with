package com.cookandroid.with.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.R;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.register.HelperRegisterActivity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperLoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
    TextView txtBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_login);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        edtUsername=(EditText)findViewById(R.id.username);
        edtPassword=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.login);
        txtBtn=(TextView)findViewById(R.id.txtBtn_Register);
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

                                Intent intent = new Intent(getApplicationContext(), HelperHomeActivity.class);
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
                RequestQueue queue = Volley.newRequestQueue(HelperLoginActivity.this);
                queue.add(loginRequest);
            }
        });

        txtBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        //회원가입 버튼 클릭
                        Intent intent = new Intent(getApplicationContext(), HelperRegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


