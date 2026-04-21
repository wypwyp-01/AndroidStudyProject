package com.wyp.studyproject

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.ViewActivityLayoutBinding
import com.wyp.studyproject.util.L
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewActivity: AppCompatActivity(), View.OnClickListener {
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

        binding.buttonChangeActivity.setOnClickListener(this)

        binding.studybutton.setOnClickListener {
            binding.studybutton.isEnabled = false
            var other = ""
            if (binding.agree.isChecked == false && binding.disagree.isChecked == false) {
                other = ",请至少选择一个"
            } else {
                other = ",选中了${findViewById<RadioButton>(binding.isagree.checkedRadioButtonId).text.toString()}"
            }
            L.showToast(this,"长按了,${binding.checkbox.isChecked},${binding.myswitch.isChecked}$other")
            lifecycleScope.launch {
                delay(2000)
                binding.studybutton.isEnabled = true
            }
        }

        binding.imageview.setOnClickListener {
            L.showToast(this,"点击了ImageView")
        }

        binding.imagebutton.setOnClickListener {
            L.showToast(this,"点击了ImageButton")
        }

        binding.checkbox.setOnCheckedChangeListener { button , bool ->
            if (bool) button.text = "选中了"
            else button.text = "还没有选中"
            // L.showToast(this,"状态变化了,$bool")
        }

        binding.myswitch.setOnCheckedChangeListener { button , bool ->
            if (bool) binding.switchtext.text = "选中了"
            else binding.switchtext.text = "没有选中"
            // L.showToast(this,"状态变化了,$bool")
        }



        binding.isagree.setOnCheckedChangeListener { group, i ->
            if (i == R.id.agree) {
                binding.radiotext1.text = "同意了"
            } else if (i == R.id.disagree) {
                binding.radiotext1.text = "没同意"
            }
        }


















    }

    override fun onClick(v: View?) {
        if (v == findViewById(R.id.button_change_activity)) {
            L.showToast(this,"点击了")
        }
    }


}