package com.wyp.studyproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.wyp.studyproject.databinding.AdvancedViewActivityLayoutBinding
import com.wyp.studyproject.databinding.ViewActivityLayoutBinding
import com.wyp.studyproject.util.L

class AdvancedViewActivity: AppCompatActivity() {
    private lateinit var binding: AdvancedViewActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = AdvancedViewActivityLayoutBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.chooseDate.init(2026,3,20,null)
        binding.chooseTime.setIs24HourView(true)

        binding.userPhoneInput.setOnFocusChangeListener{ v,hasFocus->
            if (!hasFocus) {
                val phoneNumber = binding.userPhoneInput.text.toString()
                if (phoneNumber.length != 11) {
                    L.showToast(this,"请输入11位手机号码")
                }
            }
        }

        binding.userNameInput.addTextChangedListener(
            beforeTextChanged = {text, start, count, after ->
                L.d("beforeTextChanged,text = $text,start = $start,count = $count,after = $after")
            },
            onTextChanged = {text, start, before, count ->
                L.d("onTextChanged,text = $text,start = $start,count = $count,before = $before")
            },

            afterTextChanged = { text->
                L.d("afterTextChanged")
                binding.textlength.text = text.toString().length.toString()
            }
        )


        binding.buttonLogin.setOnClickListener {
            val datePicker = binding.chooseDate
            val year = datePicker.year
            val month = 1 + datePicker.month
            val day = datePicker.dayOfMonth

            val timePicker = binding.chooseTime
            val hour = timePicker.hour
            val m = timePicker.minute
            binding.chooseDateText.text = "$year:$month:$day:$hour:$m"




        }



        binding.buttonChooseDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(this,{_, year, month, dayOfMonth->
                binding.chooseDateText.text = "$year:${month + 1}:$dayOfMonth"
            },2026,3,20).show()
        }

        binding.buttonChooseTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val h = calendar.get(Calendar.HOUR_OF_DAY)
            val m = calendar.get(Calendar.MINUTE)
            val dialog = TimePickerDialog(this,{view, hourOfDay, minute->
                binding.chooseDateText.text = "$hourOfDay:${minute}"
            },h,m,true)
            dialog.show()
        }






        // AlterDialog提醒对话框
        onBackPressedDispatcher.addCallback(this) {
            // 创建构建器
            val dialog = AlertDialog.Builder(this@AdvancedViewActivity).apply {
                setTitle("qq音乐")
                setMessage("确定退出？")
                setPositiveButton("退出") {dialog, which ->
                    // 退出
                    finish()
                }
                setNeutralButton("我再想想") {dialog, which ->
                    // 什么都不干 不退出
                }
            }
            // 创建  展示对话框
            dialog.create().show()
        }


    }






}