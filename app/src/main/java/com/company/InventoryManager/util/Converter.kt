package com.company.inventoryManager.util

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import java.text.SimpleDateFormat
import java.util.*
/*
@BindingAdapter("android:text")
fun setDateText(view: TextView, date: Date?) {
    if (date != null) {
        view.setText(SimpleDateFormat("yyyy.MM.dd.").format(date));
    } else{
        view.setText("");
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getTime(view: TextView) : Date? {
    return SimpleDateFormat("yyyy.MM.dd.").parse(view.text.toString())
}*/

object Converter {
    @InverseMethod("stringToDate")
    @JvmStatic fun dateToString(
        view: EditText, oldValue: Date,
        value: Date
    ): String {
        // Converts long to String.
        return SimpleDateFormat("yyyy.MM.dd.").format(value);
    }

    @JvmStatic fun stringToDate(
        view: EditText, oldValue: String,
        value: String
    ): Date {
        // Converts String to long.
        return SimpleDateFormat("yyyy.MM.dd.").parse(view.text.toString())
    }
}
