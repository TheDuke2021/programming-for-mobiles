package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.MyDialogFragmentListener {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), null);
        });
    }

    @Override
    public void onReturnValue(String value) {
        textView.setText("Здравствуйте, " + value);
    }
}