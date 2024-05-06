package com.route.to_do.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.to_do.database.models.Task
import com.route.to_do.databinding.TaskItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter( private var task: List<Task>?) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
     class TaskViewHolder( var viewBinding: TaskItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind (task : Task){
            viewBinding.taskTitle.text = task.title
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val newDate = simpleDateFormat.format(task.date!!)
            viewBinding.taskTime.text = newDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return task?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val view = task?.get(position) ?: return
        holder.bind(view)
    }
    fun showData(task: List<Task>?){
        this.task = task
        notifyDataSetChanged()
    }
}