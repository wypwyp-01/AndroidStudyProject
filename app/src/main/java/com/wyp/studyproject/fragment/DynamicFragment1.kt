package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyp.studyproject.databinding.DynamicFragmentLayout1Binding
import com.wyp.studyproject.databinding.FragmentTestActivityLayoutBinding

class DynamicFragment1: Fragment() {

    lateinit var binding: DynamicFragmentLayout1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DynamicFragmentLayout1Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}