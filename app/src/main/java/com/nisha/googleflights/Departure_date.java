package com.nisha.googleflights;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Departure_date extends AppCompatActivity {
    String dte;
    private int yr, mnth, day;
    EditText edDate;
    Button btdtes;
    Button btdtec;
    private int mYear, mMonth, mDay, mHour, mMinute;
    SharedPreferences sp;
    public static final String mypreferencess = "mypref1";
    public static final String Dept = "dpKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure_date);
        edDate=(EditText)findViewById(R.id.dep_dte);
        btdtes=(Button)findViewById(R.id.btndptc);
        btdtec=(Button)findViewById(R.id.btndpts);


        sp   = getSharedPreferences(mypreferencess,
                Context.MODE_PRIVATE);
        if (sp.contains(Dept)) {
            edDate.setText(sp.getString(Dept, ""));
        }


        edDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               sss(111).show();
             }
            });
            dte=  edDate.getText().toString();



        }
    protected Dialog sss(int id)
    {
        if (id == 111)
        {
            return new DatePickerDialog(this, pickerlistener, yr, mnth, day);
        }

        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerlistener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmoth, int selectedday)
        {
            yr = selectedyear;
            mnth = selectedmoth;
            day = selectedday;

            dte=String.valueOf(day)+"/"+mnth+"/"+yr;
            edDate.setText(dte);
        }
    };



    public void SaveDate(View view) {
        String f = edDate.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Dept, f);
        editor.commit();
        Intent in=new Intent(Departure_date.this,Search_Flights.class);
        startActivity(in);
    }
    public void ClearDate(View view) {
        edDate = (EditText) findViewById(R.id.dep_dte);

        edDate.setText("");




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}




