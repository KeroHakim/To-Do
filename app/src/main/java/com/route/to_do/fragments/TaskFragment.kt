package com.route.to_do.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.to_do.Adapters.TaskAdapter
import com.route.to_do.database.TaskDatabase
import com.route.to_do.databinding.FragmentTaskBinding
import java.util.Calendar

class TaskFragment : Fragment(){
    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskAdapter : TaskAdapter
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
    }

    private fun initRv() {
        taskAdapter = TaskAdapter(null)
        calendar = Calendar.getInstance()
        binding.taskRv.adapter = taskAdapter
        binding.calenderView.setOnDateChangedListener { widget, date, selected ->
            val year = date.year
            val month = date.month - 1
            val dayOfMonth = date.day
            calendar.clearTime()
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            backTask()
        }
        backTask2()
    }
    fun  backTask(){
        val updateList = TaskDatabase.getInstance(requireContext()).getTaskDao().getTasksByDate(calendar.time)
        taskAdapter.showData(updateList)
    }
    fun backTask2(){
        val list = TaskDatabase.getInstance(requireContext()).getTaskDao().getAllTask()
        taskAdapter.showData(list)
    }
}