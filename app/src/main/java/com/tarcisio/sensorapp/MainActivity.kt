package com.tarcisio.sensorapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import java.lang.Exception


class MainActivity : AppCompatActivity(), SensorEventListener {

    private val TAG:String = "MainActivity"
    lateinit var sensorManager: SensorManager

    lateinit var accelerometro: Sensor
    lateinit var mGyro: Sensor
    lateinit var mPressure: Sensor
    lateinit var mMagno: Sensor
    lateinit var mTemp: Sensor
    lateinit var mHumi: Sensor
    lateinit var light: Sensor
    lateinit var mProx:Sensor

    lateinit var xValue:TextView
    lateinit var yValue:TextView
    lateinit var zValue:TextView

    lateinit var xGyroValue:TextView
    lateinit var yGyroValue:TextView
    lateinit var zGyroValue:TextView

    lateinit var xMagnoValue:TextView
    lateinit var yMagnoValue:TextView
    lateinit var zMagnoValue:TextView

    lateinit var lightValue:TextView
    lateinit var pressureValue:TextView
    lateinit var tempValue:TextView
    lateinit var humiValue:TextView
    lateinit var proxValue:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xValue = findViewById(R.id.xValue)
        yValue = findViewById(R.id.yValue)
        zValue = findViewById(R.id.zValue)

        xGyroValue = findViewById(R.id.xGyroValue)
        yGyroValue = findViewById(R.id.yGyroValue)
        zGyroValue = findViewById(R.id.zGyroValue)

        xMagnoValue = findViewById(R.id.xMagnoValue)
        yMagnoValue = findViewById(R.id.yMagnoValue)
        zMagnoValue = findViewById(R.id.zMagnoValue)

        lightValue = findViewById(R.id.lightValue)
        pressureValue = findViewById(R.id.pressureValue)
        tempValue = findViewById(R.id.temp)
        humiValue = findViewById(R.id.humiValue)
        proxValue = findViewById(R.id.proxValue)

        Log.d(TAG, "on create: Iniciar Sensor Service")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager;

        accelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometro != null){
            sensorManager.registerListener(this, accelerometro, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor registrado escutando ")
        }else{
            xValue.setText("Accelerometer não suportado")
            yValue.setText("Accelerometer não suportado")
            zValue.setText("Accelerometer não suportado")
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro != null){
            sensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Gyro escutando ")
        }else{
            xGyroValue.setText("Gyro não suportado")
            yGyroValue.setText("Gyro não suportado")
            zGyroValue.setText("Gyro não suportado")
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno != null){
            sensorManager.registerListener(this, mMagno, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Magno escutando ")
        }else{
            xMagnoValue.setText("Magno não suportado")
            yMagnoValue.setText("Magno não suportado")
            zMagnoValue.setText("Magno não suportado")
        }

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null){
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Luz escutando ")
        }else{
            lightValue.setText("Luz não suportada")
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure != null){
            sensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Pressão escutando ")
        }else{
            pressureValue.setText("Pressão não suportado")
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp != null){
            sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Temperatura escutando ")
        }else{
            tempValue.setText("Temperatura não suportada")
        }

        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumi != null){
            sensorManager.registerListener(this, mHumi, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Humidade escutando ")
        }else{
            humiValue.setText("Humidade não suportada")
        }

        mProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(mProx != null){
            sensorManager.registerListener(this, mProx, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "on create: Sensor Proximidade escutando ")
        }else{
            proxValue.setText("Proximidade não suportada")
        }

    }

    override fun onResume(){
        super.onResume()
    }

    override fun onPause(){
        super.onPause()
    }


    override fun onSensorChanged(event: SensorEvent?) {
        var sensor = event!!.sensor
        if(sensor.type == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X: " + event!!.values[0] + " Y: " + event!!.values[1] + " Z: "+ event!!.values[2])

            xValue.setText("xValue: " + event!!.values[0])
            yValue.setText("yValue: " + event!!.values[1])
            zValue.setText("zValue: " + event!!.values[2])
        }else if(sensor.type == Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xGyroValue: " + event!!.values[0])
            yGyroValue.setText("yGyroValue: " + event!!.values[1])
            zGyroValue.setText("zGyroValue: " + event!!.values[2])
        }else if(sensor.type == Sensor.TYPE_MAGNETIC_FIELD){
            xMagnoValue.setText("xMagnoValue: " + event!!.values[0])
            yMagnoValue.setText("yMagnoValue: " + event!!.values[1])
            zMagnoValue.setText("zMagnoValue: " + event!!.values[2])
        }else if(sensor.type == Sensor.TYPE_LIGHT){
            lightValue.setText("Light: "+ event.values[0])
        }else if(sensor.type == Sensor.TYPE_PRESSURE){
            pressureValue.setText("Pressure: " + event.values[0])
        }else if(sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE){
            tempValue.setText("Temperatura: "+event.values[0])
        }else if(sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY){
            humiValue.setText("Humidade: "+event.values[0])
        }else if(sensor.type == Sensor.TYPE_PROXIMITY){
            proxValue.setText("Proximidade: " + event.values[0])
        }


    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}


