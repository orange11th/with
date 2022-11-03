package com.cookandroid.with.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.cookandroid.with.login.HelperLoginActivity;
import com.cookandroid.with.login.LoginActivity;

import org.json.JSONObject;


public class HelperRegisterActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText edtID,edtPW,edtName,edtEmail,edtPhone,edtRegion,edtAddress,edtBirth,edtLicense;
    private Button btnRegist, searchBtn;
    private RadioGroup rgIE, rgTF;
    private RadioButton rbIE, rbTF;
    private String needs,personality_IE, personality_TF;
    private CheckBox radioButtons[]=new CheckBox[6];
    private Integer [] radioButtonIDs={R.id.radioBtn1, R.id.radioBtn2, R.id.radioBtn3, R.id.radioBtn4, R.id.radioBtn5, R.id.radioBtn6};
    private String [] radioButtonStrings={"병원동행","이동보조","가사돌봄","말동무","취미/돌봄","기타"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_register);

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
        edtLicense=(EditText)findViewById(R.id.edtLicense);
        for(int i=0;i<=5;i++) {
            radioButtons[i]=(CheckBox)findViewById(radioButtonIDs[i]);
        }
        rgIE=(RadioGroup)findViewById(R.id.radioGroupIE);
        rgTF=(RadioGroup)findViewById(R.id.radioGroupTF);
        searchBtn=(Button)findViewById(R.id.searchBtn);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("돌보미 회원가입");

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 절차 시작
                needs="";
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "돌보미 회원가입 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HelperLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "돌보미 회원가입 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                for(int i=0;i<6;i++){
                    if(radioButtons[i].isChecked())
                        needs+=radioButtonStrings[i]+" ";
                }
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
                HelperRegistRequest registRequest = new HelperRegistRequest(edtID.getText().toString(), edtPW.getText().toString(),
                        edtName.getText().toString(),edtEmail.getText().toString(),edtPhone.getText().toString(),
                        edtRegion.getText().toString(),edtAddress.getText().toString(),spinGender.getSelectedItem().toString(),
                        edtBirth.getText().toString(),edtLicense.getText().toString(),needs,personality_IE,personality_TF,responseListener);
                RequestQueue queue = Volley.newRequestQueue(HelperRegisterActivity.this);
                queue.add(registRequest);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HelperRegisterActivity.this, WebViewActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), HelperLoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}