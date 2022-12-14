package com.example.parte2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaFeature;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer,
                   light,
                   temperature;

    private Button gps_button;

    private EditText x_text, y_text, z_text,
                     light_text,
                     temperature_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x_text = (EditText) findViewById(R.id.x_text);
        y_text = (EditText) findViewById(R.id.y_text);
        z_text = (EditText) findViewById(R.id.z_text);
        light_text = (EditText) findViewById(R.id.light_text);
        temperature_text = (EditText) findViewById(R.id.temperature_text);

        gps_button = (Button) findViewById(R.id.gps_button);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        gps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "LONG: " +
                            longi, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
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

            x_text.setText(String.valueOf(x));
            y_text.setText(String.valueOf(y));
            z_text.setText(String.valueOf(z));
        }
        else if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
            light_text.setText(String.valueOf(event.values[0]));
        }
        else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temperature_text.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}