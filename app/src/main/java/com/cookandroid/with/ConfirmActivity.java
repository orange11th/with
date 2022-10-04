package com.cookandroid.with;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmActivity extends Activity{
    private Button modify_btn, confirm_btn, btn1_change, btn2_change, btn3_change, btn4_change;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);


        //선착순, 지원에 따라 완료 화면 다르게 뜨도록 수정 필요
        confirm_btn = (Button) findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Complete1Activity.class);
                startActivity(intent);
            }
        });

        btn1_change = (Button) findViewById(R.id.change_btn1);

    }
}