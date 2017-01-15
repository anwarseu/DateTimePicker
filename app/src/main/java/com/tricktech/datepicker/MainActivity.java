package com.tricktech.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static Button btnDatePicker,btnTimePicker;
    public static final String DATE_PICKER = "date_picker";
    public static final String TIME_PICKER = "time_picker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

       btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();

                dialogFragment.show(getSupportFragmentManager(), DATE_PICKER);
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new TimePickerFragment();

                dialogFragment.show(getSupportFragmentManager(), TIME_PICKER);
            }
        });

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),this,year,month,day);

            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

            long twoDays = 1000*60*60*24*2;

            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis()+twoDays);

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            btnDatePicker.setText(year+"-"+(month+1)+"-"+day);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            int hours = c.get(Calendar.HOUR);
            int minute = c.get(Calendar.MINUTE);
            boolean is24Hours = true;


            return new TimePickerDialog(getActivity(),this,hours,minute,is24Hours);
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int menute) {
            btnTimePicker.setText(hours+":"+menute);
        }
    }

}


