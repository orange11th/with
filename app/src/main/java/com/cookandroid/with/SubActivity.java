package com.cookandroid.with;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //confirm 확인 버튼 activity
        Button confirm_btn = (Button) findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}