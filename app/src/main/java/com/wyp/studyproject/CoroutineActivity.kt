package com.wyp.studyproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.CoroutineActivityLayoutBinding
import com.wyp.studyproject.util.MyApplication
import com.wyp.studyproject.util.L as L
import com.wyp.studyproject.util.L.log as P
import com.wyp.studyproject.util.RetrofitClient
import com.wyp.studyproject.viewmodel.CoroutineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity: AppCompatActivity() {
    private lateinit var binding: CoroutineActivityLayoutBinding
    private val mainScope = MainScope() // MainScope是一个函数，但是是大写开头，工厂模式
    private val vm: CoroutineViewModel by viewModels()




















    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        P("CoroutineActivity onCreate")
        enableEdgeToEdge()
        binding = CoroutineActivityLayoutBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        binding.buttonAsyncTask.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                // 挂起
                L.d("${Thread.currentThread()}before delay")
                delay(12000)
                L.d("after delay")
                binding.textReceiveBundle.text = ""
                val ret = withContext(Dispatchers.IO) {
                    val response = RetrofitClient.retrofit.testApi()
                    response
                }
                binding.textReceiveBundle.text = ret.toString()
            }

//            L.d("${Thread.currentThread()}before sleep")
//            Thread.sleep(6000)
//            L.d("${Thread.currentThread()}after sleep")

        }

        binding.buttonMainscope.setOnClickListener {
            mainScope.launch {
                try {
                    delay(10000)
                    val response = RetrofitClient.retrofit.testApi()
                    binding.textReceiveBundle.text = response.toString()
                } catch (e: Exception) {
                    // 协程被取消会抛异常，这里来catch异常
                    P("e = $e")
                    e.printStackTrace()
                }
            }
        }
        binding.buttonCoroutineBegin.setOnClickListener {
            vm.getUser()

        }











        vm.userLiveData.observe(this) {user->
            binding.textReceiveBundle.text = user.toString()
        }




    }


    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }




}