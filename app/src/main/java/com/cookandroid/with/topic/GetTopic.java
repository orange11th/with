package com.cookandroid.with.topic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.with.R;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.JSONObject;

public class GetTopic extends AppCompatActivity {
    EditText edtNo;
    Button btnGet;
    TextView txtResult;
    String ID,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_topic);

        edtNo=(EditText)findViewById(R.id.edtNo);
        btnGet=(Button) findViewById(R.id.btnGet);
        txtResult=(TextView)findViewById(R.id.txtResult);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ID= edtNo.getText().toString();
                result="";

                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                result=jsonResponse.getString("ID");
                                result+=": "+jsonResponse.getString("title");
                                txtResult.setText(result);
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            StringWriter errors = new StringWriter();
                            e.printStackTrace(new PrintWriter(errors));
                            Toast.makeText(getApplicationContext(), errors.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                GetRequest getRequest = new GetRequest(ID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(GetTopic.this);
                queue.add(getRequest);
            }
        });
    }
}