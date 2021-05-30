@file:JvmName("BindingUtils")
package com.company.inventory_manager.util

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import com.company.inventory_manager.modell.ProductStatusType
import java.text.SimpleDateFormat
import java.util.*


fun dateToString(value: Date?): String? {
    if(value == null){
        return "";
    }
    return SimpleDateFormat("yyyy.MM.dd.").format(value);
}

fun stringToDate(value: String?): Date? {
    if(value == null){
        return null;
    }
    try {
        return SimpleDateFormat("yyyy.MM.dd.").parse(value);
    }catch ( e:Exception){

    }
    return null;
}

fun boolToString(value: Boolean): String{
    return if(value){
        "Yes";
    } else{
        "No";
    }
}

fun statusToString(value: ProductStatusType?): String{
    if(value == null){
        return "";
    }
    return value.toString();
}

@InverseMethod("positionToStatus")
fun statusToPosition(gender: ProductStatusType?): Int {
    return gender?.ordinal ?: 0
}

fun positionToStatus(position: Int): ProductStatusType {
    return ProductStatusType.values()[position]
}

