package com.example.broadcastmusic

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("MusicService","Service started")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MusicService","Service started")

        // broadcast a message to start the music after 5 sec
        Handler(Looper.getMainLooper()).postDelayed({
            val broadcastIntent = Intent().apply {
                action = "com.example.broadcastmusic.MUSIC_START"
            }
            sendBroadcast(broadcastIntent)
        },5000)

        // broadcast a message to stop the music after 15 second

        Handler(Looper.getMainLooper()).postDelayed({
            val broadcastIntent = Intent().apply {
                action = "com.example.broadcastmusic.MUSIC_STOP"
            }
            sendBroadcast(broadcastIntent)
        },15000)

        // stop service after 20 sec
        Handler(Looper.getMainLooper()).postDelayed({
            stopSelf()
        },20000)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MusicService","Service Destroyed")
    }
}