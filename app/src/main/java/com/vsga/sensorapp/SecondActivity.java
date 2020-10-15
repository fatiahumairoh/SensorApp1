package com.vsga.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;
    private TextView txtLight;
    private TextView txtProximity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        txtLight=findViewById(R.id.txtLight);
        txtProximity=findViewById(R.id.txtProximity);

        mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorLight=mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        String no_sensor=getResources().getString(R.string.error_no_sensor);

        if(mSensorLight==null){ 
            txtLight.setText(no_sensor);
        }
        if (mSensorProximity==null){
            txtProximity.setText(no_sensor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mSensorLight !=null){
            mSensorManager.registerListener((android.hardware.SensorEventListener) this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(mSensorProximity !=null){
            mSensorManager.registerListener((android.hardware.SensorEventListener) this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener((android.hardware.SensorEventListener) this);
    }

    @Override
    public void OnSensorChanged(SensorEvent sensorEvent){
        Character sensor;
        int sensorType=SensorEvent.sensor.getType();

        float currentValue=sensorEvent.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                txtLight.setText(getResources().getString(R.string.label_light, currentValue));
            case Sensor.TYPE_PROXIMITY:
                txtProximity.setText(getResources().getString(R.string.label_proximity, currentValue));
        }

    }

        public void onAccuracyChanged(Sensor sensor,int i){

        }


}