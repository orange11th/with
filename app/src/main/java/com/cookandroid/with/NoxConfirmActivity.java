package com.cookandroid.with;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cookandroid.with.databinding.ActivityNoxConfirmBinding;

import java.util.Objects;

public class NoxConfirmActivity extends AppCompatActivity {
    public ActivityResultLauncher<Intent> launcher;
    private ActivityNoxConfirmBinding binding;
    public static String typeBtn, howBtn, titleValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoxConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ") ;

        //수정 버튼 activity
        binding.modifyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
            startActivity(intent);
        });

        launcher();
    }

    //선착순, 지원에 따라 완료 화면 다르게 뜨도록
    public void launcher() {
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData(); //Nox에서 넘어온 값 받아옴
                //typeBtn = intent.getStringExtra("TYPE");
                titleValue = intent.getStringExtra("TITLE");
                //howBtn = intent.getStringExtra("HOW");

                //binding.titleText.setText(titleValue);

                //Log.d("Tag", "data:" + typeBtn);
                Log.d("Tag", "data:" + howBtn);
                //Log.d("Tag", "data:" + titleValue);
            }


        });

        //등록 버튼을 눌렀을 때
        binding.confirmBtn.setOnClickListener(v -> {
            if (Objects.equals(howBtn, "rBtn1")) {
                Intent intent = new Intent(getApplicationContext(), Complete1Activity.class);
                startActivity(intent);
            } else if(Objects.equals(howBtn, "rBtn2")) {
                Intent intent = new Intent(getApplicationContext(), Complete2Activity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), Complete2Activity.class);
                startActivity(intent);
            }
        });
    }
    //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), NoxActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}