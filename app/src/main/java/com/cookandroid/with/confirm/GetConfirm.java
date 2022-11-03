package com.cookandroid.with.confirm;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetConfirm extends StringRequest {
    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/confirm/dbget.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    /* 수정 필요 */
    public GetConfirm(String matchingID, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("matchingID", matchingID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
