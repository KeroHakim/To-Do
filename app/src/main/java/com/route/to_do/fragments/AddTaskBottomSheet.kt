package com.route.to_do.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.to_do.OnTaskAddedListener
import com.route.to_do.R
import com.route.to_do.database.TaskDatabase
import com.route.to_do.database.models.Task
import com.route.to_do.databinding.SheetBottomTaskAddBinding
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    lateinit var viewBinding: SheetBottomTaskAddBinding
    lateinit var calender: Calendar
    var onTaskAddedListener : OnTaskAddedListener? = null
    var onTaskAddedListener2: OnTaskAddedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = SheetBottomTaskAddBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calender = Calendar.getInstance()
        viewBinding.selectTimeTv.setOnClickListener {
            val picker =
                TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calender.set(Calendar.MINUTE, minute)
                        val formattedHour: Int
                        val period: String
                        if (hourOfDay in 0..11) {
                            formattedHour = if (hourOfDay == 0) 12 else hourOfDay
                            period = "AM"
                        } else {
                            formattedHour = if (hourOfDay == 12) 12 else hourOfDay - 12
                            period = "PM"
                        }

                        val formattedMinute = String.format("%02d", minute)

                        viewBinding.selectTimeTv.text = "$formattedHour : $formattedMinute $period"
                    }

                }, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), false)
            picker.show()
        }
        viewBinding.selectDateText.setOnClickListener {
            val picker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calender.set(Calendar.YEAR, year)
                    calender.set(Calendar.MONTH, month)
                    calender.set(Calendar.DAY_OF_MONTH , dayOfMonth)
                    viewBinding.selectDateText.text = "$dayOfMonth / ${month + 1} / $year"
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )
            picker.datePicker.minDate = System.currentTimeMillis()
            picker.show()
        }
        Log.d(TAG, "Message")
        viewBinding.saveBtn.setOnClickListener {
            if (validateFields()) {
                calender.clearTime()
                val task = Task(title = viewBinding.editTextTask.text.toString(),
                    description = viewBinding.editTextDescription.text.toString(),
                    date = calender.time,
                    isDone = false
                )
                TaskDatabase.getInstance(requireContext()).getTaskDao().insertTask(task)
                onTaskAddedListener?.onTaskAdded()
                onTaskAddedListener2?.onTaskAdded()
                dismiss()
            }
        }
    }

    private fun validateFields(): Boolean {
        if (viewBinding.editTextTask.text?.isEmpty() == true || viewBinding.editTextTask.text?.isBlank() == true) {
            viewBinding.editTextLayoutTask.error = "Required"
            return false
        } else {
            viewBinding.title.error = null
        }
        if (viewBinding.editTextDescription.text?.isEmpty() == true || viewBinding.editTextDescription.text?.isBlank() == true) {
            viewBinding.editTextLayoutDescription.error = "Required"
            return false
        } else {
            viewBinding.title.error = null
        }
        if (viewBinding.selectDateText.text == getString(R.string.select_date)) {
            viewBinding.selectDateLayout.error = "required"
            return false
        }
        else viewBinding.selectDateLayout.error = null

        if (viewBinding.selectTimeTv.text == getString(R.string.select_time)) {
            viewBinding.selectTimeTv.error = "required"
            return false
        }
        else viewBinding.selectTimeLayout.error = null
        return true
    }
}