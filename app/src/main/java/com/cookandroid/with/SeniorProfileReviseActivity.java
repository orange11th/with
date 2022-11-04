package com.cookandroid.with;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.cookandroid.with.profile.SeniorProfileActivity;

public class SeniorProfileReviseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_profile_revise);

        //toolbar 연결
        Toolbar toolbar=findViewById(R.id.toolbar_seniorproProfileRevise);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("프로필 수정");
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), SeniorProfileActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}