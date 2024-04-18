package com.route.to_do.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.route.to_do.R
import com.route.to_do.databinding.ActivityMainBinding
import com.route.to_do.fragments.TaskFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
    }

    private fun initFragment() {
        binding.content.btnNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.task_nav -> { pushFragment(TaskFragment()) }
            }
            true
        }
        binding.content.btnNav.selectedItemId = R.id.task_nav
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment).commit()
    }
}