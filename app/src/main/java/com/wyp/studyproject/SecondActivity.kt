package com.wyp.studyproject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.SecondActivityBinding

class SecondActivity: AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()

    }






}