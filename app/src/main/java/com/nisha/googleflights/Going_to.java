package com.nisha.googleflights;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Going_to extends AppCompatActivity {

    EditText edgo;
    Button bt;
    Button btn;
    SharedPreferences sharedpreference;
    public static final String mypreferences = "mypreff";
    public static final String Go = "goKey";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_going_to);
        edgo = (EditText) findViewById(R.id.go_plc);
        bt = (Button) findViewById(R.id.btngo);
        btn = (Button) findViewById(R.id.btngn);





        sharedpreference   = getSharedPreferences(mypreferences,
                Context.MODE_PRIVATE);
        if (sharedpreference.contains(Go)) {
            edgo.setText(sharedpreference.getString(Go, ""));
        }
    }




    public void Sav(View view) {
        String e = edgo.getText().toString();
        SharedPreferences.Editor editor = sharedpreference.edit();
        editor.putString(Go, e);
        editor.commit();
        Intent in=new Intent(Going_to.this,Search_Flights.class);
        startActivity(in);
    }
    public void cle(View view) {
        edgo = (EditText) findViewById(R.id.go_plc);

        edgo.setText("");




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
