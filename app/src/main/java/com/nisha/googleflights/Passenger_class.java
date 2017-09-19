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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.checked;

public class Passenger_class extends AppCompatActivity {



   // AutoCompleteTextView  edpasscls;
    TextView txt;
    Button dne;
    RadioGroup radgrp;
    //String[] languages={"Economy ","Premium Economy","Business","First"};
    //Button btnpasscls;
    //Button btnpassclr;
    SharedPreferences sp4;
    public static final String mypreference4 = "mypref4";
    public static final String Cls = "pclKey";
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_class);
        txt = (TextView) findViewById(R.id.text_rad);
        radgrp = (RadioGroup) findViewById(R.id.rad_grp);
        dne=(Button)findViewById(R.id.done);
        //btnpassclr = (Button) findViewById(R.id.btn_passclr);

        //ArrayAdapter adapter = new
               // ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);

       // edpasscls.setAdapter(adapter);
       // edpasscls.setThreshold(1);

        txt.setText("");

        radgrp .setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener(){
            @Override
            public  void  onCheckedChanged(RadioGroup radioGroup,int i){
                RadioButton rb=(RadioButton)radgrp.findViewById(i);
                switch (i){
                    case  R.id.spinner1:
                        txt.setText("Economy");
                        break;
                    case  R.id.spinner2:
                        txt.setText("Bussiness Class");
                        break;

                    case  R.id.spinner3:
                        txt.setText("Premium  Economy");
                        break;
                }
            }
        });

       // sp4 = getSharedPreferences(mypreference4,
               // Context.MODE_PRIVATE);
       // if (sp4.contains(Cls)) {
          //  txt.setText(sp4.getString(Cls, ""));
       // }


        dne.setOnClickListener(new View.OnClickListener() {
                                       @Override
          public void onClick(View v) {
//           Toast.makeText(getApplicationContext(), txt.getText().toString(), Toast.LENGTH_SHORT).show();
          //Intent in = new Intent(Passenger_class.this, Search_Flights.class);
            //  in.putExtra("name",txt.getText().toString());
           //startActivity(in);
                                           //String i = txt.getText().toString();
                                          // SharedPreferences.Editor editor = sp4.edit();
                                          // editor.putString(Cls, i);
                                         //  editor.commit();





                                           Intent intent = new Intent(Passenger_class.this, Search_Flights.class);
                                           //Create a bundle object
                                           Bundle b = new Bundle();

                                           //Inserts a String value into the mapping of this Bundle
                                           b.putString("name", txt.getText().toString());

                                           //Add the bundle to the intent.
                                           intent.putExtras(b);

                                           //start the DisplayActivity
                                           startActivity(intent);












                                       }
            });

    //public void SaveClass(View view) {
        //String i = edpasscls.getText().toString();
        //SharedPreferences.Editor editor = sp4.edit();
        //editor.putString(Cls, i);
        //editor.commit();
        //Intent in = new Intent(Passenger_class.this, Search_Flights.class);
        //startActivity(in);
    //}

    //public void ClearClass(View view) {
      //  edpasscls = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

//        edpasscls.setText("");
    }
}
