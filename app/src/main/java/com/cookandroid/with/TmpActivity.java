package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.with.simple.SimpleActivity;

public class TmpActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);
        btn1=(Button)findViewById(R.id.btnSelect1);
        btn2=(Button)findViewById(R.id.btnSelect2);
        btn3=(Button)findViewById(R.id.btnSelect3);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Intent intent = new Intent(getApplicationContext(), HelperHomeActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Intent intent = new Intent(getApplicationContext(), SimpleActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Intent intent = new Intent(getApplicationContext(),SeniorCheck2Activity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}