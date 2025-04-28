package com.example.czujniki;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor proximitySensor;

    private TextView accelerometerText;
    private TextView gyroscopeText;
    private TextView proximityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerText = findViewById(R.id.accelerometerText);
        gyroscopeText = findViewById(R.id.gyroscopeText);
        proximityText = findViewById(R.id.proximityText);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the sensors
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listeners
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listeners
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                accelerometerText.setText("Accelerometer:\nX: " + x + "\nY: " + y + "\nZ: " + z);
                break;

            case Sensor.TYPE_GYROSCOPE:
                float rotationX = event.values[0];
                float rotationY = event.values[1];
                float rotationZ = event.values[2];
                gyroscopeText.setText("Gyroscope:\nX: " + rotationX + "\nY: " + rotationY + "\nZ: " + rotationZ);
                break;

            case Sensor.TYPE_PROXIMITY:
                float distance = event.values[0];
                proximityText.setText("Proximity: " + distance + " cm");
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // You can handle accuracy changes if needed
    }
}