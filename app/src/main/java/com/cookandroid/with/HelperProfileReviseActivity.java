package com.cookandroid.with;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelperProfileReviseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile_revise);

        /*ProfileReviseActivity에 필요한 기능 정리해보자
         * 1. 프로필 사진 변경
         * 2. 저장 버튼 기능
         * 3. 뒤로가기 버튼 기능
         * 4. EditText 입력값을 저장 버튼 눌렀을 때 TextView로 바꿔주는 기능
         * 5. 자격증 추가 버튼을 누르면 레이아웃이 추가되는 기능
         * 6. 도움 가능 종류를 고르는 기능
         * */

        /*1. 프로필 사진 변경 기능*/
        TextView btn1 = findViewById(R.id.btn_profile_revise);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
    }
}