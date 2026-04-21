package com.wyp.studyproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyp.studyproject.databinding.SharedpreferenceTestBinding
import com.wyp.studyproject.databinding.StaticFragmentLayout1Binding
import android.widget.RatingBar
import com.wyp.studyproject.util.L

class StaticFragment1: Fragment() {

    lateinit var binding: StaticFragmentLayout1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StaticFragmentLayout1Binding .inflate(layoutInflater)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.radiogroupLikeOrDislike.setOnCheckedChangeListener { group, i ->
            var text = binding.textLikeOrDislike.text.toString()
            if (binding.radiobuttonLike.isChecked) {
                text = "喜欢"
                binding.textLikeOrDislike.text = text
            } else if (binding.radiobuttonDislike.isChecked) {
                text = "不喜欢"
                binding.textLikeOrDislike.text = text
            }
        }

        binding.ratingbarScore.onRatingBarChangeListener = object: RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(
                ratingBar: RatingBar?,
                rating: Float,
                fromUser: Boolean
            ) {
                if (fromUser) L.showToast(requireActivity(),"float = $rating")
            }
        }





    }



}