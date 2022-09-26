package com.cookandroid.with.board;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/board/dbinsert.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    public InsertRequest(String userID, String subject, String content,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("subject", subject);
        parameters.put("content", content);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
