package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*written by 병훈*/

/* 필요 기능
* 1. 결제 버튼(완료)
* 2. 수정 버튼
* 3. 설정 버튼*/

public class SeniorProfileActivity extends AppCompatActivity {

    ImageView btn_cash;// 결제 버튼 선언
    ImageView btn_revise; //수정 버튼 선언
    ImageView btn_setting; //세팅 버튼 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_profile);

        btn_cash = (ImageView) findViewById(R.id.btn_cash);
        btn_revise = (ImageView) findViewById(R.id.btn_revise);
        btn_setting = (ImageView) findViewById(R.id.btn_setting);

        //화면전환 - 결제 버튼 누르면 결제내역화면(CashHistoryActivity)으로 이동
        btn_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CashHistoryActivity.class);
                startActivity(intent);
            }
        });

        //화면전환 - 수정 버튼 누르면 시니어 프로필 수정 화면으로 이동
        btn_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeniorProfileReviseActivity.class);
                startActivity(intent);
            }
        });

        //화면전환 - 세팅 버튼 누르면 세팅 화면으로 이동
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeniorProfileSettingActivity.class);//변경 예정
                startActivity(intent);
            }
        });
    }
}