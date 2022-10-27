package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cookandroid.with.databinding.ActivityComplete1Binding;
import com.cookandroid.with.databinding.ActivityComplete2Binding;

public class Complete2Activity extends AppCompatActivity {
    private ActivityComplete2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplete2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //상세보기 버튼
        binding.detailsBtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), SeniorCheck1Activity.class);
            startActivity(intent);
        });

        //홈으로 가기 버튼
        binding.confirmBtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), SeniorHomeActivity.class);
            startActivity(intent);

        });
    }
}
