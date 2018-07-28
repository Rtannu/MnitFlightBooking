package com.example.roushan.mnitflightbookiing;


import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static android.R.attr.name;


public class Flight_search extends AppCompatActivity {
    Spinner mySpinner;
    Spinner mySpinner1;
    Spinner mySpinner2;
    Button button, buttonInsert;
    DatebaseHelper datebaseHelper;
    EditText departure,comp,place,seat,price;
    TextView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);
        setTitle("Flight Search");


        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Spinner Dropdown Menu

        mySpinner = (Spinner) findViewById(R.id.spin1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Flight_search.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.source));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        mySpinner1 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Flight_search.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.destination));

        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);


        mySpinner2 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Flight_search.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.classes));

        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);
        //  onStart();
        search_flight();
        insertFlight();


    }

    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText) findViewById(R.id.editText3);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }


        });


    }


    public void search_flight() {

        button = (Button) findViewById(R.id.button1);
        mySpinner = (Spinner) findViewById(R.id.spin1);
        mySpinner1 = (Spinner) findViewById(R.id.spinner2);
        mySpinner2 = (Spinner) findViewById(R.id.spinner3);
        departure = (EditText) findViewById(R.id.editText3);
        comp=(EditText)findViewById(R.id.editText19);
        place=(EditText)findViewById(R.id.editText20);
        seat=(EditText)findViewById(R.id.editText21);
        price=(EditText)findViewById(R.id.editText22);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = mySpinner.getSelectedItem().toString();
                String des = mySpinner1.getSelectedItem().toString();
                //    String depart=departure.getText().toString();
                String clas = mySpinner2.getSelectedItem().toString();


                Intent intent = new Intent(Flight_search.this, ShowSearch.class);
                intent.putExtra("from", from);
                intent.putExtra("des", des);
                //intent.putExtra("depart",departure);
                intent.putExtra("clas", clas);
                intent.putExtra("comp", comp.getText().toString());
                intent.putExtra("place", place.getText().toString());
                intent.putExtra("seat", seat.getText().toString());
                intent.putExtra("price", price.getText().toString());

                startActivity(intent);
            }
        });

    }

    public void insertFlight() {
        datebaseHelper=new DatebaseHelper(this);
        buttonInsert = (Button) findViewById(R.id.button9);
        mySpinner = (Spinner) findViewById(R.id.spin1);
        mySpinner1 = (Spinner) findViewById(R.id.spinner2);
        mySpinner2 = (Spinner) findViewById(R.id.spinner3);
        departure = (EditText) findViewById(R.id.editText3);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = datebaseHelper.insertflight(mySpinner.getSelectedItem().toString(), mySpinner1.getSelectedItem().toString(),departure.getText().toString(), mySpinner2.getSelectedItem().toString(),comp.getText().toString(),place.getText().toString(),seat.getText().toString(),price.getText().toString());
                if (result)
                    Toast.makeText(Flight_search.this, "inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Flight_search.this, " not inserted", Toast.LENGTH_LONG).show();

            }
        });


    }



    //back button on action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}



