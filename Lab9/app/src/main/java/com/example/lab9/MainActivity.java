package com.example.lab9;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(chronometer -> {
            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            int h = (int) (time / 3600000);
            int m = (int) (time - h * 3600000) / 60000;
            int s = (int) (time - h * 3600000 - m * 60000) / 1000;
            chronometer.setText(String.format("%02d:%02d:%02d", h, m, s));
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start:
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                break;
            case R.id.button_stop:
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                break;
            case R.id.button_reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                break;
            case R.id.button_create:
                ViewGroup mylayout = findViewById(R.id.root);
                Button button = new Button(this);
                ConstraintLayout.LayoutParams layoutParams =
                        new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topToBottom = R.id.linearLayout;
                layoutParams.bottomToBottom = mylayout.getId();
                layoutParams.leftToLeft = mylayout.getId();
                layoutParams.rightToRight = mylayout.getId();
                button.setLayoutParams(layoutParams);
                button.setText("Button");
                mylayout.addView(button);
                break;
        }
    }
}