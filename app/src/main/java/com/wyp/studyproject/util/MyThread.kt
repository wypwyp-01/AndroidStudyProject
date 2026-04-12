package com.wyp.studyproject.util

import android.os.Handler
import android.os.Looper
import android.os.Message

class MyThread(val handler: Handler): Thread() {

    override fun run() {
        // 耗时操作
        super.run()
        var str = ""
        for (i in (1..100)) {
            str += "字符串$i"
        }
        sleep(30000)
        // 如何把得到的字符串传给主线程，让主线程去显示？
        // 创建message
        val message = Message()
        message.what = 1
        message.obj = str
        handler.sendMessage(message)


    }

}