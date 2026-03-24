package com.wyp.studyproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

const val ORDERACTION = "com.wyp.studyproject.orderedaction"

class OrderABroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null && intent.action == ORDERACTION) {
            Toast.makeText(context,"OrderABroadcastReceiver",LENGTH_SHORT).show()
        }
        // 中断广播
        abortBroadcast()
    }
}
class OrderBBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null && intent.action == ORDERACTION) {
            Toast.makeText(context,"OrderBBroadcastReceiver",LENGTH_SHORT).show()
        }
    }
}
class OrderCBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null && intent.action == ORDERACTION) {
            Toast.makeText(context,"OrderCBroadcastReceiver",LENGTH_SHORT).show()
        }
    }
}