package com.nisha.googleflights;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class Passenger extends AppCompatActivity {


    AutoCompleteTextView edpass;
    String[] languages={"1 Passenger ","2 Passenger","3 Passenger","4 Passenger","5 Passenger","6 Passenger","7 Passenger"};
    Button btnpas;
    Button btnpasc;
    SharedPreferences sp3;
    public static final String mypreference3 = "mypref3";
    public static final String Pasnger = "pnKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        edpass = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        btnpas = (Button) findViewById(R.id.btn_pass);
        btnpasc = (Button) findViewById(R.id.btn_passc);


        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);

        edpass.setAdapter(adapter);
        edpass.setThreshold(1);


        sp3 = getSharedPreferences(mypreference3,
                Context.MODE_PRIVATE);
        if (sp3.contains(Pasnger)) {
            edpass.setText(sp3.getString(Pasnger, ""));
        }
    }

    public void SavePassenger(View view) {
        String h = edpass.getText().toString();
        SharedPreferences.Editor editor = sp3.edit();
        editor.putString(Pasnger, h);
        editor.commit();
        Intent in = new Intent(Passenger.this, Search_Flights.class);
        startActivity(in);
    }

    public void ClearPassenger(View view) {
        edpass = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        edpass.setText("");

    }
}
