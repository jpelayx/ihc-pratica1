package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float accelerometer_x, accelerometer_y, accelerometer_z;
    private boolean accelerometer_first_read;

    private EditText x_text, y_text, z_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometer_first_read = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x_text = (EditText) findViewById(R.id.x_text);
        y_text = (EditText) findViewById(R.id.y_text);
        z_text = (EditText) findViewById(R.id.z_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected  void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if(accelerometer_first_read) {
                accelerometer_first_read = false;
            }
            else {
                float acc_threshold = 5.0F;
                if(Math.abs(x - accelerometer_x) > acc_threshold
                        || Math.abs(y - accelerometer_y) > acc_threshold
                        || Math.abs(z - accelerometer_z) > acc_threshold) {
                    Intent intent = new Intent(this, MainActivity2.class);
                    startActivity(intent);
                }
            }
            accelerometer_x = x;
            accelerometer_y = y;
            accelerometer_z = z;

            x_text.setText(String.valueOf(x));
            y_text.setText(String.valueOf(y));
            z_text.setText(String.valueOf(z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}