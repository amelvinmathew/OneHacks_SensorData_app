package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

   TextView accelerometerX, accelerometerY, accelerometerZ, ambientTemperature;
    private SensorManager sensorManager;
    private Sensor accelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get text ids
        accelerometerX = findViewById(R.id.accelerometerX);
        accelerometerY = findViewById(R.id.accelerometerY);
        accelerometerZ = findViewById(R.id.accelerometerZ);

        //declare sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //declare sensor type
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        //listen for sensors
        sensorManager.registerListener(MainActivity.this, accelerometer, sensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(MainActivity.this, ambientTemperatureSensor, sensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
           float[] accelerometerValues = new float[0];
           if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
               accelerometerValues = event.values;
               textAccelerometerX.setText("X " + accelerometerValues[0]);
               textAccelerometerY.setText("Y " + accelerometerValues[1]);
               textAccelerometerZ.setText("Z " + accelerometerValues[2]);
           }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
