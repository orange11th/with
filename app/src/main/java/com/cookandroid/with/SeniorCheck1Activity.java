package com.cookandroid.with;

/*Made by 병훈*/

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SeniorCheck1Activity extends AppCompatActivity {

    /*뷰 선언*/
    Button btn_back; //뒤로가기
    Button btn1_change; //변경하기 1
    Button btn2_change; //변경하기 2
    Button btn3_change; //변경하기 3
    Button btn4_change; //변경하기 4

    Button btn_cancel; //취소하기
    Button btn_confirm; //확인

    TextView text_address;//주소

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Toolbar toolbar = findViewById(R.id.toolbar_main3);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(" ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_check1);


        /*id 매핑*/
        //버튼
        btn_back = (Button)findViewById(R.id.back_btn);
        btn1_change = (Button)findViewById(R.id.change_btn1);
        btn2_change = (Button)findViewById(R.id.change_btn2);
        btn3_change = (Button)findViewById(R.id.change_btn3);
        btn4_change = (Button)findViewById(R.id.change_btn4);
        btn_cancel = (Button)findViewById(R.id.cancel_btn);
        btn_confirm = (Button)findViewById(R.id.confirm_btn);

        //텍스트뷰
        text_address = (TextView)findViewById(R.id.address_text);


        /*btn2_change 기능 구현*/
        btn2_change.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                //클릭 이벤트
                //다이얼로그로 EditText를 띄우고, 넣은 값을 textview로 만든다.
                final EditText editText = new EditText(SeniorCheck1Activity.this);

                AlertDialog.Builder dig = new AlertDialog.Builder(SeniorCheck1Activity.this);
                dig.setTitle("장소가 어디인가요?");
                dig.setView(editText);
                dig.setIcon(R.drawable.ic_launcher_background);
                dig.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //입력 버튼이 눌리면, 텍스트를 바꿔주자.
                        text_address.setText(editText.getText().toString());
                        //Toast.makeText(getApplicationContext(), editText.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                dig.show();
            }
        });
    }

//    //toolbar
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId()){
//            case android.R.id.home:{
//                //Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                //startActivity(intent);
//                finish();
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

}