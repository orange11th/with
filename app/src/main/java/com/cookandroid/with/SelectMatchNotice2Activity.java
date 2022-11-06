package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SelectMatchNotice2Activity extends AppCompatActivity {
    private Button SMCButton, detailsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match_notice2);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_selectmatchnotice2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");


        detailsBtn = findViewById(R.id.detailsBtn);
        detailsBtn.setOnClickListener(v->{
            Intent intent = new Intent(SelectMatchNotice2Activity.this, MatchingListContents.class);
            startActivity(intent);
        });

        //click confirm button
        SMCButton = findViewById(R.id.SMatchConfirm);
        SMCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectMatchNotice2Activity.this, HelperHomeActivity.class);
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