package com.nisha.googleflights;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.reginald.editspinner.EditSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



public class Search_Flights extends AppCompatActivity {
    SharedPreferences sharedpreferences, sharedpreference, sp, sp1, sp3, sp4;//For saving in shared preferences
    public static final String mypreference = "mypref";
    public static final String Lev = "lvKey";
    public static final String mypreferences = "mypreff";
    public static final String Go = "goKey";
    public static final String mypreferencess = "mypref1";
    public static final String Dept = "dpKey";
    public static final String mypreference2 = "mypref2";
    public static final String Retn = "rtKey";
    public static final String mypreference3 = "mypref3";
    public static final String Pasnger = "pnKey";
    public static final String mypreference4 = "mypref4";
    public static final String Cls = "pclKey";


    String userIs;
    RadioGroup radiogroup;
    RadioButton rad1, rad2;
    String selectedSuperStar;
    Spinner spinner1,spinner;
    private Calendar calendar;
    private int year, month, day, yea, mont, da;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
     private  DatabaseReference mHeadingRefence=mRootReference.child("head");
    DatabaseReference databaseFlights = FirebaseDatabase.getInstance().getReference("head");


    AutoCompleteTextView lev_frm;
    AutoCompleteTextView gng_to;
    EditText dpt_date;
    EditText rtn_date;
    TextView pasngr;
    TextView txt;
    TextView t;
    TextView psngr_cls;
    Button srch, clr, vw;
    CheckBox bt1, bt2;
     String l;
    public String name;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__flights);
        lev_frm = (AutoCompleteTextView) findViewById(R.id.ed_lvfrm);
        gng_to = (AutoCompleteTextView) findViewById(R.id.ed_gng);
        dpt_date = (EditText) findViewById(R.id.ed_dept);
        rtn_date = (EditText) findViewById(R.id.ed_rtn);
        pasngr = (TextView) findViewById(R.id.ed_pngr);
      //psngr_cls = (TextView) findViewById(R.id.ed_cls);
        srch = (Button) findViewById(R.id.btnsrch);
        clr = (Button) findViewById(R.id.btnclr);
        vw = (Button) findViewById(R.id.btnview);
        spinner=(Spinner)findViewById(R.id.spinner2);
        rad1 = (RadioButton) findViewById(R.id.radioOne);
        rad2 = (RadioButton) findViewById(R.id.radioRound);
        TextView tv;

        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select an item...",
                "Economy",
                "Buisness   Class",
                "Premium Economy "

        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                sp4 = getSharedPreferences(mypreference4,Context.MODE_PRIVATE);
                TextView tv = (TextView) view;
                tv.setText(selectedItemText);
                 tv.setText(sp4.getString(Cls, ""));
                SharedPreferences.Editor editor4 = sp3.edit();
                editor4.putString(Cls, "");
                editor4.commit();
                // If user change the default selection
                // First item is disable and it is used for hint
               // if(position > 0){
                    // Notify the selected item text
                   // Toast.makeText
                      //      (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                      //      .show();

              //  }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        dpt_date.setText("");


        yea = calendar.get(Calendar.YEAR);
        mont = calendar.get(Calendar.MONTH);
        da = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(yea, mont + 1, da);
        rtn_date.setText("");


        String[] names = {"Agartala [IX]", " Agatti  Island [AGX]","Agra [AGR]", "Ahmedabad [AMD]", "Aizawl [AJL]", "AKOLA [AKD]", "ALLAHABAD [IXD]", "AMRITSAR[ATQ]", "AURANGABAD [IXU]", "BAGDOGRA [IXB]", "BANGLORE [BLR]", "BATHINDA [BUP]", "BELGAUM [IXG]", "BELLARY [BEP]", "BHAVNAGAR [BHU]", "BHUJ [BHJ]", "BIKANER [BKB]", "BILASPUR [PAB]", "CAR NICOBAR [CBD]", "CHANDIGRAH [IXC]", "CHENNAI [MAA]", "COCHIN [COK]","COIMBATORE [CJB]","COOCH BEHAR [COH]","CUDDAPAH [CDP]","DAMAN [NMB]","DEHRA DUN [DED]","DHARMASALA [DHM]","DIBRUGARH [DIR]","DIMAPUR [DMU]","DIU [DIU]","DURGAPUR [RDP]","GAYA [GAY]","GFDG [AAA]","GOA [GOI]","GORAKHPUR [GOP]","GUWAHATI [GAU]","GWALIOR [GAU]","GWALIOR [GWL]","GWALIOR [GWL]","HUBLI [HBX]",
                "IMPHALL [IMF]", "INDORE [IDR]", "JABALPUR [JLR]", "JAGDALPUR [JGB]", "JAIPUR [JAI]", "JAISALMER [JSA]", "JAMMU [IXJ]", "JAMNAGAR [JGA]", "JAMSHEDPUR [IXW]", "JEYPORE [PYB]", "JODHPUR [PYB]", "JORHAT [JRH]", "KAILASHAHAR [IXH]", "KAILASHAHAR [IHX]", "KANDLA [IXY]","KANPUR [KNU]"," KESHOD [IXK]"," KHAJURAHO [HJR]","   KOLHAPUR [KLH]"," KOLKATA [CCU]","KOTA [KTU]","KOZHIKODE [CCJ]","KULU [KUU]","LATUR [LTU]","LEH [IXL]","LILABARI [IXI]","LUCKNOW [LKO]","LUDHIANA [LUH]","MADURAI [IXM]","MALDA [LDA]","MANGLORE [IXE]",
               "MUMBAI [BOM]"," MYSORE [MYQ]"," NAGPUR [NAG]","NANDED [NDC]"," NASIK [ISK]"," NEW DELHI [DEL]","NEYVELI [NVY]","PANTNAGAR [PGH]"," PATHANKOT [IXP]","PATNA [PAT]","PONDICHERRY [PBD]","PORT BLAIR [IXZ]","PUNE(POONA)[PNQ]","PUTTAPARTHI [PUT]","RAIPUR [RPR]","RAJAHMUNDRY [RJA]","RAJKOT [RPR]","RAJOURI [RJI]","RANCHI [IXR]","REWA [REW]","ROURKELA [RRK]","SALEM [SXV]","SATNA [TNI]","SHILLONG [SHL]","SHIMLA [SLV]","SHOLAPUR [SSE]","SILCHAR [IXS]","SIMLA [SLV]","SRINAGAR [SXR]","SURAT [STV]","TEZPUR [TEZ]",
                "TEZU [TEI]", "THIRUVANANTHAPURAM [TRV]", "TIRUCHIRAPALLY [TRZ]","TIRUPATI [TIR]","TUTICORIN [TCR]", "UDAIPUR [UDR]", "VADODARA [BDQ]", "VARANASI [VNS]", "VIDAYNAGAR [VDY]", "VIJAYWADA [VGA]", "VISHAKHAPATNAM [VTZ]", "WARRANGAL [WGC]"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lev_frm.setThreshold(1);
        lev_frm.setAdapter(adapter);


        String[] names1 = {"Agartala [IX]", " Agatti  Island [AGX]","Agra [AGR]", "Ahmedabad [AMD]", "Aizawl [AJL]", "AKOLA [AKD]", "ALLAHABAD [IXD]", "AMRITSAR[ATQ]", "AURANGABAD [IXU]", "BAGDOGRA [IXB]", "BANGLORE [BLR]", "BATHINDA [BUP]", "BELGAUM [IXG]", "BELLARY [BEP]", "BHAVNAGAR [BHU]", "BHUJ [BHJ]", "BIKANER [BKB]", "BILASPUR [PAB]", "CAR NICOBAR [CBD]", "CHANDIGRAH [IXC]", "CHENNAI [MAA]", "COCHIN [COK]","COIMBATORE [CJB]","COOCH BEHAR [COH]","CUDDAPAH [CDP]","DAMAN [NMB]","DEHRA DUN [DED]","DHARMASALA [DHM]","DIBRUGARH [DIR]","DIMAPUR [DMU]","DIU [DIU]","DURGAPUR [RDP]","GAYA [GAY]","GFDG [AAA]","GOA [GOI]","GORAKHPUR [GOP]","GUWAHATI [GAU]","GWALIOR [GAU]","GWALIOR [GWL]","GWALIOR [GWL]","HUBLI [HBX]",
                "IMPHALL [IMF]", "INDORE [IDR]", "JABALPUR [JLR]", "JAGDALPUR [JGB]", "JAIPUR [JAI]", "JAISALMER [JSA]", "JAMMU [IXJ]", "JAMNAGAR [JGA]", "JAMSHEDPUR [IXW]", "JEYPORE [PYB]", "JODHPUR [PYB]", "JORHAT [JRH]", "KAILASHAHAR [IXH]", "KAILASHAHAR [IHX]", "KANDLA [IXY]","KANPUR [KNU]"," KESHOD [IXK]"," KHAJURAHO [HJR]","   KOLHAPUR [KLH]"," KOLKATA [CCU]","KOTA [KTU]","KOZHIKODE [CCJ]","KULU [KUU]","LATUR [LTU]","LEH [IXL]","LILABARI [IXI]","LUCKNOW [LKO]","LUDHIANA [LUH]","MADURAI [IXM]","MALDA [LDA]","MANGLORE [IXE]",
                "MUMBAI [BOM]"," MYSORE [MYQ]"," NAGPUR [NAG]","NANDED [NDC]"," NASIK [ISK]"," NEW DELHI [DEL]","NEYVELI [NVY]","PANTNAGAR [PGH]"," PATHANKOT [IXP]","PATNA [PAT]","PONDICHERRY [PBD]","PORT BLAIR [IXZ]","PUNE(POONA)[PNQ]","PUTTAPARTHI [PUT]","RAIPUR [RPR]","RAJAHMUNDRY [RJA]","RAJKOT [RPR]","RAJOURI [RJI]","RANCHI [IXR]","REWA [REW]","ROURKELA [RRK]","SALEM [SXV]","SATNA [TNI]","SHILLONG [SHL]","SHIMLA [SLV]","SHOLAPUR [SSE]","SILCHAR [IXS]","SIMLA [SLV]","SRINAGAR [SXR]","SURAT [STV]","TEZPUR [TEZ]",
                "TEZU [TEI]", "THIRUVANANTHAPURAM [TRV]", "TIRUCHIRAPALLY [TRZ]","TIRUPATI [TIR]","TUTICORIN [TCR]", "UDAIPUR [UDR]", "VADODARA [BDQ]", "VARANASI [VNS]", "VIDAYNAGAR [VDY]", "VIJAYWADA [VGA]", "VISHAKHAPATNAM [VTZ]", "WARRANGAL [WGC]"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names1);
        gng_to.setThreshold(0);
        gng_to.setAdapter(adapter1);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        lev_frm.setText(sharedpreferences.getString(Lev, ""));


        sharedpreference = getSharedPreferences(mypreferences,
                Context.MODE_PRIVATE);
        gng_to.setText(sharedpreference.getString(Go, ""));

        sp = getSharedPreferences(mypreferencess,
                Context.MODE_PRIVATE);
        dpt_date.setText(sp.getString(Dept, ""));

        sp1 = getSharedPreferences(mypreference2,
                Context.MODE_PRIVATE);
        rtn_date.setText(sp1.getString(Retn, ""));


        sp3 = getSharedPreferences(mypreference3,
                Context.MODE_PRIVATE);
        pasngr.setText(sp3.getString(Pasnger, ""));

        sp4 = getSharedPreferences(mypreference4,
               Context.MODE_PRIVATE);
      //  psngr_cls.setText(sp4.getString(Cls, ""));


        lev_frm.getText().clear();
        gng_to.getText().clear();
        dpt_date.getText().clear();
        rtn_date.getText().clear();
       // pasngr.setText().clear();
      // psngr_cls.getText().clear();

    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @SuppressWarnings("deprecation")
    public void setDate1(View view) {
        showDialog(99);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        } else {
            return new DatePickerDialog(this,
                    myDateListener1, yea, mont, da);
        }

    }


    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);

                }
            };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate1(arg1, arg2 + 1, arg3);

                }
            };


    private void showDate(int year, int month, int day) {
        dpt_date.setText(new StringBuilder().append(day).append("/")

                .append(month).append("/").append(year));
    }

    private void showDate1(int yea, int mont, int da) {
        rtn_date.setText(new StringBuilder().append(da).append("/")

                .append(mont).append("/").append(yea));

    }


    public void Save(View view) {

        if (rad1.isChecked()) {
            selectedSuperStar = rad1.getText().toString();
        } else if (rad2.isChecked()) {
            selectedSuperStar = rad2.getText().toString();
        }



        String n = lev_frm.getText().toString();
        String m = gng_to.getText().toString();
        String p = dpt_date.getText().toString();
        String q = rtn_date.getText().toString();
        String r = pasngr.getText().toString();
        //String s = psngr_cls.getText().toString();


        String flightlev = lev_frm.getText().toString().trim();
        String flightlgo = gng_to.getText().toString().trim();
        String flightdept = dpt_date.getText().toString().trim();
        String flightretn = rtn_date.getText().toString().trim();
        String flightpasn = pasngr.getText().toString().trim();
        String flightpasncls = psngr_cls.getText().toString().trim();
        String id = databaseFlights.push().getKey();
        Flights flights = new Flights(flightlev, flightlgo, flightdept, flightretn, flightpasn, flightpasncls, selectedSuperStar);
        databaseFlights.child(id).setValue(flights);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Lev, n);
        editor.commit();


        SharedPreferences.Editor editor1 = sharedpreference.edit();
        editor1.putString(Go, m);
        editor1.commit();


        SharedPreferences.Editor editor2 = sp.edit();
        editor2.putString(Dept, p);
        editor2.commit();


        SharedPreferences.Editor editor3 = sp1.edit();
        editor3.putString(Retn, q);
        editor3.commit();


        SharedPreferences.Editor editor4 = sp3.edit();
        editor4.putString(Pasnger, r);
        editor4.commit();



        SharedPreferences sharedPreferences =getSharedPreferences(mypreference4,
                Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor5 = sp4.edit();
        editor.putInt(Cls, 1);
        editor.commit();
    }
    public void clear(View view) {

         lev_frm = (AutoCompleteTextView) findViewById(R.id.ed_lvfrm);
        lev_frm.setText("");

            gng_to = (AutoCompleteTextView) findViewById(R.id.ed_gng);
            gng_to.setText("");


            dpt_date = (EditText) findViewById(R.id.ed_dept);
            dpt_date.setText("");


            rtn_date = (EditText) findViewById(R.id.ed_rtn);
            rtn_date.setText("");


            pasngr = (TextView) findViewById(R.id.ed_pngr);
            pasngr.setText("");

           // psngr_cls = (TextView) findViewById(R.id.ed_cls);
            //psngr_cls.setText("");

            spinner.setSelection(0);




        }
        public void Get (View view){
            lev_frm = (AutoCompleteTextView) findViewById(R.id.ed_lvfrm);
            sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

           if (sharedpreferences.contains(Lev)) {
               lev_frm.setText(sharedpreferences.getString(Lev, ""));
           }



             gng_to = (AutoCompleteTextView) findViewById(R.id.ed_gng);
            sharedpreference = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);

            if (sharedpreference.contains(Go)) {
             gng_to.setText(sharedpreference.getString(Go, ""));
             }



            dpt_date = (EditText) findViewById(R.id.ed_dept);
            sp = getSharedPreferences(mypreferencess, Context.MODE_PRIVATE);

            if (sp.contains(Dept)) {
                dpt_date.setText(sp.getString(Dept, ""));
            }


            rtn_date = (EditText) findViewById(R.id.ed_rtn);
            sp1 = getSharedPreferences(mypreference2, Context.MODE_PRIVATE);

            if (sp1.contains(Retn)) {
                rtn_date.setText(sp1.getString(Retn, ""));
            }


            pasngr = (TextView) findViewById(R.id.ed_pngr);
            sp3 = getSharedPreferences(mypreference3, Context.MODE_PRIVATE);

            if (sp3.contains(Pasnger)) {
                pasngr.setText(sp3.getString(Pasnger, ""));
            }


           // psngr_cls = (TextView) findViewById(R.id.ed_cls);
            sp4 = getSharedPreferences(mypreference4, Context.MODE_PRIVATE);

            if (sp4.contains(Cls)) {
               psngr_cls.setText(sp4.getString(Cls, ""));
            }
        }
}



























