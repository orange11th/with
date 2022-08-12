package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        //list contents
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.TitleText);
        title.setText(intent.getStringExtra("title"));
        TextView region = (TextView) findViewById(R.id.RegionText);
        region.setText(intent.getStringExtra("region"));
        TextView tag = (TextView) findViewById(R.id.TagText);
        tag.setText(intent.getStringExtra("tag"));
        TextView contents = (TextView) findViewById(R.id.ContentsText);
        contents.setText(intent.getStringExtra("contents"));

        //contents scroll
        contents.setMovementMethod(new ScrollingMovementMethod());
    }
    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                //Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                //startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}