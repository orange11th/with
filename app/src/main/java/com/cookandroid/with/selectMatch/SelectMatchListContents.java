package com.cookandroid.with.selectMatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cookandroid.with.R;
import com.cookandroid.with.SelectMatchNotice;

public class SelectMatchListContents extends AppCompatActivity {

        private Button confirmbutton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_select_match_list_contents);
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

            //click 신청하기

            confirmbutton = findViewById(R.id.confirmbutton2);
            confirmbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectMatchListContents.this, SelectMatchNotice.class);
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