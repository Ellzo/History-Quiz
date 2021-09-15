package com.ellzo.historyquiz

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerFragment(private val day: Int, private val month: Int, private val year: Int) :
    DialogFragment(),
    OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val activity = activity as QuizActivity?
        activity?.setTvDate(dayOfMonth, month + 1, year)
    }
}