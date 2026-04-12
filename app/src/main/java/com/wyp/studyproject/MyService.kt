package com.wyp.studyproject

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

val TAG = "testService"
class MyBindService : Service() {
    val mBinder = MyBinder()
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"MyBindService onCreate")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MyBindService onDestroy")

    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG,"MyBindService onStartCommand")
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"MyBindService onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        super.onUnbind(intent)
        Log.d(TAG,"MyBindService onUnbind")
        return true
    }
}

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"MyService onCreate")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MyService onDestroy")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG,"MyService onStartCommand")
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"MyService onBind")
        return Binder()
    }
    override fun onUnbind(intent: Intent?): Boolean {
        super.onUnbind(intent)
        Log.d(TAG,"MyService onUnbind")
        return true
    }
}

class MyForegroundService : Service() {
    @SuppressLint("ForegroundServiceType", "ServiceCast")
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"MyForegroundService onCreate")
        // 首先创建通知
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 创建通知渠道（Android 8.0+需要）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "qq_music",
                "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        val no = NotificationCompat.Builder(this,"qq_music")
            .setSmallIcon(R.drawable.small_icon_noon_rain)
            .setContentTitle("qq音乐")
            .setContentText("播放中")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        // 然后startForeground
        startForeground(1,no)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MyForegroundService onDestroy")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG,"MyForegroundService onStartCommand")
        val extra = intent?.getIntExtra("stop_foreground",0)
        if(extra == 1) {
            Log.d(TAG,"stop_foreground")
            stopForeground(true)
        }

        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"MyForegroundService onBind")
        return Binder()
    }
    override fun onUnbind(intent: Intent?): Boolean {
        super.onUnbind(intent)
        Log.d(TAG,"MyForegroundService onUnbind")
        return true
    }
}

class MyBinder: Binder() {
    fun startDownload() {
        Log.d(TAG,"MyBinder startDownload")
    }

    fun getProcess() {
        Log.d(TAG,"MyBinder getProcess")
    }
}

