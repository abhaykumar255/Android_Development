package com.example.threadrunning

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var getObjHandler1: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val textView1 = findViewById<TextView>(R.id.textView1)
            textView1.text = "Background tasked is Completed on Button 2"
        }
    }
    var getObjHandler2: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val textView2 = findViewById<TextView>(R.id.textView2)
            textView2.text = "Background tasked is Completed on Button 1"
        }
    }

    fun buttonclicked1(v: View?) {
        Log.w("Button 1", "Button 1 is clicked")
        val objRunnable1 = Runnable {
            try {
                Thread.sleep(5000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            getObjHandler2.sendEmptyMessage(0)
        }
        val objBGThread1 = Thread(objRunnable1)
        objBGThread1.start()
        val textView1 = findViewById<TextView>(R.id.textView1)
        textView1.text = "Button1 is Clicked"
        val timeStampCurrent = findViewById<TextView>(R.id.timeStampCurrent)
        val threadName = findViewById<TextView>(R.id.threadName)
        val className = findViewById<TextView>(R.id.className)
        val functionName = findViewById<TextView>(R.id.functionName)
        val tagName = findViewById<TextView>(R.id.tagName)
        val messageName = findViewById<TextView>(R.id.messageName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            val now = LocalDateTime.now()
            timeStampCurrent.text = dtf.format(now)
        }
        threadName.text = "Current Thread " + Thread.currentThread().name
        val classNameC = this.javaClass.simpleName
        className.text = classNameC
        val name = object : Any() {}.javaClass.enclosingMethod.name
        functionName.text = name
        val TAG = MainActivity::class.java.simpleName
        tagName.text = TAG
    }

    fun buttonclicked2(v: View?) {
        Log.w("Button 2", "Button 2 is clicked")
        val objRunnable2 = Runnable {
            try {
                Thread.sleep(5000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            getObjHandler1.sendEmptyMessage(0)
        }
        val objBGThread2 = Thread(objRunnable2)
        objBGThread2.start()
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.text = "Button2 is Clicked"
        val timeStampCurrent = findViewById<TextView>(R.id.timeStampCurrent)
        val threadName = findViewById<TextView>(R.id.threadName)
        val className = findViewById<TextView>(R.id.className)
        val functionName = findViewById<TextView>(R.id.functionName)
        val tagName = findViewById<TextView>(R.id.tagName)
        val messageName = findViewById<TextView>(R.id.messageName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
            val now = LocalDateTime.now()
            timeStampCurrent.text = dtf.format(now)
        }
        threadName.text = "Current Thread " + Thread.currentThread().name
        val classNameC = this.javaClass.simpleName
        className.text = classNameC
        val name = object : Any() {}.javaClass.enclosingMethod.name
        functionName.text = name
        val TAG = MainActivity::class.java.simpleName
        tagName.text = TAG
    }
}