package com.example.lab10;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    ProgressBar progressBar;
    RatingBar ratingBar;
    TextView text;
    ToggleButton button;
    boolean isRunning = false;
    static final int NUM_STARS = 5;
    float step = 0.5f;
    float rating = 1.0f;
    Thread background;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable updateProgress = new Runnable() {
        public void run() {
            progressBar.incrementProgressBy(1);
            text.setText("Progress: " + progressBar.getProgress() + "%");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        text = (TextView) findViewById(R.id.text3);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        button = (ToggleButton) findViewById(R.id.button_start);
        TextView label = (TextView) findViewById(R.id.text_value);
        ratingBar.setNumStars(NUM_STARS);
        ratingBar.setRating(rating);
        ratingBar.setStepSize(step);
        progressBar.setProgress(0);
        label.setText("Delay: 1000/" + rating);
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> label.setText("Delay: 1000/" + ratingBar.getRating()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                if (button.isChecked()) {
                    background = new Thread(() -> {
                        while (isRunning) {
                            try {
                                Thread.sleep((long) (1000 / ratingBar.getRating()));

                                handler.post(updateProgress);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    isRunning = true;
                    background.start();
                } else isRunning = false;
                break;
            case R.id.button_reset:
                isRunning = false;
                progressBar.setProgress(0);
                text.setText("Progress: 0%");
                break;
            case R.id.button_down:
                rating -= step;
                if (rating < 0.5) rating = 0.5F;
                ratingBar.setRating(rating);
                break;
            case R.id.button_up:
                rating += step;
                if (rating > NUM_STARS) rating = NUM_STARS;
                ratingBar.setRating(rating);
                break;
        }
    }


}