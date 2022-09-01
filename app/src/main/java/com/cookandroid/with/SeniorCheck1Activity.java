package com.cookandroid.with;

/*Made by 병훈*/

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
    TextView text_date;//날짜
    TextView text_time;//시간
    TextView text_how;//돌보미 구인 방식

    //도움 종류 기능 구현에 필요
    List<String> mSelectedItems;
    AlertDialog.Builder builder;

    /*도움시간변경버튼 눌렀을 때, 필요한 시간 선언*/
    static int y = 0, m = 0, d = 0, h = 0, mi = 0;

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
        btn_back = (Button) findViewById(R.id.back_btn);
        btn1_change = (Button) findViewById(R.id.change_btn1);
        btn2_change = (Button) findViewById(R.id.change_btn2);
        btn3_change = (Button) findViewById(R.id.change_btn3);
        btn4_change = (Button) findViewById(R.id.change_btn4);
        btn_cancel = (Button) findViewById(R.id.cancel_btn);
        btn_confirm = (Button) findViewById(R.id.confirm_btn);

        //텍스트뷰
        text_address = (TextView) findViewById(R.id.address_text);//도움장소 Text
        text_date = (TextView) findViewById(R.id.date_text);// 도움시간 Text(날짜)
        text_time = (TextView) findViewById(R.id.time_text);// 도움시간 Text(시간)
        text_how = (TextView) findViewById(R.id.how_text);;//돌보미 구인 방식 Text(선착순, 선택 중 택 1)

        /*도움 종류 변경 버튼 기능입니다.
        * 1. 버튼을 누르면 체크박스 다이얼로그를 띄운다.
        * 2. 선택한 데이터를 변수에 넣는다.
        * 3. 선택된 데이터의 버튼의 텍스트를 변경한다. */
        btn1_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();

            }
        });

        /*btn2_change 기능 구현 - 도움 장소 변경하기 버튼 기능입니다.*/
        btn2_change.setOnClickListener(new Button.OnClickListener() {

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

        /*btn3_change 기능 구현 - 도움 시간(날짜랑 시간) 바꾸는 기능*/
        btn3_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //뷰는 스택 형태이기 때문에 showTime()을 먼저 호출한다.
                showTime();
                showDate();
            }
        });

        /*btn4_change 기능 구현 - 변경하기 버튼을 누르면, change4_OnClick 메소드를 실행시켜서
        돌보미 구인 방법 중 선착순인지 선택인지 결정한다.*/
        btn4_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });

    }

    /*메소드 - 도움 종류 변경하는 기능 - 노션에 정리해두었습니다.
    * 1. 체크 박스 다이얼로그 띄우기로 구현했습니다.*/
    void showDialog1(){
        //리스트를 만들고
        mSelectedItems = new ArrayList<>();
        builder = new AlertDialog.Builder(SeniorCheck1Activity.this);
        builder.setTitle("도움 종류를 선택해주세요.");

        //string.xml에 만들어둔 kinOfHelp를 가져와서 체크박스로 만듭니다.
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
                //문제 : 체크박스에서 선택을 했을 때, 어떻게 보여줄 것인가?..
                String final_selection1 ="";
                String final_selection2 ="";

                for(String item : mSelectedItems){
                    final_selection1 = item;
                    final_selection2 = item;
                }
                //여기서 잠시 멈춤 : 확인 버튼을 누르면 값을 어떻게 보여줄지 결정해야함.
                TextView text1 = findViewById(R.id.type1);
                TextView text2 = findViewById(R.id.type2);

                text1.setText(final_selection1);
                text2.setText(final_selection2);

                Toast.makeText(getApplicationContext(),"선택된 아이템은"+final_selection1,Toast.LENGTH_SHORT).show();
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


    /*날짜 보여주는 메소드*/
    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOdMonth) {
                y = year;
                m = month + 1;
                d = dayOdMonth;
            }
        }, 2022, 8, 22);//다이얼로그가 켜졌을 때 첫 세팅된 날짜를 의미한다.
        ////업그레이드 : 오늘 날짜로 자동으로 변경하는 법 찾아보기

        datePickerDialog.setMessage("날짜를 선택하세요");//다이얼로그 제목


        datePickerDialog.show();//다이얼로그 띄우기

        //이제 입력값에 따라 text를 바꿔주자
        text_date.setText(y + "년 " + m + "월 " + d + "일");


    }

    /*시간을 보여주는 메소드*/
    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                mi = minute;
            }
        }, 18, 30, true); //다이얼로그가 켜졌을 때, 첫 세팅된 시간을 의미한다.
        timePickerDialog.setMessage("시간을 선택하세요.");

        timePickerDialog.show();//다이얼로그 띄우기

        //이제 입력값에 따라 text를 바꿔주자
        text_time.setText(h + "시 " + mi + "분");
        //문제 발생 : 다이얼로그에서 값을 입력하고 창을 닫으면, settext가 되어야하는데, 한발 늦게 되고 있다.
        //해결법 : 리스너를 공부할 것

    }

    /*메소드 - 돌보미 도움 종류 변경 버튼을 눌렀을 때 기능할 것들
     * 1. 다이얼로그(목록) - 도움 종류 중 고르기
     * 2. 고른 값을 버튼으로 만들어주기*/




    /*메소드 - 돌보미 구인 방법 변경 버튼을 눌렀을 때 기능할 것들
     * 1. 다이얼로그(목록) - 선착순, 선택 중 고르기
     * 2. 고른 값을 text로 변경해주기*/

    void showDialog4() {
        //블로그의 코드를 가져와서 만들었습니다.(출처 노션에 남겨둠) -> setText 부분만 수정해서 적용했습니다.
        final CharSequence[] oltems = {"선착순", "선택"}; //배열에 값을 넣어준다.
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