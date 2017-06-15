package com.example.stefano.happypark.activities.SignUpActivity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import com.example.stefano.happypark.callbacks.DatePickerCallback
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by stefano on 14/06/17.
 */
class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var callback:DatePickerCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = sdf.format(c.time)
        callback?.onDataSet(formattedDate)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is DatePickerCallback) {
            callback = context as DatePickerCallback?
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}