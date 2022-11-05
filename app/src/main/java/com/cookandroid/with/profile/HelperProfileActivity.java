package com.cookandroid.with.profile;

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
import com.cookandroid.with.HelperPrivacySettingActivity;
import com.cookandroid.with.HelperProfileReviseActivity;
import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.R;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.login.LoginActivity;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/*written by 병훈*/

public class HelperProfileActivity extends AppCompatActivity {
    private TextView txtName,txtSex,txtAge,txtAddress,txtLicense;
    private String ID, address;
    private int age;
    private Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("돌보미 프로필");

        txtName=(TextView)findViewById(R.id.txtName);
        txtAge=(TextView)findViewById(R.id.txtAge);
        txtSex=(TextView)findViewById(R.id.txtSex);
        txtAddress=(TextView)findViewById(R.id.txtAddress);
        txtLicense=(TextView)findViewById(R.id.txtLicense);
        logoutBtn=(Button)findViewById(R.id.logoutBtn);

        Cookie cookie=Cookie.getCookie();
        cookie.readCookie();
        ID = cookie.getID();

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
                        txtName.setText(jsonResponse.getString("name"));
                        txtSex.setText(jsonResponse.getString("sex"));
                        txtAge.setText(Integer.toString(2022-Integer.parseInt(jsonResponse.getString("birth").substring(0,4))));
                        address=jsonResponse.getString("address")+" ";
                        address+=jsonResponse.getString("address_detail");
                        txtAddress.setText(address);
                        txtLicense.setText(jsonResponse.getString("license"));
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
        GetHelperRequest getRequest = new GetHelperRequest(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HelperProfileActivity.this);
        queue.add(getRequest);

        /* ProfileActivity에서 필요한 기능 정리를 해보자
         * 이 액티비티에는 3개의 버튼이 있다.
         * 1. 수정 버튼
         * 2. 설정 버튼
         * 3. 뒤로가기 버튼
         * 차례대로 구현해보겠습니다.*/

        /*1. <수정 버튼> 클릭시 액티비티 전환하는 코드입니다.*/
        //btn_revise를 id로 가지는 ImageView를 변수 image1에 넣는다.
        ImageView image1 = findViewById(R.id.btn_revise);

        //image1(수정 버튼)이 눌렸을 때
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 사용해서 프로필 수정 화면으로 전환한다.
                Intent intent1 = new Intent(getApplicationContext(), HelperProfileReviseActivity.class);
                startActivity(intent1);
            }
        });

        /*2. <설정 버튼> 클릭시 액티비티 전환하는 코드입니다.*/
        //btn_setting를 id로 가지는 ImageView를 변수 image2에 넣는다.
        ImageView image2 = findViewById(R.id.btn_setting);

        //image2(설정 버튼) 이 눌렸을 때
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 사용해서 프로필 세팅 화면으로 전환한다.
                Intent intent2 = new Intent(getApplicationContext(), HelperPrivacySettingActivity.class);
                startActivity(intent2);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookie.clearCookie();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), HelperHomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}