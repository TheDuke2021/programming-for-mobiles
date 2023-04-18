package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView image = findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable animation = (AnimationDrawable)image.getBackground();
        animation.start();
    }
}