package com.example.roushan.mnitflightbookiing;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Airport extends AppCompatActivity {
    EditText airport;
    TextView textView;
    EditText city;
    EditText citySearch;
    Button insert,search;
    DatebaseHelper datebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);
        setTitle("Airport Of India");
        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        datebaseHelper =new DatebaseHelper(this);
        textView=(TextView)findViewById(R.id.textView);
        airport=(EditText)findViewById(R.id.editText10);
        city=(EditText)findViewById(R.id.editText11);
        citySearch=(EditText)findViewById(R.id.editText12);
        insert=(Button)findViewById(R.id.button7);
        search=(Button)findViewById(R.id.button8);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result=datebaseHelper.insertAirport(airport.getText().toString(),city.getText().toString());
                if(result)
                    Toast.makeText(Airport.this,"inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Airport.this,"not inserted",Toast.LENGTH_LONG).show();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=datebaseHelper.searchAirpot(citySearch.getText().toString());

                StringBuffer buffer=new StringBuffer();

                while(cursor.moveToNext())
                {
                    buffer.append("AIRPORT:- "+cursor.getString(1)+"\n");
                    buffer.append("CITY:- "+cursor.getString(2)+"\n");


                }

                alert("AIRPORTS",buffer.toString());
            }
        });





    }

    public  void alert(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Airport.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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
