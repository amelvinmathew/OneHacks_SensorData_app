package com.example.hackathon;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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



    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //get text ids
        textSensorList = findViewById(R.id.all);

        textAccelerometerX = findViewById(R.id.accelerometerX);
        textAccelerometerY = findViewById(R.id.accelerometerY);
        textAccelerometerZ = findViewById(R.id.accelerometerZ);

        textLinearAccelerometerX = findViewById(R.id.linearAccerlarationX);
        textLinearAccelerometerY = findViewById(R.id.linearAccerlarationY);
        textLinearAccelerometerZ = findViewById(R.id.linearAccerlarationZ);

        textGyroscopeX = findViewById(R.id.gyroscopeX);
        textGyroscopeY = findViewById(R.id.gyroscopeY);
        textGyroscopeZ = findViewById(R.id.gyroscopeZ);

        textMagnetometerX = findViewById(R.id.magnetometerX);
        textMagnetometerY = findViewById(R.id.magnetometerY);
        textMagnetometerZ = findViewById(R.id.magnetometerZ);


        textAmbientTemperature = findViewById(R.id.ambientTemperature);

        textLight = findViewById(R.id.light);

        textPressure = findViewById(R.id.pressure);

        textRelativeHumidity = findViewById(R.id.relativeHumidity);

        GraphView ambientTemperatureGraph = (GraphView) findViewById(R.id.ambientTemperaturegraph);

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
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

        });


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
        float[] accelerometerValues = new float[0];
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = event.values;
            textAccelerometerX.setText("X " + accelerometerValues[0]);
            textAccelerometerY.setText("Y " + accelerometerValues[1]);
            textAccelerometerZ.setText("Z " + accelerometerValues[2]);
        }

        float[] linearAccelerometerValues = new float[0];
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            linearAccelerometerValues = event.values;
            textLinearAccelerometerX.setText("X " + Math.round(linearAccelerometerValues[0]));
            textLinearAccelerometerY.setText("Y " + Math.round(linearAccelerometerValues[1]));
            textLinearAccelerometerZ.setText("Z " + Math.round(linearAccelerometerValues[2]));
        }

        float[] gyroscopeValues = new float[0];
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeValues = event.values;
            textGyroscopeX.setText("X " + gyroscopeValues[0]);
            textGyroscopeY.setText("Y " + gyroscopeValues[1]);
            textGyroscopeZ.setText("Z " + gyroscopeValues[2]);
        }

        float[] magnetometerValues = new float[0];
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerValues = event.values;
            textMagnetometerX.setText("X " + magnetometerValues[0]);
            textMagnetometerY.setText("Y " + magnetometerValues[1]);
            textMagnetometerZ.setText("Z " + magnetometerValues[2]);
        }

        float[] ambientTemperatureValues =  new float[0];
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            ambientTemperatureValues = event.values;
            textAmbientTemperature.setText("Ambient Temp: " + ambientTemperatureValues[0]);
        }

        float[] lightValues =  new float[0];
        if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            lightValues = event.values;
            textLight.setText("Light Value: " + lightValues[0]);
        }

        float[] pressureValues =  new float[0];
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE){
            pressureValues = event.values;
            textPressure.setText("Pressure: " + pressureValues[0]);
        }

        float[] relativeHumidityValues =  new float[0];
        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            relativeHumidityValues = event.values;
            textRelativeHumidity.setText("Relative Humidity: " + relativeHumidityValues[0]);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}