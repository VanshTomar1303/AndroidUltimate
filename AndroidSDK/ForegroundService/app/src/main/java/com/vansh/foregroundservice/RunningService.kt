package com.vansh.foregroundservice

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class RunningService: Service() {
    // IBinder? -> it is used to create bound service
    override fun onBind(intent: Intent?): IBinder?
    {
        return null
    }

    // it's trigger when an android component send an intent to RunningService
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTIONS.START.toString() -> start()
            ACTIONS.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }
    @SuppressLint("ForegroundServiceType")
    private fun start(){
        val notification = NotificationCompat.Builder(this,"running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("Elapsed time : 00:50")
            .build()
        startForeground(1,notification)
        // id has to be 1 at least
    }

    enum class ACTIONS{
        START,STOP
    }
}