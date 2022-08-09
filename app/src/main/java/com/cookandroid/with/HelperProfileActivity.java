package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HelperProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        /* ProfileActivity에서 필요한 기능 정리를 해보자
         * 이 액티비티에는 3개의 버튼이 있다.
         * 1. 수정 버튼
         * 2. 설정 버튼
         * 3. 뒤로가기 버튼
         * 차례대로 구현해보겠습니다.*/

        /*1. <수정 버튼> 클릭시 액티비티 전환하는 코드입니다.*/
        //btn_revise를 id로 가지는 ImageView를 변수 image1에 넣는다.
        ImageView image1 = findViewById(R.id.btn_revise);

        //image1이 눌렸을 때
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 사용해서 프로필 수정 화면으로 전환한다.
                Intent intent = new Intent(getApplicationContext(), HelperProfileReviseActivity.class);
                startActivity(intent);
            }
        });

        /*2. <설정 버튼> 클릭시 액티비티 전환하는 코드입니다.*/
        //btn_setting를 id로 가지는 ImageView를 변수 image2에 넣는다.
        ImageView image2 = findViewById(R.id.btn_setting);

        //image2이 눌렸을 때
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 사용해서 프로필 수정 화면으로 전환한다.
                Intent intent = new Intent(getApplicationContext(), HelperPrivacySettingActivity.class);
                startActivity(intent);
            }
        });

        /*3. <뒤로가기 버튼> 클릭시 액티비티 전환하는 코드입니다.*/
        ImageView image3 = findViewById(R.id.btn_back);

        //image3이 눌렸을 때
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 사용해서 프로필 수정 화면으로 전환한다.
                Intent intent = new Intent(getApplicationContext(), HelperPrivacySettingActivity.class);//홈 액티비티로 바꿔줘야합니다.
                startActivity(intent);
            }
        });

    }
}