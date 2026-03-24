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
import com.wyp.studyproject.util.ORDERACTION
import com.wyp.studyproject.util.OrderABroadcastReceiver
import com.wyp.studyproject.util.OrderBBroadcastReceiver
import com.wyp.studyproject.util.OrderCBroadcastReceiver
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


    lateinit var orderBroadcast1: OrderABroadcastReceiver
    lateinit var orderBroadcast2: OrderBBroadcastReceiver
    lateinit var orderBroadcast3: OrderCBroadcastReceiver






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

        // 发送有序广播
        binding.buttonSendOrderBroadcast.setOnClickListener {
            val intent = Intent(ORDERACTION).apply {
                setPackage(packageName)
            }
            // 有些广播，接收广播需要权限。广播接受者如果没有权限就收不到这个广播
            sendOrderedBroadcast(intent,null)
        }

        // 发送静态广播
        binding.buttonSendStaticBroadcast.setOnClickListener {
            val intent = Intent("com.wyp.studyproject.static").apply {
                setPackage(packageName)
            }
            sendBroadcast(intent)
        }

        // 跳转activity
        binding.buttonChangeActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
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

        unregisterReceiver(orderBroadcast1)
        unregisterReceiver(orderBroadcast2)
        unregisterReceiver(orderBroadcast3)
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
//        ContextCompat.registerReceiver(
//            this,timeChangeReceiver,timeChangeIntentFilter,ContextCompat.RECEIVER_NOT_EXPORTED
//        )

        // 注册有序广播接受者
        val orderIntentFilter1 = IntentFilter().apply{
            addAction(ORDERACTION)
            priority = 10
        }
        val orderIntentFilter2 = IntentFilter().apply{ addAction(ORDERACTION)
            priority = 5
        }
        val orderIntentFilter3 = IntentFilter().apply{ addAction(ORDERACTION)
            priority = 15
        }
        orderBroadcast1 = OrderABroadcastReceiver()
        orderBroadcast2 = OrderBBroadcastReceiver()
        orderBroadcast3 = OrderCBroadcastReceiver()

        ContextCompat.registerReceiver(this, orderBroadcast1, orderIntentFilter1, ContextCompat.RECEIVER_NOT_EXPORTED)
        ContextCompat.registerReceiver(this, orderBroadcast2, orderIntentFilter2, ContextCompat.RECEIVER_NOT_EXPORTED)
        ContextCompat.registerReceiver(this, orderBroadcast3, orderIntentFilter3, ContextCompat.RECEIVER_NOT_EXPORTED)
    }


}