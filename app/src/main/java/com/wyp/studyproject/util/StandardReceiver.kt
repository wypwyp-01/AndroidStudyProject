package com.wyp.studyproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

// 定义广播接受者
class StandardReceiver: BroadcastReceiver() {
    companion object {
        const val STANDARD_ACTION = "com.wyp.studyproject.standard"
    }
    // 收到标准广播，马上触发StandardReceiver
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent?.action == STANDARD_ACTION) {
            Toast.makeText(context,"收到standard广播", Toast.LENGTH_SHORT).show()
            Log.d("test","收到广播")
        }
    }
}