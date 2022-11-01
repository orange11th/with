package com.cookandroid.with.register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HelperRegistRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://3.36.66.178/register/Helperdbinsert.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;

    public HelperRegistRequest(String ID, String password, String name, String email, String number, String address,
                         String address_detail,String sex, String birth, String license, String needs,
                         String personality_IE,
                         String personality_TF,
                         Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("ID", ID);
        parameters.put("password", password);
        parameters.put("name", name);
        parameters.put("email", email);
        parameters.put("number", number);
        parameters.put("address", address);
        parameters.put("address_detail", address_detail);
        parameters.put("sex", sex);
        parameters.put("birth", birth);
        parameters.put("license", license);
        parameters.put("needs", needs);
        parameters.put("personality_IE", personality_IE);
        parameters.put("personality_TF", personality_TF);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
