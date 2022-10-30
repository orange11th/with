package com.cookandroid.with;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertNox extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/nox/dbinsert.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    public InsertNox(String userID, String title, String help, String start, String dest, String date, String time, String how, String contents, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("title", title);
        parameters.put("help", help);
        parameters.put("start", start);
        parameters.put("dest", dest);
        parameters.put("date", date);
        parameters.put("time", time);
        parameters.put("how", how);
        parameters.put("contents", contents);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}