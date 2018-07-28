package com.example.roushan.mnitflightbookiing;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Status extends AppCompatActivity {
    DatebaseHelper datebaseHelper;
    EditText s_no;
    TextView status;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setTitle("Status");

        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        datebaseHelper=new DatebaseHelper(this);
        s_no=(EditText)findViewById(R.id.editText23);
        status=(TextView)findViewById(R.id.textView62);
        button=(Button)findViewById(R.id.button15);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=datebaseHelper.Status( Integer.parseInt(s_no.getText().toString()));

                while (cursor.moveToNext())
                  {
                      status.setText("Current position:- "+cursor.getString(6));
                 }
            ///  status.setText(s_no.getText().toString());
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
