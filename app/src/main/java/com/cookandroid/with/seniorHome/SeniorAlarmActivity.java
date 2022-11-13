package com.cookandroid.with.seniorHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.with.R;
import com.cookandroid.with.selectMatch.PopUpActivity;
import com.cookandroid.with.selectMatch.TestList;

import java.util.ArrayList;

public class SeniorAlarmActivity extends AppCompatActivity {
    private ListView listview = null;
    private ListViewAdapter adapter = null;
    private String[] dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_alarm);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_alarm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" 알림");

        Intent intent = getIntent();
        dataList = intent.getStringArrayExtra("dataList");

        listview=(ListView) findViewById(R.id.listtest);
        adapter=new ListViewAdapter();



        if(dataList.length!=0) {
            int i=0;
            while(i<dataList.length-2) {
                adapter.addItem(new TestList(dataList[i++]+"님이 매칭을 신청하셨습니다.", "#"+dataList[i++]
                        , "여길 눌러 돌보미 정보를 확인하세요.", "N."+dataList[i++]));
                i++;
            }
            //adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "필요한 도움에 대한 내용", "지역"));
            //adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "필요한 도움에 대한 내용", "지역"));
        }
        else{
            Toast.makeText(getApplicationContext(), "현재 신청내역이 없습니다", Toast.LENGTH_SHORT).show();//거슬림
        }

        listview.setAdapter(adapter);
    }
    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class ListViewAdapter extends BaseAdapter {
        ArrayList<TestList> items = new ArrayList<TestList>();

        @Override
        public int getCount(){
            return items.size();
        }
        public void addItem (TestList item){
            items.add(item);
        }
        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //@Override
        //public long getItemID(int position){
        //    return position;
        // }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup){
            final Context context = viewGroup.getContext();
            final TestList testList = items.get(position);
            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_layout, viewGroup, false);
            }else {
                View view = new View(context);
                view=(View) convertView;
            }
            TextView TV_title = (TextView) convertView.findViewById(R.id.TV_title);
            TextView TV_contents = (TextView) convertView.findViewById(R.id.TV_contents);
            TextView TV_tag = (TextView) convertView.findViewById(R.id.TV_tag);
            TextView TV_region = (TextView) convertView.findViewById(R.id.TV_region);

            TV_title.setText(testList.getTitle());
            TV_contents.setText(testList.getContents());
            TV_region.setText(testList.getRegion());
            TV_tag.setText(testList.getTag());



            //listview = findViewById(R.id.listtest);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, testList.getTitle() + "제목", Toast.LENGTH_LONG).show( );
                    Intent intent = new Intent(SeniorAlarmActivity.this, AlarmPopUpActivity.class);
                    intent.putExtra("title", testList.getTitle());
                    intent.putExtra("tag", testList.getTag());
                    intent.putExtra("region", testList.getRegion());
                    intent.putExtra("contents", testList.getContents());
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }

}