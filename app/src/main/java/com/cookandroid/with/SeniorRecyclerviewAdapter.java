package com.cookandroid.with;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*written by 병훈*/

//리사이클러 뷰 어댑터 클래스
public class SeniorRecyclerviewAdapter extends RecyclerView.Adapter<SeniorRecyclerviewAdapter.CustomViewHolder> {

    //배열 선언
    private ArrayList<SeniorRecyclerviewItem> mData;

    //ArrayList를 파라미터로 가진 생성자로 만들어준다.
    public SeniorRecyclerviewAdapter(ArrayList<SeniorRecyclerviewItem> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public SeniorRecyclerviewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();//Context 클래스가 왜 쓰이는 걸까?
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        //이부분 이해가 안됌 - LayoutInflater가 데이터를 넣는 틀(레이아웃)을 선언하는 건가?(예상)
        View view = inflater.inflate(R.layout.listview_senior_item,parent,false);
        SeniorRecyclerviewAdapter.CustomViewHolder vh = new SeniorRecyclerviewAdapter.CustomViewHolder(view);

        //holder 리턴
        return vh;
    }

    //실제 추가될 때에 대한 생명주기
    @Override
    public void onBindViewHolder(@NonNull SeniorRecyclerviewAdapter.CustomViewHolder holder, int position) {
        SeniorRecyclerviewItem item = mData.get(position);


        holder.profile.setBackground(item.getIv_profile()); //대이터의 이미지 가져오기
        holder.name.setText(item.getTv_name()); //데이터의 mainText 홀딩
        holder.content.setText(item.getTv_detail()); //데이터의 subText 홀딩

    }

    @Override
    public int getItemCount() {

        return mData.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        //item_list.xml 에 있는 위젯을 선언한다.
        ImageView profile;
        TextView name;
        TextView content;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            profile =  itemView.findViewById(R.id.profile);
            name =  itemView.findViewById(R.id.name);
            content =  itemView.findViewById(R.id.detail);

        }
    }
}
