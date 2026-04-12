package com.wyp.studyproject.fragment

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wyp.studyproject.data.SongDao
import com.wyp.studyproject.data.SongDatabase
import com.wyp.studyproject.data.SongDatabaseProvider
import com.wyp.studyproject.data.SongEntity
import com.wyp.studyproject.databinding.FourZujianBinding
import com.wyp.studyproject.databinding.SharedpreferenceTestBinding
import com.wyp.studyproject.util.MyThread
import com.wyp.studyproject.util.StandardReceiver.Companion.STANDARD_ACTION
import kotlinx.coroutines.launch

class FourZujianFragment: Fragment() {
    private lateinit var binding: FourZujianBinding

    val handler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                1->{
                    binding.textTest.text = msg.obj as String
                }
                else -> {

                }
            }
        }
    }






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FourZujianBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonMainThread.setOnClickListener {
            var str = ""
            for (i in (1..100)) {
                str += "字符串$i"
            }
            Thread.sleep(3000)
            binding.textTest.text = str
        }

        binding.buttonSonThread.setOnClickListener {
            val thread = MyThread(handler)

            thread.start()

        }




    }


}

