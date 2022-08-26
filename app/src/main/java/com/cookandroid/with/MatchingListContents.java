package com.cookandroid.with;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MatchingListContents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_list_contents);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        //click 확인
        Button okaybutton = (Button) findViewById(R.id.okaybutton);
        okaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MatchingList.class);
                startActivity(intent);
            }
        });

        //click 취소
        Button cancelbutton = (Button) findViewById(R.id.cancelbutton);
        cancelbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                show();
            }
        });

    }
    //취소 버튼 팝업
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("약속 취소"); //title
        builder.setMessage("약속은 도움 예정 날짜 3일 전 까지만 취소할 수 있습니다. 정말 취소하시겠습니까?"); //message
        builder.setPositiveButton("예, 취소합니다.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"약속을 취소하였습니다.", Toast.LENGTH_LONG).show();
                    }
                }); //우측 버튼(positive)
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }); //좌측 버튼(negative)
        builder.show();
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