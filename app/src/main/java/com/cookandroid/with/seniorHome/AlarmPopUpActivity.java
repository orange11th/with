package com.cookandroid.with.seniorHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.MatchingListContents;
import com.cookandroid.with.R;
import com.cookandroid.with.SelectMatchNotice;
import com.cookandroid.with.SelectMatchNotice2Activity;
import com.cookandroid.with.cookie.Cookie;
import com.cookandroid.with.profile.GetSeniorRequest;
import com.cookandroid.with.profile.SeniorProfileActivity;
import com.cookandroid.with.selectMatch.PopUpActivity;
import com.cookandroid.with.selectMatch.PopUpRequest;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AlarmPopUpActivity extends AppCompatActivity {
    private Button btnAccept,btnRefuse;
    private TextView txtText;
    private String name,tag,matching_id,sex,birth,license,needs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm_pop_up);
        btnAccept=(Button)findViewById(R.id.btnAccept);
        btnRefuse=(Button)findViewById(R.id.btnRefuse);
        txtText=(TextView)findViewById(R.id.txtText);

        Intent intent=getIntent();
        tag= intent.getStringExtra("tag");//Needs
        matching_id= intent.getStringExtra("region");//Matching_id DB입력할때 쓸예정

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                    // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){ // 회원가입이 가능한다면
                        name=jsonResponse.getString("name");
                        sex=jsonResponse.getString("sex");
                        birth=Integer.toString(2022-Integer.parseInt(jsonResponse.getString("birth").substring(0,4)));
                        license=jsonResponse.getString("license");
                        needs=jsonResponse.getString("needs");
                        txtText.setText(name+"("+ birth+", "+sex
                                +")\n자격증:"+license+"\n관심 분야: "+needs+"\n");
                        //Toast.makeText(getApplicationContext(), "프로필 가져오기 성공", Toast.LENGTH_SHORT).show();
                    }else{// 회원가입이 안된다면
                        Toast.makeText(getApplicationContext(), matching_id.substring(2), Toast.LENGTH_SHORT).show();
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
        AlarmPopUpRequest getRequest = new AlarmPopUpRequest(matching_id.substring(2), responseListener);
        RequestQueue queue = Volley.newRequestQueue(AlarmPopUpActivity.this);
        queue.add(getRequest);



        btnAccept.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(AlarmPopUpActivity.this, SelectMatchNotice2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRefuse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }
}