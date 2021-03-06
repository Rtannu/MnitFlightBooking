package com.example.roushan.mnitflightbookiing;

/**
 * Created by ROUSHAN on 10-04-2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by sarwajeet on 9/4/17.
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    EditText txtDate;
    public DateDialog(View view)
    {
        txtDate=(EditText)view;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c= Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,year,month,day);


    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        String date=day+"-"+(month+1)+"-"+year;
        txtDate.setText(date);
    }
}

