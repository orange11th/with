package com.cookandroid.with.simple;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertNox extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/nox/dbinsert.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    public InsertNox(String ID, String title, String needs, String startDes, String endDes, String time, String way, String content, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("ID", ID);
        parameters.put("title", title);
        parameters.put("needs", needs);
        parameters.put("startDes", startDes);
        parameters.put("endDes", endDes);
        parameters.put("time", time);
        parameters.put("way", way);
        parameters.put("content", content);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}