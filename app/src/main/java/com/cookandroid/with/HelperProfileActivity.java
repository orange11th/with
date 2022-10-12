package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/*written by 병훈*/

public class HelperProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_helperProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

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



    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}