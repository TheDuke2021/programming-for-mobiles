package com.example.lab15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private TextView accelerometer;
    private TextView light;

    private Sensor accelerometerSensor;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = findViewById(R.id.accelerometer);
        light = findViewById(R.id.light);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);

        accelerometerSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        for (Sensor sensor :
                deviceSensors) {
            System.out.println("=========================");
            System.out.println("Name: " + sensor.getName());
            System.out.println("Type: " + sensor.getStringType());
            System.out.println("Vendor: " + sensor.getVendor());
            System.out.println("=========================");
        }

        manager.registerListener(this, accelerometerSensor, 2_000_000);
        manager.registerListener(this, lightSensor, 2_000_000);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.equals(accelerometerSensor)) {
            String values = String.format("\nX: %.2f Y: %.2f Z: %.2f", sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            accelerometer.append(values);
        } else if (sensor.equals(lightSensor)) {
            String values = String.format("\nLight: %.2f", sensorEvent.values[0]);
            light.append(values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}