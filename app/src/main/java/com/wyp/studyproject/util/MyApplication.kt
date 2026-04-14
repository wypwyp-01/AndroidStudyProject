package com.wyp.studyproject.util

import android.app.Application
import android.content.res.Configuration
import com.wyp.studyproject.util.L.log as P

class MyApplication: Application() {
    var infoMap: MutableMap<String,Any> = mutableMapOf()
    var userName: String = ""
    var userAge: Int = 0
    companion object {
        var mApplication: MyApplication? = null
        fun getInstance(): MyApplication {
            return mApplication!!
        }
    }
    // 在APP启动的时候调用
    override fun onCreate() {
        super.onCreate()
        mApplication = this
        P("MyApplication onCreate")
    }
}