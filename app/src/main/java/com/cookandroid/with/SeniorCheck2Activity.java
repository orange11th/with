package com.cookandroid.with;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*written by 병훈*/

public class SeniorCheck2Activity extends AppCompatActivity {


    RecyclerView mRecyclerView = null;  //리사이클러 뷰
    SeniorRecyclerviewAdapter mAdapter = null;//어댑터
    ArrayList<SeniorRecyclerviewItem> mList;  //이거 역할이 뭐지?

    private Drawable mImageDrawable; // 이게 왜 필요한거지?
    private String mMainText; //
    private String mSubText; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_check2);

        //toolbar
        /*
        Toolbar toolbar = findViewById(R.id.toolbar_senior2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        */

        mRecyclerView = findViewById(R.id.rv);//리사이클러뷰 연결
        mList = new ArrayList<>();// 데이터 생성, 초기값 null

        mAdapter = new SeniorRecyclerviewAdapter(mList);//어댑터와 데이터를 연결
        mRecyclerView.setAdapter(mAdapter);//어댑터와 리사이클러뷰를 연결하기

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));//리사이클러뷰의 방향 정하기

        mImageDrawable = ResourcesCompat.getDrawable(getResources(),R.drawable.profile, null);//
        mMainText = "홍길동";
        mSubText = "자세히 보기 >";

        //배열에 데이터 추가


        addItem(mImageDrawable, "홍길동1", "자세히 보기1");
        addItem(mImageDrawable, mMainText+ " - 2", mSubText);
        addItem(mImageDrawable, mMainText+ " - 3", mSubText);
        addItem(mImageDrawable, mMainText+ " - 4", mSubText);
        addItem(mImageDrawable, mMainText+ " - 5", mSubText);

        mAdapter.notifyDataSetChanged(); //추가된 리스트 파악후, 아이템을 표시한다.


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


    private void addItem(Drawable icon, String mainText, String subText){
        SeniorRecyclerviewItem item = new SeniorRecyclerviewItem();

        item.setIv_profile(icon);
        item.setTv_name(mainText);
        item.setTv_content(subText);

        mList.add(item);
    }
}


