package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SeniorHomeActivity extends AppCompatActivity {
    private Button btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_home);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  홈");

        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimpleActivity.class);
                startActivity(intent);
            }
        });

        Spannable span1 = (Spannable) btn2.getText();
        span1.setSpan(new AbsoluteSizeSpan(45), 11,40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
                startActivity(intent);
            }
        });

        Spannable span3 = (Spannable) btn3.getText();
        span3.setSpan(new AbsoluteSizeSpan(45), 13,45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeniorCheck1Activity.class);
                startActivity(intent);
            }
        });

        Spannable span4 = (Spannable) btn4.getText();
        span4.setSpan(new AbsoluteSizeSpan(45), 6,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}