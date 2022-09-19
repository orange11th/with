package com.cookandroid.with;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/*written by 병훈*/

public class SeniorCheck2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_check2);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        /*자세히 보기 버튼 기능입니다.
        자세히 보기 버튼을 누르면 돌보미의 프로필로 화면 전환합니다.*/
        TextView readMore = (TextView) findViewById(R.id.readMore);
        readMore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), HelperProfileActivity.class);
                startActivity(intent);
            }
        });

        /*확인 버튼 기능입니다.
        * 버튼을 누르면 선택한 돌보미의 데이터와 함께 화면을 전환압니다.
        * 생각해볼 것 : 어느 화면으로 전환해야하지?*/


        /*돌보미 선택 버튼 기능입니다.
        * 버튼을 누르면 해당 돌보미가 선택되도록 합니다.*/
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{

                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}