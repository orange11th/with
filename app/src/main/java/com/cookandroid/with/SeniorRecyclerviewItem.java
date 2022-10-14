package com.cookandroid.with;

/*written by 병훈*/

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

//리사이클러 뷰, 돌보미 지원자 데이터 클래스 - 리스트 뷰에 출력할 항목 리스트
public class SeniorRecyclerviewItem{
    private Drawable profile;
    private String name;
    private String detail;

    //setter getter

    public Drawable getIv_profile() {
        return profile;
    }

    public void setIv_profile(Drawable profile) {
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