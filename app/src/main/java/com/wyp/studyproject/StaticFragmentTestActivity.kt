package com.wyp.studyproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.FragmentTestActivityLayoutBinding
import com.wyp.studyproject.fragment.DataPassFragment
import com.wyp.studyproject.fragment.DynamicFragment1
import com.wyp.studyproject.fragment.DynamicFragment2
import com.wyp.studyproject.fragment.FullScreenFragment
import com.wyp.studyproject.fragment.OnFragmentDataChangeListener
import com.wyp.studyproject.util.L

class StaticFragmentTestActivity: AppCompatActivity() {


    lateinit var binding: FragmentTestActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = FragmentTestActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val manager = supportFragmentManager
        // 动态添加fragment
        if (savedInstanceState == null) {
            Log.d("test","savedInstanceState == null")
            val transAction = manager.beginTransaction() // 开启事务,和数据库的事务一个概念
            // 第三个参数是传递的参数，可以为空
            val bundel = Bundle().apply {
                putString("test","我是传递的参数")
            }
//            transAction.add(R.id.dynamic_fragment1,DynamicFragment1::class.java,bundel, "dynamic_fragment1")
//            transAction.addToBackStack("fragment1")
//            transAction.setReorderingAllowed(true)
            transAction.commit() // 提交事务  提交之前还可以有多个操作
        }


        binding.buttonAddDynamicFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            trs.add(R.id.dynamic_fragment1, DynamicFragment2::class.java,null,"dynamic_fragmentn")
            trs.setReorderingAllowed(true)
            trs.addToBackStack("fragment2")
            trs.commit()
        }


        binding.buttonAddFullscreenFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            trs.add(R.id.fullscreen_fragment1, FullScreenFragment::class.java,null,"full")
            trs.setReorderingAllowed(true)
            trs.addToBackStack(null)
            trs.commit()
        }


        binding.buttonSearchFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            val fragments = manager.findFragmentByTag("dynamic_fragment1")
            fragments?.let {
                L.d("找到了fragment，${it.toString()}")
                trs.remove(fragments)
//                manager.popBackStack()
                trs.commit()
            }
        }

        binding.buttonReplaceFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            trs.replace(R.id.dynamic_fragment1, FullScreenFragment::class.java,null)
            trs.commit()
        }

        // 隐藏
        binding.buttonHideFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            val toHide = manager.findFragmentByTag("dynamic_fragment1")
            if (toHide != null) {
                trs.hide(toHide)
            }
            trs.commit()
        }

        binding.buttonShowFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            val toShow = manager.findFragmentByTag("dynamic_fragment1")
            if (toShow != null) {
                trs.show(toShow)
            }
            trs.commit()
        }

        binding.buttonDetachFragment.setOnClickListener {

            val trs = manager.beginTransaction()
            val toDetach = manager.findFragmentByTag("dynamic_fragment1")
            if (toDetach != null) {
                trs.detach(toDetach)
            }
            trs.commit()
        }

        binding.buttonAttachFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            val toAttach = manager.findFragmentByTag("dynamic_fragment1")
            if (toAttach != null) {
                trs.attach(toAttach)
            }
            trs.commit()
        }

        binding.buttonConstructor.setOnClickListener {
            val trs = manager.beginTransaction()
            trs.add(R.id.dynamic_fragment1,DataPassFragment("我是参数"),"dataPassFragment")
            trs.setReorderingAllowed(true)
            trs.commit()
        }

        binding.buttonPublic.setOnClickListener {
            val f = manager.findFragmentByTag("dataPassFragment")
            f as DataPassFragment
            f?.let {
                it.passParam("我是通过public方法传递的参数")
            }
        }

        // 通过Arguments传参
        binding.buttonArguments.setOnClickListener {
            val trs = manager.beginTransaction()
            val bundle = Bundle().apply {
                putString("name","wyp")
                putDouble("lat",31.0)
                putDouble("lon",121.0)
                putInt("age",26)
            }
            trs.replace(R.id.dynamic_fragment1, DataPassFragment::class.java,bundle,"dataPassFragmentsWithArg")
            trs.commit()
        }


        // 通过接口传递参数
        binding.buttonInterface.setOnClickListener {
            mMyDataChangeListener?.onDataChange("MyData")
        }


        if (savedInstanceState == null) {
            val trs = manager.beginTransaction()
            trs.add(R.id.dynamic_fragment1,DataPassFragment("我是参数"),"dataPassFragment")
            trs.setReorderingAllowed(true)
            trs.commitNow()

            // 注册fragment的事件监听
            val fragment = manager.findFragmentByTag("dataPassFragment")
            (fragment as DataPassFragment).setOnFragmentDataChangeListener(object: OnFragmentDataChangeListener {
                override fun onFragmentDataChange(data: String) {
                    binding.textFromFragment.text = data
                }
            })
        }
















    }

    private var mMyDataChangeListener: OnMyDataChangeListener? = null
    fun setOnMyDataChangeListener(listener: OnMyDataChangeListener) {
        mMyDataChangeListener = listener
    }


    //TIP fragment 向 activity传参 ，通过activity的方法
    public fun passParamFronFragment(data: String) {
        binding.textFromFragment.text = data
    }





}


interface OnMyDataChangeListener {
    fun onDataChange(data: String)
}