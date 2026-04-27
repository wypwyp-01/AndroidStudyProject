package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyp.studyproject.databinding.DynamicFragmentLayout1Binding
import com.wyp.studyproject.databinding.FragmentTestActivityLayoutBinding
import com.wyp.studyproject.util.L

class DynamicFragment1: Fragment() {

    lateinit var binding: DynamicFragmentLayout1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        L.d("DynamicFragment1 onCreateView","testHide")
        binding = DynamicFragmentLayout1Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        L.d("DynamicFragment1 onViewCreated","testHide")
        val str = arguments?.getString("test")
        L.d("test = $str")
        binding.textBundle.text = str


        binding.dynamicFragment1.setOnClickListener {
            L.showToast(requireActivity(),"点击了 dynamic_fragment1")
        }







    }

    override fun onDestroy() {
        super.onDestroy()
        L.d("DynamicFragment1 onDestroy","testHide")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        L.d("DynamicFragment1 onDestroyView","testHide")

    }


}