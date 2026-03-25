package com.wyp.studyproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.SecondActivityBinding

class SecondActivity: AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test","SecondActivity onCreate")
        binding = SecondActivityBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonFinishActivity.setOnClickListener {
            // 结束当前activity
            finish()
        }

        binding.buttonCallPhone.setOnClickListener {
            // 隐式意图-拨打电话
            val intent = Intent()
            intent.setAction(Intent.ACTION_DIAL); // 设置意图动作行为
            val uri = Uri.parse("tel:10001") //
            intent.data = uri
            startActivity(intent)
        }

        binding.buttonSendMessage.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SENDTO); // 设置意图动作行为
            val uri = Uri.parse("smsto:19899989998") //
            intent.data = uri
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("test","SecondActivity onStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d("test","SecondActivity onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.d("test","SecondActivity onPause")
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("test","SecondActivity onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("test","SecondActivity onStop")

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("test","SecondActivity onDestroy")
    }
}