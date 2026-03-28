package com.wyp.studyproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class EventDistributeActivity: AppCompatActivity() {
    var buttonTop: Button? = null
    var textTop : TextView? = null
    var layoutTop : LinearLayout? = null
    var layoutMiddle: FrameLayout? = null
    var imgBg: ImageView? = null
    var buttonFloat: Button? = null
    var scrollArea: ScrollView? = null
    var layoutScrollContent: LinearLayout? = null
    var textScroll: TextView? = null
    var recyclerView: RecyclerView? = null
    var buttonBottom: Button? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_dispatch)
        buttonTop = findViewById(R.id.btn_top)
        textTop = findViewById(R.id.tv_top)
        layoutTop = findViewById(R.id.layout_top)

        layoutMiddle = findViewById(R.id.layout_middle)
        imgBg = findViewById(R.id.img_bg)
        buttonFloat = findViewById(R.id.btn_float)

        scrollArea = findViewById(R.id.scroll_area)
        layoutScrollContent = findViewById(R.id.layout_scroll_content)
        textScroll = findViewById(R.id.tv_scroll)
        recyclerView = findViewById(R.id.recycler_view)
        buttonBottom = findViewById(R.id.btn_bottom)

        layoutTop?.setOnTouchListener { view, event ->
            val result = when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Touch", "layoutTop ACTION_DOWN")
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("Touch", "layoutTop ACTION_MOVE")
                    false
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()   // 关键：调用 performClick
                    Log.d("Touch", "layoutTop ACTION_UP")
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d("Touch", "layoutTop ACTION_CANCEL")
                    true
                }
                else -> true
            }
            result
        }






//        buttonTop?.setOnTouchListener { view, event ->
//            val result = when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    Log.d("Touch", "buttonTop ACTION_DOWN")
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    Log.d("Touch", "buttonTop ACTION_MOVE")
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    view.performClick()   // 关键：调用 performClick
//                    Log.d("Touch", "buttonTop ACTION_UP")
//                    false
//                }
//                MotionEvent.ACTION_CANCEL -> {
//                    Log.d("Touch", "buttonTop ACTION_CANCEL")
//                    true
//                }
//                else -> true
//            }
//            result
//        }
//
//
//
//        textTop?.setOnTouchListener { view, event ->
//            val result = when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    Log.d("Touch", "textTop ACTION_DOWN")
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    Log.d("Touch", "textTop ACTION_MOVE")
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    view.performClick()   // 关键：调用 performClick
//                    Log.d("Touch", "textTop ACTION_UP")
//                    false
//                }
//                MotionEvent.ACTION_CANCEL -> {
//                    Log.d("Touch", "textTop ACTION_CANCEL")
//                    true
//                }
//                else -> true
//            }
//            result
//        }



    }


    override fun onDestroy() {
        super.onDestroy()
    }

}

private fun LinearLayout?.setOnTouchListener(l: (View, MotionEvent) -> Unit) {}
