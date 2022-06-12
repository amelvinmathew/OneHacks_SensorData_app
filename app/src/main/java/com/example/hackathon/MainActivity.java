package com.example.hackathon;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView
            textSensorList,
            textAccelerometerX, textAccelerometerY, textAccelerometerZ,
            textLinearAccelerometerX, textLinearAccelerometerY, textLinearAccelerometerZ,
            textGyroscopeX, textGyroscopeY, textGyroscopeZ,
            textMagnetometerX, textMagnetometerY, textMagnetometerZ,
            textAmbientTemperature,
            textLight,
            textPressure,
            textRelativeHumidity;

    private SensorManager sensorManager;
    private List<Sensor> allSensors;
    private Sensor accelerometer;
    private Sensor linearAccelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;
    private Sensor ambientTemperature;
    private Sensor light;
    private Sensor pressure;
    private Sensor relativeHumidity;

    private float[] accelerometerCurrentValues       = new float[3];
    private float[] linearAccelerometerCurrentValues = new float[3];
    private float[] gyroscopeCurrentValues           = new float[3];
    private float[] magnetometerCurrentValues        = new float[3];
    private float[] ambientTemperatureCurrentValues  = new float[1];
    private float[] lightCurrentValues               = new float[1];
    private float[] pressureCurrentValues            = new float[1];
    private float[] relativeHumidityCurrentValues    = new float[1];

    private ArrayList<Float> accelerometerXValues       = new ArrayList<Float>();
    private ArrayList<Float> accelerometerYValues       = new ArrayList<Float>();
    private ArrayList<Float> accelerometerZValues       = new ArrayList<Float>();
    private ArrayList<Float> linearAccelerometerXValues = new ArrayList<Float>();
    private ArrayList<Float> linearAccelerometerYValues = new ArrayList<Float>();
    private ArrayList<Float> linearAccelerometerZValues = new ArrayList<Float>();
    private ArrayList<Float> gyroscopeXValues           = new ArrayList<Float>();
    private ArrayList<Float> gyroscopeYValues           = new ArrayList<Float>();
    private ArrayList<Float> gyroscopeZValues           = new ArrayList<Float>();
    private ArrayList<Float> magnetometerXValues        = new ArrayList<Float>();
    private ArrayList<Float> magnetometerYValues        = new ArrayList<Float>();
    private ArrayList<Float> magnetometerZValues        = new ArrayList<Float>();
    private ArrayList<Float> ambientTemperatureValues   = new ArrayList<Float>();
    private ArrayList<Float> lightValues                = new ArrayList<Float>();
    private ArrayList<Float> pressureValues             = new ArrayList<Float>();
    private ArrayList<Float> relativeHumidityValues     = new ArrayList<Float>();

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //get text ids
        //textSensorList = findViewById(R.id.all);

        textAccelerometerX = findViewById(R.id.accelerometerX);
        textAccelerometerY = findViewById(R.id.accelerometerY);
        textAccelerometerZ = findViewById(R.id.accelerometerZ);

//        textLinearAccelerometerX = findViewById(R.id.linearAccerlarationX);
//        textLinearAccelerometerY = findViewById(R.id.linearAccerlarationY);
//        textLinearAccelerometerZ = findViewById(R.id.linearAccerlarationZ);

//        textGyroscopeX = findViewById(R.id.gyroscopeX);
//        textGyroscopeY = findViewById(R.id.gyroscopeY);
//        textGyroscopeZ = findViewById(R.id.gyroscopeZ);
//
//        textMagnetometerX = findViewById(R.id.magnetometerX);
//        textMagnetometerY = findViewById(R.id.magnetometerY);
//        textMagnetometerZ = findViewById(R.id.magnetometerZ);


        textAmbientTemperature = findViewById(R.id.ambientTemperature);
