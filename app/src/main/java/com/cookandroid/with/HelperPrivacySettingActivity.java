package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/*written by 병훈*/

/*HelperPrivacySettingActivity 에서 필요한 기능을 정리해보자
 * 1. 저장 버튼
 * 2. 비밀번호 변경 버튼 기능
 * 3. 전화번호 변경 버튼 기능
 * 4. 이메일 변경 버튼 기능
 * 5. 뒤로가기 버튼 기능*/



public class HelperPrivacySettingActivity extends AppCompatActivity implements View.OnClickListener{

    /*비밀번호 변경 버튼 구현*/
    private EditText inputEditText1;
    private TextView textView1;
    private TextView btn1;


    /*전화번호 변경 버튼 구현*/
    private EditText inputEditText2;
    private TextView textView2;
    private TextView btn2;

    /*이메일 변경 버튼 구현*/
    private EditText inputEditText3;
    private TextView textView3;
    private TextView btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_privacy_setting);

        /*비밀번호 변경 버튼 구현*/
        inputEditText1 = findViewById(R.id.edittext_input1);
        inputEditText1.setOnClickListener(this); // 여기서 this가 onClick 메서드를 말하는 것 같다.

        textView1 = findViewById(R.id.textview_id1);
        textView1.setOnClickListener(this);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        /*전화번호 변경 버튼 구현*/
        inputEditText2 = findViewById(R.id.edittext_input2);
        inputEditText2.setOnClickListener(this::onClick1); // 여기서 this가 onClick 메서드를 말하는 것 같다.

        textView2 = findViewById(R.id.textview_id2);
        textView2.setOnClickListener(this::onClick1);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this::onClick1);

        /*이메일 변경 버튼 구현*/
        inputEditText3 = findViewById(R.id.edittext_input3);
        inputEditText3.setOnClickListener(this::onClick2); // 여기서 this가 onClick 메서드를 말하는 것 같다.

        textView3 = findViewById(R.id.textview_id3);
        textView3.setOnClickListener(this::onClick2);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this::onClick2);


        /*뒤로가기 버튼 구현하기*/
        ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HelperProfileActivity.class);
                startActivity(intent);//액티비티 띄우기
            }
        });



    }
    /*비밀번호 변경 버튼 눌렀을 때*/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edittext_input1:
                textView1.setVisibility(View.GONE);
                inputEditText1.setVisibility(View.VISIBLE);
                break;

            case R.id.textview_id1:
                String txt = textView1.getText().toString().isEmpty()?"":textView1.getText().toString();
                textView1.setVisibility(View.GONE);
                inputEditText1.setVisibility(View.VISIBLE);
                inputEditText1.setText(txt);
                break;

            case R.id.btn1:
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputEditText1.getWindowToken(),0);
                String str = inputEditText1.getText().toString().isEmpty()?"":inputEditText1.getText().toString();
                textView1.setVisibility(View.VISIBLE);
                textView1.setText(str);
                inputEditText1.setVisibility(View.GONE);
                break;
        }
    }
    /*전화번호 변경 버튼 눌렀을 때*/
    public void onClick1(View view) {
        switch (view.getId()){
            case R.id.textview_id2:
                String txt = textView2.getText().toString().isEmpty()?"":textView2.getText().toString();
                textView2.setVisibility(View.GONE);
                inputEditText2.setVisibility(View.VISIBLE);
                inputEditText2.setText(txt);
                break;

            case R.id.edittext_input2:
                textView2.setVisibility(View.GONE);
                inputEditText2.setVisibility(View.VISIBLE);
                break;

            case R.id.btn2:
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputEditText2.getWindowToken(),0);
                String str = inputEditText2.getText().toString().isEmpty()?"":inputEditText2.getText().toString();
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(str);
                inputEditText2.setVisibility(View.GONE);
                break;
        }
    }
    /*이메일 변경 버튼 눌렀을 때*/
    public void onClick2(View view) {
        switch (view.getId()){
            case R.id.textview_id3:
                String txt = textView3.getText().toString().isEmpty()?"":textView3.getText().toString();
                textView3.setVisibility(View.GONE);
                inputEditText3.setVisibility(View.VISIBLE);
                inputEditText3.setText(txt);
                break;

            case R.id.edittext_input3:
                textView3.setVisibility(View.GONE);
                inputEditText3.setVisibility(View.VISIBLE);
                break;

            case R.id.btn3:
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputEditText3.getWindowToken(),0);
                String str = inputEditText3.getText().toString().isEmpty()?"":inputEditText3.getText().toString();
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(str);
                inputEditText3.setVisibility(View.GONE);
                break;
        }
    }
}