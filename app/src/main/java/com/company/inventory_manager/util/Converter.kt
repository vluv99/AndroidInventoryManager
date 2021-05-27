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


object Converter {

    @JvmStatic fun dateToString(value: Date?): String? {
        if(value == null){
            return "";
        }
        return SimpleDateFormat("yyyy.MM.dd.").format(value);
    }

    @JvmStatic fun stringToDate(value: String?): Date? {
        if(value == null){
            return null;
        }
        try {
            return SimpleDateFormat("yyyy.MM.dd.").parse(value);
        }catch ( e:Exception){

        }
        return null;
    }

    @JvmStatic fun boolToString(value: Boolean): String{
        return if(value){
            "Yes";
        } else{
            "No";
        }
    }

    @JvmStatic fun statusToString(value: ProductStatusType?): String{
        if(value == null){
            return "";
        }
        return value.toString();
    }
}