//
//        textLight = findViewById(R.id.light);
//
//        textPressure = findViewById(R.id.pressure);
//
//        textRelativeHumidity = findViewById(R.id.relativeHumidity);
        
        ////////////////////////////////////////////////////////////////////////////////////////////
        //declare sensors/manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer       = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linearAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        gyroscope           = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer        = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        ambientTemperature  = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light               = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressure            = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        relativeHumidity    = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);


        ////////////////////////////////////////////////////////////////////////////////////////////
        //listen for sensors
        sensorManager.registerListener(MainActivity.this, accelerometer,       SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(MainActivity.this, linearAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(MainActivity.this, gyroscope,           SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(MainActivity.this, magnetometer,        SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(MainActivity.this, ambientTemperature,  SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(MainActivity.this, light,               SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(MainActivity.this, pressure,            SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(MainActivity.this, relativeHumidity,    SensorManager.SENSOR_DELAY_UI);

        ////////////////////////////////////////////////////////////////////////////////////////////


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                accelerometerXValues.add(accelerometerCurrentValues[0]);
                accelerometerYValues.add(accelerometerCurrentValues[1]);
                accelerometerZValues.add(accelerometerCurrentValues[2]);

                linearAccelerometerXValues.add(linearAccelerometerCurrentValues[0]);
                linearAccelerometerYValues.add(linearAccelerometerCurrentValues[1]);
                linearAccelerometerZValues.add(linearAccelerometerCurrentValues[2]);

                gyroscopeXValues.add(gyroscopeCurrentValues[0]);
                gyroscopeYValues.add(gyroscopeCurrentValues[1]);
                gyroscopeZValues.add(gyroscopeCurrentValues[2]);

                magnetometerXValues.add(magnetometerCurrentValues[0]);
                magnetometerYValues.add(magnetometerCurrentValues[1]);
                magnetometerZValues.add(magnetometerCurrentValues[2]);

                ambientTemperatureValues.add(ambientTemperatureCurrentValues[0]);

                lightValues.add(lightCurrentValues[0]);

                pressureValues.add(pressureCurrentValues[0]);

                relativeHumidityValues.add(relativeHumidityCurrentValues[0]);

                System.out.println(ambientTemperatureValues);
            }
        }, 0, 1000);


        ////////////////////////////////////////////////////////////////////////////////////////////
        //create list of all sensors
//        allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        List<String> namesOfAllSensors = new ArrayList();
//        for (int i = 0; i<allSensors.size(); i++){
//            namesOfAllSensors.add(allSensors.get(i).getName());
//        }
//        textSensorList.setText("Sensors: \n");
//        for (String i: namesOfAllSensors){
//            textSensorList.append(i);
//            textSensorList.append("\n");
//        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerCurrentValues = event.values;
            textAccelerometerX.setText("X " + accelerometerCurrentValues[0]);
            textAccelerometerY.setText("Y " + accelerometerCurrentValues[1]);
            textAccelerometerZ.setText("Z " + accelerometerCurrentValues[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            linearAccelerometerCurrentValues = event.values;
            textLinearAccelerometerX.setText("X " + Math.round(linearAccelerometerCurrentValues[0]));
            textLinearAccelerometerY.setText("Y " + Math.round(linearAccelerometerCurrentValues[1]));
            textLinearAccelerometerZ.setText("Z " + Math.round(linearAccelerometerCurrentValues[2]));
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeCurrentValues = event.values;
            textGyroscopeX.setText("X " + gyroscopeCurrentValues[0]);
            textGyroscopeY.setText("Y " + gyroscopeCurrentValues[1]);
            textGyroscopeZ.setText("Z " + gyroscopeCurrentValues[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerCurrentValues = event.values;
            textMagnetometerX.setText("X " + magnetometerCurrentValues[0]);
            textMagnetometerY.setText("Y " + magnetometerCurrentValues[1]);
            textMagnetometerZ.setText("Z " + magnetometerCurrentValues[2]);
        }

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            ambientTemperatureCurrentValues = event.values;
            textAmbientTemperature.setText("Ambient Temp: " + ambientTemperatureCurrentValues[0]);
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            lightCurrentValues = event.values;
            textLight.setText("Light Value: " + lightCurrentValues[0]);
        }

        if (event.sensor.getType() == Sensor.TYPE_PRESSURE){
            pressureCurrentValues = event.values;
            textPressure.setText("Pressure: " + pressureCurrentValues[0]);
        }

        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            relativeHumidityCurrentValues = event.values;
            textRelativeHumidity.setText("Relative Humidity: " + relativeHumidityCurrentValues[0]);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}