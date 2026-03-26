package com.wyp.studyproject

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
        Log.d("test","MainActivity onCreate")
        enableEdgeToEdge()
        binding = ActivityMainBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val bundle = data?.extras
                val height = bundle?.getDouble("response_height",0.0) ?: 0.0
                val age = bundle?.getInt("response_age",0) ?: 0
                val name = bundle?.getString("response_name","") ?: ""
                val info = data?.getIntExtra("info",0) ?: 0
                binding.textReceiveBundle.text = "name:$name\nage:$age\nheight:$height\ninfo:$info"
                binding.textReceiveBundle.visibility = VISIBLE
            }
        }

        // 包管理器可以获取应用程序相关的信息
        // 从ContextWrapper获取包管理器
        val manager = packageManager
        // 从包管理器获取activity的元数据信息
        val info = manager.getActivityInfo(componentName,PackageManager.GET_META_DATA)
        val data = info.metaData
        val s = data.getString("weather")
        binding.textReceiveBundle.text = "s:$s"
        binding.textReceiveBundle.visibility = VISIBLE





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
            // 显式意图 - 在Intent的构造函数中指定。
            // val intent = Intent(this, SecondActivity::class.java)

            // 显式意图
//            val intent = Intent()
//            intent.setClass(this,SecondActivity::class.java)
            val component = ComponentName(this,SecondActivity::class.java)
            val intent = Intent().apply{
                setComponent(component)
            }
            // 向下一个activity传递信息
            // Intent使用Bundle对象存放待传递的数据信息。
            val bundle = Bundle().apply{
                putString("time","2026.03.26")
                putInt("age",25)
                putDouble("height",183.0)
            }
            intent.putExtra("extra",123)
            intent.putExtras(bundle)
            // startActivity(intent)
            launcher.launch(intent)

        }









        binding.buttonTestActivity.setOnClickListener {
            Toast.makeText(this,"test activity",LENGTH_LONG).show()
        }





    }


    override fun onStart() {
        super.onStart()
        Log.d("test","MainActivity onStart")
        broadCastReceiverTest()

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("test","MainActivity onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("test","MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("test","MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("test","MainActivity onStop")

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("test","MainActivity onDestroy")
        // 注销广播接收器
        unregisterReceiver(standardReceiver)
        // unregisterReceiver(timeChangeReceiver)

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