package com.example.lab5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final ConstraintLayout mylayout = (ConstraintLayout) findViewById(R.id.my_constraint_layout);
        final TextView mytextview = (TextView) findViewById(R.id.my_text);
        switch (item.getItemId()) {
            case R.id.red:
                mytextview.setTextColor(getResources().getColor(R.color.red));
                return true;
            case R.id.green:
                mytextview.setTextColor(getResources().getColor(R.color.green));
                return true;
            case R.id.blue:
                mytextview.setTextColor(getResources().getColor(R.color.blue));
                return true;
            case R.id.exit:
                finish();
                return true;
            case R.id.create_button:
                Button button = new Button(this);
                ConstraintLayout.LayoutParams layoutParams =
                        new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topToBottom = mytextview.getId();
                layoutParams.bottomToBottom = mylayout.getId();
                layoutParams.leftToLeft = mylayout.getId();
                layoutParams.rightToRight = mylayout.getId();
                button.setLayoutParams(layoutParams);
                button.setText("Button");
                mylayout.addView(button);
                return true;
            case R.id.create_textview:
                TextView newtextview = new TextView(this);
                ConstraintLayout.LayoutParams layoutParams1 =
                        new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams1.topToTop = mylayout.getId();
                layoutParams1.bottomToTop = mytextview.getId();
                layoutParams1.leftToLeft = mylayout.getId();
                layoutParams1.rightToRight = mylayout.getId();
                newtextview.setLayoutParams(layoutParams1);
                newtextview.setText("Textview");
                mylayout.addView(newtextview);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
