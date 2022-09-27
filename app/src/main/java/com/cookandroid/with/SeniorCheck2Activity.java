package com.cookandroid.with;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

/*written by 병훈*/

public class SeniorCheck2Activity extends AppCompatActivity {

    ArrayList<MyDessert> arDessert; //돌보미 선택 기능에 필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_check2);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_senior2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        //MyDessert 클래스 형태의 데이터 준비
        arDessert = new ArrayList<>();

//        for(int i=0;i<10;i++){
//            MyDessert mydessert1 = new MyDessert(R.drawable.profile,"홍길동"+i,"자세히 보기"+i);
//            arDessert.add(mydessert1);
//        }

        MyDessert mydessert;

        mydessert = new MyDessert(R.drawable.profile,"홍길동1","자세히 보기1");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.profile,"홍길동2","자세히 보기2");
        arDessert.add(mydessert);
        mydessert = new MyDessert(R.drawable.profile,"홍길동3","자세히 보기3");
        arDessert.add(mydessert);

        MyDessertAdapter adapter = new MyDessertAdapter(this,R.layout.listview_senior_item,arDessert);

        ListView list;
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);



//        /*자세히 보기 버튼 기능입니다.
//        자세히 보기 버튼을 누르면 돌보미의 프로필로 화면 전환합니다.*/
//        TextView readMore = (TextView) findViewById(R.id.readMore);
//        readMore.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), HelperProfileActivity.class);
//                startActivity(intent);
//            }
//        });

        /*확인 버튼 기능입니다.
        * 버튼을 누르면 선택한 돌보미의 데이터와 함께 화면을 전환압니다.
        * 생각해볼 것 : 어느 화면으로 전환해야하지?*/


        /*돌보미 선택 버튼 기능입니다.
        * 버튼을 누르면 해당 돌보미가 선택되도록 합니다.*/
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

//돌보미 선택 기능 구현
//리스트 뷰에 출력할 항목 리스트
class MyDessert{
    int Icon;
    String Name;
    String Detail;

    MyDessert(int aIcon, String aName,String adetail){
        Icon = aIcon;
        Name = aName;
        Detail = adetail;
    }
}

//어댑터 클래스
class MyDessertAdapter extends BaseAdapter {

    Context con;
    LayoutInflater inflacter;
    ArrayList<MyDessert> arD;
    int layout;

    public MyDessertAdapter(Context context, int alayout, ArrayList<MyDessert> aarD){
        con = context;
        inflacter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arD = aarD;
        layout = alayout;

//        Log.d("woosung1", "MyDessertAdapter:"+arD.get(1).Name);
    }

    //어댑터에 몇 개의 항목이 있는지 조사
    @Override
    public int getCount() {
        Log.d("woosung1", "getCount:"+arD.size());
        return arD.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("woosung1", "getItem:"+arD.get(position).Name);
        return arD.get(position).Name;
    }
    //position 위치의 항목 ID반환
    @Override
    public long getItemId(int position) {
        Log.d("woosung1", "getItemId:"+position);
        return position;
    }

    //각 항목의 뷰 생성 후 반환한다.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("woosung1", "getView position1: "+position);

        if(convertView == null){
            convertView = inflacter.inflate(layout,parent,false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        img.setImageResource(arD.get(position).Icon);

        TextView txt1 = (TextView) convertView.findViewById(R.id.txt1);
        txt1.setText(arD.get(position).Name);

        TextView txt2 = (TextView) convertView.findViewById(R.id.txt2);
        txt2.setText(arD.get(position).Detail);//test

        Log.d("woosung1", "getView position2: "+position);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                String str = arD.get(position).Name + "를 주문합니다.";
                Toast.makeText(con,str,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}