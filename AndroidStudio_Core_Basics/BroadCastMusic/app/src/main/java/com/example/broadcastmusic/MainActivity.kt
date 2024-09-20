package com.example.broadcastmusic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        //val video = findViewById<VideoView>(R.id.videoView)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the media player
        mediaPlayer = MediaPlayer.create(this,R.raw.sample)

        // Register the broadcast
        var intentFilter  =IntentFilter().apply {
            addAction("com.example.broadcastmusic.MUSIC_START")
            addAction("com.example.broadcastmusic.MUSIC_STOP")
        }

        registerReceiver(broadcastReceiver,intentFilter)

        // start the service
        val serviceIntent = Intent(this,MusicService::class.java)
        startService(serviceIntent)
    }

    private val broadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                "com.example.broadcastmusic.MUSIC_START" -> {
                    Log.d("MainActivity","Received broadcast to start music")
                    mediaPlayer.start()
                }
                "com.example.broadcastmusic.MUSIC_STOP" -> {
                    Log.d("MainActivity","Received broadcast to stop music")
                    mediaPlayer.pause()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)

        // stop the service
        val serviceIntent  =Intent(this,MusicService::class.java)
        stopService(serviceIntent)
    }
}