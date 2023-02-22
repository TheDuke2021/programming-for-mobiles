package com.example.lab8;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentSendDataListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onSendData(String selectedItem) {
        DetailFragment fragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detailFragment);
        if (fragment != null) fragment.setSelectedItem(selectedItem);
    }
}
