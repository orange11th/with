package com.cookandroid.with.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.R;
import com.cookandroid.with.login.LoginActivity;

import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText edtID,edtPW,edtName,edtEmail,edtPhone,edtRegion,edtAddress,edtBirth;
    private Button btnRegist, searchBtn;
    private RadioGroup rgIE, rgTF;
    private RadioButton rbIE, rbTF;
    private String personality_IE, personality_TF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinGender=findViewById(R.id.spinGender);
        final String[] gender={"남","여"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGender.setAdapter(adapter);


        btnRegist=(Button)findViewById(R.id.btnRegist);
        edtID=(EditText)findViewById(R.id.edtID);
        edtPW=(EditText)findViewById(R.id.edtPW);
        edtName=(EditText)findViewById(R.id.edtName);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPhone=(EditText)findViewById(R.id.edtPhone);
        edtRegion=(EditText)findViewById(R.id.edtRegion);
        edtAddress=(EditText)findViewById(R.id.edtAddress);
        edtBirth=(EditText)findViewById(R.id.edtBirth);
        rgIE=(RadioGroup)findViewById(R.id.radioGroupIE);
        rgIE=(RadioGroup)findViewById(R.id.radioGroupIE);
        rgTF=(RadioGroup)findViewById(R.id.radioGroupTF);
        searchBtn=(Button)findViewById(R.id.searchBtn);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("회원가입");

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 절차 시작
                if(edtID.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "시니어 회원가입 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "시니어 회원가입 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                //스위치버튼 선택
                if(rgIE.getCheckedRadioButtonId()==R.id.rbtn_E)
                    personality_IE="E";
                else
                    personality_IE="I";
                if(rgTF.getCheckedRadioButtonId()==R.id.rbtn_F)
                    personality_TF="F";
                else
                    personality_TF="T";
                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                RegistRequest registRequest = new RegistRequest(edtID.getText().toString(), edtPW.getText().toString(),
                        edtName.getText().toString(),edtEmail.getText().toString(),edtPhone.getText().toString(),
                        edtRegion.getText().toString(),edtAddress.getText().toString(),spinGender.getSelectedItem().toString(),
                        edtBirth.getText().toString(),personality_IE,personality_TF,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registRequest);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(RegisterActivity.this, WebViewActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        edtRegion.setText(data);
                    }
                }
                break;
        }
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