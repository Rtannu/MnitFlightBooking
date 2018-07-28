package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BookFlight extends AppCompatActivity {
    int s_no;
    DatebaseHelper datebaseHelper;
    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

        datebaseHelper =new DatebaseHelper(this);
        Intent intent = getIntent();
        if(intent!=null) {


            s_no = intent.getIntExtra("id", 0);


                StringBuffer buffer = new StringBuffer();
               Cursor cursor = datebaseHelper.book(s_no);
               while (cursor.moveToNext()) {
                 buffer.append("SN_NO:-" + s_no + "\n");
                 buffer.append("SOURCE:- " + cursor.getString(1) + "\n");
                 buffer.append("DESTINATION:- " + cursor.getString(2) + "\n");
                 buffer.append("DEPARTURE:- " + cursor.getString(3) + "\n");
                 buffer.append("CLASS:- " + cursor.getString(4) + "\n");
                 buffer.append("COMPANY:-" + cursor.getString(5) + "\n");
                 buffer.append("PLACE:-" + cursor.getString(6) + "\n");
                 buffer.append("SEAT:-" + cursor.getString(7) + "\n");
                 buffer.append("PRICE:- " + cursor.getString(8) + "\n");
            }

            alrt("booking", buffer.toString());


        }
    }
    public  void alrt(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(BookFlight.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
