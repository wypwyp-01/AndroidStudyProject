package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyp.studyproject.databinding.DynamicFragmentLayout1Binding
import com.wyp.studyproject.databinding.DynamicFragmentLayout2Binding
import com.wyp.studyproject.databinding.FragmentTestActivityLayoutBinding
import com.wyp.studyproject.util.L

class DynamicFragment2: Fragment() {

    lateinit var binding: DynamicFragmentLayout2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DynamicFragmentLayout2Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}