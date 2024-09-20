package com.example.socketio


import android.app.Application
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException


class SocketInstance : Application() {
    private val URL = "https://covid-project-gzb.herokuapp.com"
    private var mSocket: Socket? = null

    override fun onCreate() {
        super.onCreate()
        try {
            // create socket instance
            mSocket = IO.socket(URL)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }

    // return socket Instance
    fun getmSocket(): Socket? {
        return mSocket
    }
}