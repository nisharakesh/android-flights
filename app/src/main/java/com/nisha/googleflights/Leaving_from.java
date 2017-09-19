package com.nisha.googleflights;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Leaving_from extends AppCompatActivity {

    EditText edsub;
    Button btnsub;
    Button btnvw;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Lev = "lvKey";
    String [] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;


    private SharedPreferences prefsPrivate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaving_from);
        edsub = (EditText) findViewById(R.id.lev_plc);
        btnsub = (Button) findViewById(R.id.btn1);
        btnvw = (Button) findViewById(R.id.btn2);
        listView=(ListView)findViewById(R.id.lstvw);


        sharedpreferences   = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Lev)) {
            edsub.setText(sharedpreferences.getString(Lev, ""));
        }
        initList();
        edsub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if (s.toString().equals("")){

                 }
                 else
                 {
                     searchItem(s.toString());
                 }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }


    public  void searchItem( String textToSearch){

        for (String item:items){

            if ((!item.contains(textToSearch))){
                listItems.remove(item);
            }

        }


          adapter.notifyDataSetChanged();

    }

    public void Savee(View view) {
        String n = edsub.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Lev, n);
        editor.commit();
        Intent in=new Intent(Leaving_from.this,Search_Flights.class);
        startActivity(in);
    }
    public void clearr(View view) {
        edsub = (EditText) findViewById(R.id.lev_plc);

        edsub.setText("");




    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public  void  initList(){

        items=new  String[]{"vet","uuu"};
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter =new ArrayAdapter<String>(Leaving_from.this,R.layout.list_item,R.id.txt_vw,listItems);
        listView.setAdapter(adapter);
    }

}

