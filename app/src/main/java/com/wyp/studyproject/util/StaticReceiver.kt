package com.wyp.studyproject.util

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.RequiresPermission

class StaticReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.VIBRATE)
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent != null && intent.action == "com.wyp.studyproject.static") {
            Toast.makeText(context,"StaticReceiver",LENGTH_SHORT).show()
            // 从系统中获得震动管理器
            val vb: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vb.vibrate(1000)
        }
    }
}