package com.wyp.studyproject

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.fragment.StorageFragment
import androidx.fragment.app.commit
import com.wyp.studyproject.fragment.FourZujianFragment
import com.wyp.studyproject.util.StandardReceiver
import com.wyp.studyproject.util.StandardReceiver.Companion.STANDARD_ACTION
import com.wyp.studyproject.util.TimerChangerReceiver
import com.wyp.studyproject.util.TimerChangerReceiver.Companion.TIMEACTION

class MainActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityMainBinding
    // 广播
    lateinit var standardReceiver: StandardReceiver
    lateinit var timeChangeReceiver: TimerChangerReceiver










    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // 进入存储页面
        binding.buttonSharedpreference.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.aharedpreferenceFragmentContainer.id, StorageFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
        // 四大组件页面
        binding.buttonFourZujian.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fourzujianFragmentContainer.id, FourZujianFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }

        // 发送自定义广播
        binding.buttonSengBroadcast.setOnClickListener {
            val intent = Intent(STANDARD_ACTION).apply {
                setPackage(packageName)
            }
            sendBroadcast(intent)
        }




    }


    override fun onStart() {
        super.onStart()
        broadCastReceiverTest()

    }

    override fun onStop() {
        super.onStop()
        // 注销广播接收器
        unregisterReceiver(standardReceiver)
        unregisterReceiver(timeChangeReceiver)
    }



    private fun broadCastReceiverTest() {
        // 注册自定义广播
        val intentFilter = IntentFilter()
        intentFilter.addAction(STANDARD_ACTION)
        standardReceiver = StandardReceiver()
        ContextCompat.registerReceiver(
            this,
            standardReceiver,
            intentFilter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        // 注册系统时间改变的广播
        val timeChangeIntentFilter = IntentFilter().apply{
            addAction(TIMEACTION)
        }
        timeChangeReceiver = TimerChangerReceiver()
        ContextCompat.registerReceiver(
            this,timeChangeReceiver,timeChangeIntentFilter,ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }


}