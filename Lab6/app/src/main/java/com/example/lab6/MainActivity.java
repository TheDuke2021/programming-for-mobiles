package com.example.lab6;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    int notificationId = 1;
    String CHANNEL_ID = "my_channel_01";
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==button1) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout,null);
            TextView text = (TextView) layout.findViewById(R.id.textView);
            EditText editText = (EditText) findViewById(R.id.editText);
            text.setText(editText.getText());
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.FILL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
        else if(v==button2) {
//            createNotificationChannel();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, 0);
            final NotificationCompat.Builder builder = new
                    NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.status_bar_icon)
                    .setContentTitle(getString(R.string.notification_title))
                    .setContentText(getString(R.string.notification_text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(getApplicationContext());
            notificationManager.notify(notificationId, builder.build());
        }
    }


//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new
//                    NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            NotificationManager notificationManager =
//                    getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

}