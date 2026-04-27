package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.wyp.studyproject.databinding.DatapassFragmentLayoutBinding
import androidx.lifecycle.MutableLiveData
import com.wyp.studyproject.OnMyDataChangeListener
import com.wyp.studyproject.StaticFragmentTestActivity
import com.wyp.studyproject.util.L

class DataPassFragment(val mParam: String): Fragment() {

    lateinit var binding: DatapassFragmentLayoutBinding

    var publicParam = ""

    constructor(): this("")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DatapassFragmentLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDataPassConstructor.text = mParam

        val arg = arguments
        val name = arg?.getString("name") ?: ""
        val lat = arg?.getDouble("lat") ?: 0.0
        val lon = arg?.getDouble("lon") ?: 0.0
        val age = arg?.getInt("age") ?: 0
        L.showToast(requireActivity(),"$name  $lat  $lon  $age")



        (activity as StaticFragmentTestActivity).setOnMyDataChangeListener(object : OnMyDataChangeListener {
            override fun onDataChange(data: String) {
                L.showToast(requireActivity(),"数据变化了，数据是：$data")
            }
        })


        // fragment 向 activity传参 ，通过activity的方法
        binding.buttonSendDataToActivity.setOnClickListener {
            (activity as StaticFragmentTestActivity).passParamFronFragment("我是通过public方法从fragment传递的数据")
        }

        binding.buttonSendDataToActivityInterface.setOnClickListener {
            listen?.let {
                it.onFragmentDataChange("我是通过接口从fragment传递的数据")
            }
        }



    }


    fun passParam(str: String) {
        publicParam = str
        binding.textDataPassPublicMethod.text = publicParam
    }


    private var listen: OnFragmentDataChangeListener? = null

    public fun setOnFragmentDataChangeListener(listener: OnFragmentDataChangeListener) {
        listen = listener
    }


}


interface OnFragmentDataChangeListener{
    fun onFragmentDataChange(data: String)
}

