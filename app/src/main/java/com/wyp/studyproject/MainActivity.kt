package com.wyp.studyproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wyp.studyproject.databinding.ActivityMainBinding
import fragment.StorageFragment
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
                replace(binding.aharedpreferenceFragmentContainer.id, StorageFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
        binding.buttonSqlite.setOnClickListener {
            Toast.makeText(this,"view bind long test",Toast.LENGTH_LONG).show()
        }
    }
}