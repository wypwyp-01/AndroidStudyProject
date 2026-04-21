package com.wyp.studyproject.util

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.wyp.studyproject.R

object L {




    fun d(content: String,tag: String = "test") {
        Log.d(tag,content)
    }



    fun log(content: String,tag: String = "test") {
        Log.d(tag,content)
    }


    fun showToast(context: Context, content: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null)
        val toast = Toast(context).apply{
            view.findViewById<TextView>(R.id.tv_msg).text = content
            setView(view)
            setGravity(Gravity.TOP,0,0)
            duration = LENGTH_LONG
        }
        toast.show()

    }

}



