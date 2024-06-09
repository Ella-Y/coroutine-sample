package com.yeji.coroutineSample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnWeather).setOnClickListener {
            getWeather()
        }
    }

    private fun getWeather() {
        lifecycleScope.launch {
            val model = WeatherUtil().getWeather(37.507859, 127.034597)
            Log.d(TAG, "getWeather: $model")
        }
    }
}