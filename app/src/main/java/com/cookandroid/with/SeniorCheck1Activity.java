package com.cookandroid.with;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/*written by 병훈*/

public class SeniorCheck1Activity extends AppCompatActivity {

    //변경하기 버튼
    Button btn_back; //뒤로가기
    Button btn_change_needs; //도움 종류 변경 버튼
    Button btn_change_address; //도움 장소 변경 버튼
    Button btn_change_time; //시간 변경 버튼
    Button btn_change_how_to_get_helper; //돌보미 구인 방법 변경 버튼

    //확인, 취소 버튼
    Button btn_cancel; //취소하기
    Button btn_confirm; //확인

    //텍스트뷰
    TextView text_address;//주소
    TextView text_date;//날짜
    TextView text_time;//시간
    TextView text_how;//돌보미 구인 방식

    /*도움시간변경버튼 눌렀을 때, 필요한 시간 선언*/
    static int y = 0, m = 0, d = 0, h = 0, mi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_check1);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        //변경버튼 연결
        btn_change_needs = (Button) findViewById(R.id.change_btn1);
        btn_change_address = (Button) findViewById(R.id.change_btn2);
        btn_change_time = (Button) findViewById(R.id.change_btn3);
        btn_change_how_to_get_helper = (Button) findViewById(R.id.change_btn4);

        //확인, 취소 버튼 연결
        btn_cancel = (Button) findViewById(R.id.cancel_btn);
        btn_confirm = (Button) findViewById(R.id.confirm_btn);

        //텍스트뷰 연결
        text_address = (TextView) findViewById(R.id.address_text);//도움장소 Text
        text_date = (TextView) findViewById(R.id.date_text);// 도움시간 Text(날짜)
        text_time = (TextView) findViewById(R.id.time_text);// 도움시간 Text(시간)
        text_how = (TextView) findViewById(R.id.how_text);;//돌보미 구인 방식 Text(선착순, 선택 중 택 1)

        //버튼 클릭 이벤트 - 도움 종류 변경 버튼
        btn_change_needs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //도움 종류 변경 메소드 호출
                change_needs();

            }
        });

        //버튼 클릭 이벤트 - 도움 장소 변경 버튼
        btn_change_address.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                //도움 장소 변경 메소드 실행
                change_address();
            }
        });

        //버튼 클릭 이벤트 - 도움 시간 변경 버튼
        btn_change_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //뷰는 스택 형태이기 때문에 showTime()을 먼저 호출한다.
                //시간 변경 메소드 호출
                change_time();
                change_date();
            }
        });


        //버튼 클릭 이벤트 - 돌보미 구인 방법 변경 버튼
        btn_change_how_to_get_helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //돌보미 구인 방법 변경 메소드 호출
                change_how_to_get_helper();
            }
        });

        //버튼 클릭 이벤트 - 확인 버튼
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //저장해야함
            }
        });

        //버튼 클릭 이벤트 - 취소 버튼
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이전 화면으로 돌아가기
            }
        });


    }

   //메소드 - 도움 종류 변경 - 체크박스 다이얼로그
    void change_needs(){

        //리스트를 만든다.
        List<String> mSelectedItems = new ArrayList<>();

        //빌더 객체 생성
        AlertDialog.Builder  builder = new AlertDialog.Builder(SeniorCheck1Activity.this);

        builder.setTitle("도움 종류를 선택해주세요.");

        //string.xml에 만들어둔 kindOfHelp를 가져와서 체크박스로 만듭니다.
        builder.setMultiChoiceItems(R.array.kindOfHelp, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                //눌리면 데이터를 리스트에 담습니다.
                String[]items = getResources().getStringArray(R.array.kindOfHelp);

                if(isChecked){
                    mSelectedItems.add(items[which]);
                }else if(mSelectedItems.contains(items[which])){
                    mSelectedItems.remove(items[which]);
                }
            }
        });

        //확인을 누르면, 리스트에 있는 데이터가 버튼으로 만들어지게 만듭니다.
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

