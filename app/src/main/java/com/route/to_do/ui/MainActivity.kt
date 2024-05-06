package com.route.to_do.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.to_do.OnTaskAddedListener
import com.route.to_do.R
import com.route.to_do.databinding.ActivityMainBinding
import com.route.to_do.fragments.AddTaskBottomSheet
import com.route.to_do.fragments.SettingsFragment
import com.route.to_do.fragments.TaskFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var taskListFragment: TaskFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addFloatingActionButton.setOnClickListener {
            val addTaskBottomSheet = AddTaskBottomSheet()
            addTaskBottomSheet.onTaskAddedListener = object : OnTaskAddedListener{
                override fun onTaskAdded() {
                    taskListFragment.backTask()
                    taskListFragment.backTask2()
                }
            }
            addTaskBottomSheet.show(supportFragmentManager, null)
        }
        chooseFragment()
    }

    private fun chooseFragment() {
        binding.btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.task_nav -> {
                    taskListFragment = TaskFragment()
                    pushFragment(taskListFragment)
                    binding.appBarTitle.text="Tasks List"
                }
                R.id.setting_nav -> {
                    pushFragment(SettingsFragment())
                    binding.appBarTitle.text="Setting"
                }

            }
            true
        }
        binding.btnNav.selectedItemId = R.id.task_nav
    }


    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragment_container,fragment).commit()
    }
}