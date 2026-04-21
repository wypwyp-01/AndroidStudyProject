package com.wyp.studyproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.FragmentTestActivityLayoutBinding
import com.wyp.studyproject.fragment.DynamicFragment1

class StaticFragmentTestActivity: AppCompatActivity() {


    lateinit var binding: FragmentTestActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = FragmentTestActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 动态添加fragment
        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transAction = manager.beginTransaction() // 开启事务,和数据库的事务一个概念
            // 第三个参数是传递的参数，可以为空
            transAction.add(R.id.dynamic_fragment1,DynamicFragment1::class.java,null)
            transAction.addToBackStack(null)
            transAction.setReorderingAllowed(true)
            transAction.commit() // 提交事务  提交之前还可以有多个操作
        }















    }







}