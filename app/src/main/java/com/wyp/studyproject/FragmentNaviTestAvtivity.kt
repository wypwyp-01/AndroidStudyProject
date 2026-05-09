package com.wyp.studyproject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.FragmentNaviTestactivityLayoutBinding

class FragmentNaviTestAvtivity: AppCompatActivity() {
    lateinit var binding: FragmentNaviTestactivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = FragmentNaviTestactivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }

}