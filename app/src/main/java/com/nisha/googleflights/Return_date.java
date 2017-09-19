package com.nisha.googleflights;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

public class Return_date extends AppCompatActivity {
    String dte;
    private int yr, mnth, day;
    EditText ed_rtn;
    Button bt_rtn;
    Button bt_rtnn;
    SharedPreferences sp1;
    public static final String mypreference2 = "mypref2";
    public static final String Retn = "rtKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_date);
        ed_rtn=(EditText)findViewById(R.id.rtn_dte);
        bt_rtn=(Button)findViewById(R.id.btn_rtns);
        bt_rtnn=(Button)findViewById(R.id.btn_rtnc);


        sp1   = getSharedPreferences(mypreference2,
                Context.MODE_PRIVATE);
        if (sp1.contains(Retn)) {
            ed_rtn.setText(sp1.getString(Retn, ""));
        }


        ed_rtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sss(111).show();
            }
        });
        dte=  ed_rtn.getText().toString();



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
            ed_rtn.setText(dte);
        }
    };



    public void SaveReturn(View view) {
        String g = ed_rtn.getText().toString();
        SharedPreferences.Editor editor = sp1.edit();
        editor.putString(Retn, g);
        editor.commit();
        Intent in=new Intent(Return_date.this,Search_Flights.class);
        startActivity(in);
    }
    public void ClearReturn(View view) {
        ed_rtn = (EditText) findViewById(R.id.rtn_dte);

        ed_rtn.setText("");




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    }

