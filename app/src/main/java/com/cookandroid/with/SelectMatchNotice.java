package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectMatchNotice extends AppCompatActivity {
    private Button SMCButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match_notice);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_selectmatchnotice);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 제거
        getSupportActionBar().setTitle(" ");

        //click confirm button
        SMCButton = findViewById(R.id.SMatchConfirm);
        SMCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectMatchNotice.this, MatchingList.class);
                startActivity(intent);
            }
        });
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