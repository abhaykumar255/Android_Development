package com.example.threadexample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var buttonStartThread: Button? = null

    @Volatile
    private var stopThread = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartThread = findViewById(R.id.button_start_thread)
    }
    fun startThread(view: View?) {
        stopThread = false
        val runnable: ExampleRunnable = ExampleRunnable(10)
        Thread(runnable).start()
    }
    fun stopThread(view: View?) {
        stopThread = true
    }
    class ExampleThread(var seconds: Int) : Thread() {
        override fun run() {
            for (i in 0 until seconds) {
                Log.d(TAG, "startThread: $i")
                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

     inner class ExampleRunnable(var seconds: Int) : Runnable {
        override fun run() {
            for (i in 0 until seconds) {
                if (stopThread) return
                if (i == 5) {
                    runOnUiThread { buttonStartThread!!.text = "50%" }
                }
                Log.d(TAG, "startThread: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}