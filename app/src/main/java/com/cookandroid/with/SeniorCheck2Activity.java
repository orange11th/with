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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*written by 병훈*/

public class SeniorCheck2Activity extends AppCompatActivity {

    ArrayList<HelperData> arrHelper; //돌보미 선택 기능에 필요

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

        //MyDessert 클래스 형태의 데이터 준비
        arrHelper = new ArrayList<>();

//        for(int i=0;i<10;i++){
//            MyDessert mydessert1 = new MyDessert(R.drawable.profile,"홍길동"+i,"자세히 보기"+i);
//            arDessert.add(mydessert1);
//        }

        //데이터 객체
        HelperData mydata;

        //배열에 데이터 추가
        mydata = new HelperData(R.drawable.profile,"홍길동1","자세히 보기1");
        arrHelper.add(mydata);
        mydata = new HelperData(R.drawable.profile,"홍길동2","자세히 보기2");
        arrHelper.add(mydata);
        mydata = new HelperData(R.drawable.profile,"홍길동3","자세히 보기3");
        arrHelper.add(mydata);

        //어댑터 생성 - arrHelper 배열과 연결
        HelperAdapter adapter = new HelperAdapter(arrHelper);

        //리사이클러뷰 생성
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        //리사이클러뷰와 어댑터 연결
        recyclerView.setAdapter(adapter);



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
}


//돌보미 지원자 데이터 클래스 - 리스트 뷰에 출력할 항목 리스트
class HelperData{
    int profile;
    String name;
    String detail;

    //생성자
    HelperData(int profile, String name,String detail){
        this.profile = profile;
        this.name = name;
        this.detail = detail;
    }
    //setter getter

    public int getIv_profile() {
        return profile;
    }

    public void setIv_profile(int profile) {
        this.profile = profile;
    }

    public String getTv_name() {
        return name;
    }

    public void setTv_name(String name) {
        this.name = name;
    }

    public String getTv_detail() {
        return detail;
    }

    public void setTv_content(String detail) {
        this.detail = detail;
    }

}

//어댑터 클래스

class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.CustomViewHolder> {

    //배열 선언
    private ArrayList<HelperData> arrayList;

    //ArrayList를 파라미터로 가진 생성자로 만들어준다.
    public HelperAdapter(ArrayList<HelperData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HelperAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //이부분 이해가 안됌 - LayoutInflater가 데이터를 넣는 틀(레이아웃)을 선언하는 건가?(예상)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_senior_item,parent,false);

        //그 틀(레이아웃)을 홀딩하나봐
        CustomViewHolder holder = new CustomViewHolder(view);//이게 커스텀 어댑터라서 그런가보다.

        //holder 리턴
        return holder;
    }

    //실제 추가될 때에 대한 생명주기
    @Override
    public void onBindViewHolder(@NonNull HelperAdapter.CustomViewHolder holder, int position) {
        //다음과 같이 적어준다.
        //그 틀(레이아웃)에 이미지를 가져와서 설정한다.
        holder.profile.setImageResource(arrayList.get(position).getIv_profile()); //arrayList로부터 position의 위치에서 getIv_profile을 갖고 와라
        //그 틀(레이아웃)에 텍스트를 가져와서 설정한다.
        holder.name.setText(arrayList.get(position).getTv_name());
        //그 틀(레이아웃)에 텍스트를 가져와서 설정한다.
        holder.content.setText(arrayList.get(position).getTv_detail());


        //리스트뷰가 클릭됐을 떄, 또는 논클릭이 됐을 떄 구현할 수 있다. - 이건 없어도 됌
        holder.itemView.setTag(position);//포지션을 가져와 준다.

        //리스트뷰의 아이템이 클릭되면 발생하는 클릭이벤트 - 이건 없어도 됌
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //이름을 토스트 메세지로 출력한다.
                String curName = holder.name.getText().toString();
                Toast.makeText(view.getContext(),curName, Toast.LENGTH_SHORT).show();
            }
        });

        //Long 클릭 이벤트를 구현하겠다. - 롱클릭을 하면 삭제하도록 만들겠음
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                //
//                remove(holder.getAbsoluteAdapterPosition());
//
//                //리턴 false -> true 변경
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    //리스트뷰 삭제하는 메소드
    public void remove(int position){
        try{
            //리스트뷰를 지운다.
            arrayList.remove(position);

            //새로고침을 한다.
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        //item_list.xml 에 있는 위젯을 선언한다.
        protected ImageView profile;
        protected TextView name;
        protected TextView content;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            //이 부분이 뭐지?
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.content = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
