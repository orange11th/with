package com.cookandroid.with;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.with.selectMatch.TestList;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private String TAG = MainActivity2.class.getSimpleName();
    private ListView listview = null;
    private ListViewAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_TEST) ;

        //ListView listview = (ListView) findViewById(R.id.listtest) ;
        //listview.setAdapter(adapter) ;

        //listview
        listview=(ListView) findViewById(R.id.listtest);
        adapter = new ListViewAdapter();

        adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "필요한 도움에 대한 내용", "지역"));
        adapter.addItem(new TestList("7월 5일 병원동행", "#산책", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "지역1"));
        adapter.addItem(new TestList("8월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "내용222", "서울"));
        adapter.addItem(new TestList("9월 5일 병원동행", "태그1", "내용3", "대전"));
        adapter.addItem(new TestList("1월 13일 병원 동행을 해줄 돌보미를...", "#다른항목", "내용4", "세종"));
        adapter.addItem(new TestList("12월 8일 병원동행", "#산책", "내용5", "강릉"));
        adapter.addItem(new TestList("2월 21일 병원 동행을 해줄 돌보미를...", "#병원동행", "내용6", "제주"));
        adapter.addItem(new TestList("8월 9일 병원동행", "태그3", "내용7", "부산"));
        adapter.addItem(new TestList("6월 8일 병원 동행을 해줄 돌보미를...", "#병원동행", "내용8", "대구"));
        adapter.addItem(new TestList("7월 5일 병원동행", "#산책", "내용9", "인천"));
        adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "내용10", "지역"));
        adapter.addItem(new TestList("7월 5일 병원동행", "태그5", "내용11", "지역1"));

        listview.setAdapter(adapter);

        //tag
        Button button1 = findViewById(R.id.tag1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "병원동행",Toast.LENGTH_LONG).show();
                String tag = "#병원동행";
            }
        });
        Button button2 = findViewById(R.id.tag2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String tag = "#산책";
            }
        });
        Button button0 = findViewById(R.id.tag0);
        button0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String tag = null;
            }
        });
    }
    //List View Adapter
    public class ListViewAdapter extends BaseAdapter{
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
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
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

    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), HelperHomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}