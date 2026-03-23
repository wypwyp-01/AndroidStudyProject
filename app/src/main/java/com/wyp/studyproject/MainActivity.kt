package com.wyp.studyproject

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.fragment.StorageFragment
import androidx.fragment.app.commit
import com.wyp.studyproject.fragment.FourZujianFragment
import com.wyp.studyproject.fragment.StandardReceiver
import com.wyp.studyproject.fragment.StandardReceiver.Companion.STANDARD_ACTION

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var standardReceiver: StandardReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonSharedpreference.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.aharedpreferenceFragmentContainer.id, StorageFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
        binding.buttonFourZujian.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fourzujianFragmentContainer.id, FourZujianFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
    }


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
        intentFilter.addAction(STANDARD_ACTION)
        standardReceiver = StandardReceiver()
        registerReceiver(standardReceiver, intentFilter)
    }


    override fun onStop() {
        super.onStop()
        // 注销接收器
        unregisterReceiver(standardReceiver)
    }


}