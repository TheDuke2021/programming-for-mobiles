package com.example.lab11;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setTitle("Second Activity");
        final Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(arg0 -> SecondActivity.this.finish());
    }
}