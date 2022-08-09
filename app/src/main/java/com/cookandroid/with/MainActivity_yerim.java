package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity_yerim extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yerim);

        //버튼 글자 크기 다르게
        ArrayList<Button> btnLIst=new ArrayList<Button>();

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn1.setSelected(true);
            }
        });

        Spannable span1 = (Spannable) btn1.getText();
        span1.setSpan(new AbsoluteSizeSpan(43), 6,33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn2.setSelected(true);
            }
        });

        Spannable span2 = (Spannable) btn2.getText();
        span2.setSpan(new AbsoluteSizeSpan(43), 6,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn3.setSelected(true);
            }
        });

        Spannable span3 = (Spannable) btn3.getText();
        span3.setSpan(new AbsoluteSizeSpan(44), 6,46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn4.setSelected(true);
            }
        });

        Spannable span4 = (Spannable) btn4.getText();
        span4.setSpan(new AbsoluteSizeSpan(44), 3,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //main 등록 버튼 activity
        Button enroll_btn = (Button) findViewById(R.id.enroll_btn);
        enroll_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        });

    }


}