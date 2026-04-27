package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyp.studyproject.R
import com.wyp.studyproject.databinding.FullscreenFragmentLayout1Binding
import com.wyp.studyproject.util.L

class FullScreenFragment: Fragment() {
    lateinit var binding: FullscreenFragmentLayout1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullscreenFragmentLayout1Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = parentFragmentManager

        binding.buttonRemoveFragment.setOnClickListener {
            val trs = manager.beginTransaction()
            val fragments = manager.findFragmentById(R.id.fullscreen_fragment1)
            L.d("找到了fragment，${fragments.toString()}")
            fragments?.let {
                trs.remove(fragments)
                trs.commit()
            }
        }


    }

}