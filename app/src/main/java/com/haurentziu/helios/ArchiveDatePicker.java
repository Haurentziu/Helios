package com.haurentziu.helios;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by haurentziu on 07.09.2016.
 */
public class ArchiveDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Button dateButton;
    private int dayOfMonth, month, year;


    public ArchiveDatePicker(){
        setCurrentDate();
    }

    public void setCurrentDate(){
        final Date date = new Date();
        month = date.getMonth();
        dayOfMonth = Integer.parseInt((String) android.text.format.DateFormat.format("dd", date));
        year = date.getYear() + 1900;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
        return dialog;

    }


    public void setDateButton(Button dateButton){
        this.dateButton = dateButton;
    }

    public void updateButtonDate(){
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
        dateButton.setText(dateFormat.format(new Date(year - 1900, month, dayOfMonth)));

    }

    public int getDayOfMonth(){
        return dayOfMonth;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        updateButtonDate();
    }
}
