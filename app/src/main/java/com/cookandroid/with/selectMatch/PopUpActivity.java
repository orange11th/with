package com.cookandroid.with.selectMatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cookandroid.with.R;
import com.cookandroid.with.SelectMatchNotice;

public class PopUpActivity extends AppCompatActivity {
    private Button btnAccept,btnRefuse;
    private TextView txtText;
    private String title,tag,region,contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up);
        btnAccept=(Button)findViewById(R.id.btnAccept);
        btnRefuse=(Button)findViewById(R.id.btnRefuse);
        txtText=(TextView)findViewById(R.id.txtText);

        Intent intent=getIntent();
        title= intent.getStringExtra("title");//StartTime
        tag= intent.getStringExtra("tag");//Needs
        region= intent.getStringExtra("region");//Matching_id DB입력할때 쓸예정
        contents= intent.getStringExtra("contents");//StartDes

        txtText.setText(tag+"\n\n시작 일시 : "+title+"\n\n출발지 주소\n[ "+contents+" ]");


        btnAccept.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(PopUpActivity.this, SelectMatchNotice.class);
                startActivity(intent);
                finish();
            }
        });
        btnRefuse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }
}