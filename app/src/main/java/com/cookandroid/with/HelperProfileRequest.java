package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.login.LoginActivity;
import com.cookandroid.with.login.LoginRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*written by 병훈*/

public class HelperProfileRequest extends StringRequest {
    final static private String URL = "http://3.36.66.178/select_helper_profile.php";
    private Map<String, String> parameters;

    public HelperProfileRequest(String id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}