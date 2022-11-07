package com.cookandroid.with.selectMatch;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.with.HelperHomeActivity;
import com.cookandroid.with.MainActivity2;
import com.cookandroid.with.R;

import java.util.ArrayList;

public class SelectMatchList extends AppCompatActivity {

    private String TAG = MainActivity2.class.getSimpleName();
    private ListView listview = null;
    private ListViewAdapter adapter = null;
    private String[] dataList;
    private String data;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match_list);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        Intent intent = getIntent();
        dataList = intent.getStringArrayExtra("dataList");

        btnRefresh=(Button)findViewById(R.id.btnRefresh);

        listview=(ListView) findViewById(R.id.listtest);
        adapter = new ListViewAdapter();


        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_TEST) ;
        //ListView listview = (ListView) findViewById(R.id.listtest) ;
        //listview.setAdapter(adapter) ;

        //listview
        /*
        try {
            Toast.makeText(getApplicationContext(), dataList[0], Toast.LENGTH_SHORT).show();//거슬림
        }catch (Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Toast.makeText(getApplicationContext(), errors.toString(), Toast.LENGTH_SHORT).show();
        }

         */
        if(dataList.length!=0) {
            int i=0;
            while(i<dataList.length-2) {
                adapter.addItem(new TestList(dataList[i++].substring(0,16), "#"+dataList[i++]
                        , dataList[i++], "N."+dataList[i++]));
            }
            //adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "필요한 도움에 대한 내용", "지역"));
            //adapter.addItem(new TestList("6월 20일 병원 동행을 해줄 돌보미를...", "#병원동행", "필요한 도움에 대한 내용", "지역"));
        }
        else{
            Toast.makeText(getApplicationContext(), "현재 신청내역이 없습니다", Toast.LENGTH_SHORT).show();//거슬림
        }
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
        btnRefresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SelectMatchList.this, SelectLoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //List View Adapter
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
                    Intent intent = new Intent(SelectMatchList.this, PopUpActivity.class);
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

    //toolbar Click
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