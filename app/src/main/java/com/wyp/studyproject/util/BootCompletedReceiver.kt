package com.wyp.studyproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"studyProject:boot completed",LENGTH_SHORT).show()
        Log.d("BootCompletedReceiver","studyProject:boot completed")
    }
}