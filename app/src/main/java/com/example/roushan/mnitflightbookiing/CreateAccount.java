package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {
    Button button;
    DatebaseHelper datebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setTitle("Sign Up");

        //DATABASE CALLING
        datebaseHelper=new DatebaseHelper(this);
        //for back button on title
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        button=(Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccount.this,SignUp.class));
            }
        });

    }

    public void process(View view)
              {
                  if(view.getId()==R.id.textView37)
                     {
                         startActivity(new Intent(CreateAccount.this,LogIn.class));
                     }
                  else if(view.getId()==R.id.textView44)
                     {
                         startActivity(new Intent(CreateAccount.this,TermsAndCondition.class));
                     }
                  else if(view.getId()==R.id.textView45)
                     {
                         startActivity(new Intent(CreateAccount.this,Privacy.class));
                     }
              }

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
