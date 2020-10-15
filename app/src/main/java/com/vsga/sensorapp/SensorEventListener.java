package com.vsga.sensorapp;

import android.hardware.SensorEvent;

public interface SensorEventListener {
    void OnSensorChanged(SensorEvent sensorEvent);
}
