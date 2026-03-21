package com.wyp.studyproject

import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wyp.studyproject.databinding.ActivityMainBinding
import fragment.SharedPreferenceFragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding .inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonSharedpreference.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.aharedpreferenceFragmentContainer.id, SharedPreferenceFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
        binding.buttonSqlite.setOnClickListener {
            Toast.makeText(this,"view bind long test",Toast.LENGTH_LONG).show()
        }
    }
}