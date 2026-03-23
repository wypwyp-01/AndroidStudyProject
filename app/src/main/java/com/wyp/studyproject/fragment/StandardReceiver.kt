package com.wyp.studyproject.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

// 定义广播接受者
class StandardReceiver: BroadcastReceiver() {
    companion object {
        const val STANDARD_ACTION = "com.wyp.studyproject.standard"
    }
    // 收到标准广播，马上触发StandardReceiver
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent?.action == STANDARD_ACTION) {
            Toast.makeText(context,"收到standard广播",LENGTH_SHORT).show()
        }
    }
}