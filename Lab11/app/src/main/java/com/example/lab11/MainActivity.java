package com.example.lab11;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private TextView myText;
    private static final int CALL_PHONE_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button bCallActivity = (Button) findViewById(R.id.bCallActivity);
        bCallActivity.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        });

        final Button bCallPhone = (Button) findViewById(R.id.bCallPhone);
        bCallPhone.setOnClickListener(view -> checkPermission(android.Manifest.permission.CALL_PHONE, CALL_PHONE_PERMISSION_CODE));

        final Button bOpenCamera = (Button) findViewById(R.id.bOpenCamera);
        bOpenCamera.setOnClickListener(view -> openCamera());
        myText = (TextView) findViewById(R.id.text);
        myText.append("onCreate()\n");
    }

    public void callPhone() {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "123456789"));
        startActivity(dialIntent);
    }

    public void openCamera() {
        startActivity(new Intent("android.media.action.IMAGE_CAPTURE"));
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        } else {
            callPhone();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Разрешение предоставлено", Toast.LENGTH_SHORT).show();
                callPhone();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(MainActivity.this, "Разрешение отклонено! Оно нужно для совершения звонка!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onRestart() {
        super.onRestart();
        myText.append("onRestart()\n");
    }

    @Override
    public void onStart() {
        super.onStart();
        myText.append("onStart()\n");
    }

    @Override
    public void onResume() {
        super.onResume();
        myText.append("onResume()\n");
    }

    @Override
    public void onPause() {
        super.onPause();
        myText.append("onPause()\n");
    }

    @Override
    public void onStop() {
        super.onStop();
        myText.append("onStop()\n");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myText.append("onDestroy()\n");
    }
}