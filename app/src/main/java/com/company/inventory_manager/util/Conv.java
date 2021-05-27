/*
package com.company.inventory_manager.util;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.InverseMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conv {
    @InverseMethod("stringToDate")
    public static String dateToString(Date value) {
        if(value == null){
            return "";
        }
        return new SimpleDateFormat("yyyy.MM.dd.").format(value);
    }

    public static Date stringToDate(String ordinal) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd.").parse(ordinal);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return null;
    }
}

*/
