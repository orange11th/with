package com.cookandroid.with.topic;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/topic/dbget.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    public GetRequest(String ID,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("ID", ID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