////////////////장우성 교수님께 여쭤본 부분/////////////////////////////////

                //리니어레이아웃을 가져와서 textview를 추가하는 방법을 상용한다.
                LinearLayout ll = findViewById(R.id.linearlayout1);
                ll.removeAllViews();


                for(String item : mSelectedItems){
                    //이 부분이 문제였음 - 리스트 mSelectedItems 에서 값을 하나씩 꺼내와야하는데, 같은 값을 변수에 넣었으니 그렇게 된 것임

                    //텍스트뷰를 2개로 고정하지 말자
//                    리스트의 사이즈만큼 textview를 생성해야한다. 따라서
                    TextView tv = new TextView(getApplicationContext());//이부분하기 - textview를 어떻게 생성하지?

                    tv.setText(item);// 배열값을 가져와서 텍스트로 보이게하기
                    tv.setTextSize(20);

                    //레이아웃 설정
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    param.rightMargin = 625;
                    tv.setLayoutParams(param);

                    //텍스트뷰 백그라운드 색상 설정하기기
                    tv.setBackground(getResources().getDrawable(R.drawable.confirm_btn_background));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextColor(Color.rgb(34,34,34));

                   //생성및 설정된 텍스트뷰 레이아웃에 적용하기
                    ll.addView(tv);
                }
////////////////////////여기까지/////////////////////////////////

                //여기서 잠시 멈춤 : 확인 버튼을 누르면 값을 어떻게 보여줄지 결정해야함.
                TextView text1 = findViewById(R.id.type1);
            }
        });
        //취소 이벤트
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //도움 장소 변경 메소드
    void change_address(){
        //다이얼로그로 EditText를 띄우고, 넣은 값을 textview로 만든다.
        final EditText editText = new EditText(SeniorCheck1Activity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(SeniorCheck1Activity.this);
        builder.setTitle("장소가 어디인가요?");
        builder.setView(editText);
        builder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //입력 버튼이 눌리면, 텍스트를 바꿔주자.
                text_address.setText(editText.getText().toString());
            }
        });
        builder.show();
    }

    /*날짜 보여주는 메소드*/
    void change_date() {

        //선택 가능한 날짜의 최소값을 저장하는 객체입니다.
        Calendar minDate = Calendar.getInstance();

        ///여기까지

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOdMonth) {
                y = year;
                m = month + 1;
                d = dayOdMonth;
                //이제 입력값에 따라 text를 바꿔주자

                //이전날짜 선택불가하게 만들기

                text_date.setText(y + "년 " + m + "월 " + d + "일");

            }
        }, 2022, 8, 22);//다이얼로그가 켜졌을 때 첫 세팅된 날짜를 의미한다.
        ////업그레이드 : 오늘 날짜로 자동으로 변경하는 법 찾아보기

        datePickerDialog.setMessage("날짜를 선택하세요");//다이얼로그 제목

//        minDate.set(2022,9-1,19);
        //오늘 날짜 이전 날짜는 선택 불가능하게 만드는 코드입니다.
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

        datePickerDialog.show();//다이얼로그 띄우기
    }

    /*시간을 보여주는 메소드*/
    void change_time() {
        Calendar cal = Calendar.getInstance();
        int HOUR = cal.get(Calendar.HOUR); //년
        int MINUTE = cal.get(Calendar.MINUTE);//월

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String time = hourOfDay + "시 " + minute + "분";

                text_time.setText(time);

            }
        }, HOUR, MINUTE, true); //현재 시간으로 변경하기
        timePickerDialog.setMessage("시간을 선택하세요.");

        timePickerDialog.show();//다이얼로그 띄우기

    }


    void change_how_to_get_helper() {
        //블로그의 코드를 가져와서 만들었습니다.(출처 노션에 남겨둠) -> setText 부분만 수정해서 적용했습니다.
        final CharSequence[] oltems = {"빠르게 돌보미 구하기", "지원한 돌보미 중 선택하기"}; //배열에 값을 넣어준다.

        //목록 다이얼로그 객체를 생성하고
        AlertDialog.Builder oDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        oDialog.setTitle("돌봄 방식을 선택해주세요.").setItems(oltems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //oltems[which]가 목록 중 선택한 값이 됩니다.
                //선택한 값으로 텍스트로 바꿔줍니다.
                text_how.setText(oltems[which]);
                //변경되었다는 Toast 메세지를 띄운다.
                Toast.makeText(getApplicationContext(), "변경되었습니다.", Toast.LENGTH_LONG).show();
            }
        }).setCancelable(false).show();//setCancelable 메소드 - 백버튼을 사용하지 못하게 만든다.
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