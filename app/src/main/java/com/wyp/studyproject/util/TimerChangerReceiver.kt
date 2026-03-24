package com.wyp.studyproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class TimerChangerReceiver: BroadcastReceiver() {
    companion object {
        const val TIMEACTION = "android.intent.action.TIME_TICK"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action == TIMEACTION) {
            Toast.makeText(context,"system time changed",LENGTH_SHORT).show()
        }
    }
}