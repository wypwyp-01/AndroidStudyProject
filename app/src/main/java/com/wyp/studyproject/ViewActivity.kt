package com.wyp.studyproject

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.ViewActivityLayoutBinding

class ViewActivity: AppCompatActivity() {
    private lateinit var binding: ViewActivityLayoutBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ViewActivityLayoutBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonToast1.setOnClickListener {
            Toast.makeText(this,"普通Toast",LENGTH_LONG).show()
        }
        binding.buttonToast2.setOnClickListener {
            val image = ImageView(this).apply{
                setImageResource(R.drawable.small_icon_rain)

            }
            val toast = Toast(this).apply{
                setView(image)
                setGravity(Gravity.CENTER,0,0)
                duration = LENGTH_LONG
            }
            toast.show()
        }
        binding.buttonToast3.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.toast_layout, null)
            val toast = Toast(this).apply{
                setView(view)
                setGravity(Gravity.CENTER,0,0)
                duration = LENGTH_LONG
            }
            toast.show()
        }


















    }







}