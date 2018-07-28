package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.button;
import static android.R.attr.defaultHeight;
import static android.R.attr.start;
import static com.example.roushan.mnitflightbookiing.R.attr.title;

public class ShowSearch extends Activity {

    DatebaseHelper datebaseHelper;
    String f;
    String d;
    String dep;
    String clas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);
     //   datebaseHelper=new DatebaseHelper(this);
        init();
    }

    public void init() {

        datebaseHelper=new DatebaseHelper(this);

        Intent intent = getIntent();
        if(intent!=null) {
            f=intent.getStringExtra("from");
            d=intent.getStringExtra("des");
            // dep=intent.getStringExtra("depart");
            clas=intent.getStringExtra("clas");


        }

        final Cursor cursor= datebaseHelper.getData(f,d,clas);
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" DEPARTURE");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" PRICE ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" COMPANY");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        int i=1;
        while(cursor.moveToNext())
         {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(cursor.getString(3));
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(cursor.getString(8));
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(cursor.getString(5));
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
             Button button=new Button(this);
             button.setText("Book");
             button.setId(cursor.getInt(0));
             int id_ = button.getId();
            //button.setOnClickListener(this);
             button.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View view) {
                 for(int j=1;j<100;j++)
                           {
                               if(view.getId()==j)
                                {







                                    Intent intent1=new Intent(ShowSearch.this,BookFlight.class);
                                    intent1.putExtra("id",j);
                                    startActivity(intent1);
                                    break;
                                }
                           }
                 }
             });


             button.setGravity(Gravity.CENTER);
             tbrow.addView(button);

            stk.addView(tbrow);
             i++;
             TableRow blank=new TableRow(this);
             TextView s1v = new TextView(this);
             s1v.setText("   ");
             s1v.setTextColor(Color.WHITE);
             s1v.setGravity(Gravity.CENTER);
             blank.addView(s1v);
             TextView s2v = new TextView(this);
             s2v.setText("    ");
             s2v.setTextColor(Color.WHITE);
             s2v.setGravity(Gravity.CENTER);
             blank.addView(s2v);
             TextView s3v = new TextView(this);
             s3v.setText("    ");
             s3v.setTextColor(Color.WHITE);
             s3v.setGravity(Gravity.CENTER);
             blank.addView(s3v);
             TextView s4v = new TextView(this);
             s4v.setText("     ");
             s4v.setTextColor(Color.WHITE);
             s4v.setGravity(Gravity.CENTER);
             blank.addView(s4v);
             stk.addView(blank);


        }

    }



}
