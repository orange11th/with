package com.cookandroid.with;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.with.databinding.ActivityComplete1Binding;
import com.cookandroid.with.seniorHome.SeniorHomeActivity;

public class Complete1Activity extends AppCompatActivity {
    private ActivityComplete1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplete1Binding.inflate(getLayoutInflater());
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
