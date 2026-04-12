package com.wyp.studyproject

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ContentValues
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ServiceCompat.stopForeground
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
import com.wyp.studyproject.data.TagInfo
class MainActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityMainBinding
    // 广播
    lateinit var standardReceiver: StandardReceiver
    lateinit var timeChangeReceiver: TimerChangerReceiver


    lateinit var orderBroadcast1: OrderABroadcastReceiver
    lateinit var orderBroadcast2: OrderBBroadcastReceiver
    lateinit var orderBroadcast3: OrderCBroadcastReceiver


    private val conn = object: ServiceConnection {
        lateinit var downloadBinder: MyBinder
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder? // 这里service指的就是倍绑定的service，来自于MyBindService的onBind方法里面返回的Binder对象
        ) {
            Log.d(TAG,"ServiceConnection onServiceConnected")
            downloadBinder = service as MyBinder // 这里通过类型转换拿到binder
            downloadBinder.startDownload()
            downloadBinder.getProcess()
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG,"ServiceConnection onServiceDisconnected")
        }
    }



    @SuppressLint("Range")
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

        binding.buttonInsert.setOnClickListener {
            val uri = Uri.parse("content://com.example.dowmloadaweme.tagcontentprovider/tags")
            val values = ContentValues().apply {
                val index = (1..100).random()
                val value = "testname$index"
                Log.d("testContentProvider","value = $value")
                put("name",value)
            }
            val cursor = contentResolver.insert(uri,values)
            Log.d("testContentProvider","cursor = $cursor")
            Toast.makeText(this,"保存成功",LENGTH_LONG).show()
        }

        binding.buttonQuery.setOnClickListener {
            val uri = Uri.parse("content://com.example.dowmloadaweme.tagcontentprovider/tags")
            val cursor = contentResolver.query(uri,null,null,null,null)
            var content = ""
            cursor?.let {
                while(cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val coverPath = cursor.getString(cursor.getColumnIndex("coverPath"))
                    val tags = TagInfo(id,name)
                    content += tags.toString()
                }
            }
            cursor?.close()
            Log.d("testContentProvider","content = $content")
            binding.textContentResolver.text = content
        }

        binding.buttonDelete.setOnClickListener {
            val uri = Uri.parse("content://com.example.dowmloadaweme.tagcontentprovider/tags")
            val rowsDeleted = contentResolver.delete(
                uri,                // 你的URI
                "name=?",           // where 条件
                arrayOf("testname64")  // 条件的值
            )
            binding.textContentResolver.text = "删除$rowsDeleted 行数据"
        }

        binding.buttonClear.setOnClickListener {
            binding.textContentResolver.text = ""
        }
        val intent = Intent(this, MyService::class.java)
        binding.buttonService1.setOnClickListener {
            startService(intent)
        }

        binding.buttonService2.setOnClickListener {
            stopService(intent)
        }

        val bindintent = Intent(this, MyBindService::class.java)

        binding.buttonService3.setOnClickListener {
            bindService(bindintent,conn,BIND_AUTO_CREATE)
        }
        binding.buttonService4.setOnClickListener {
            unbindService(conn)
        }
        val intent2 = Intent(this, MyForegroundService::class.java)
        binding.buttonService5.setOnClickListener {
            startService(intent2)
        }
        binding.buttonService6.setOnClickListener {
            stopService(intent2)
        }

        binding.buttonService7.setOnClickListener {
            intent2.putExtra("stop_foreground",1)
            startService(intent2)
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