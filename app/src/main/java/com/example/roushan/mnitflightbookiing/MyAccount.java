package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.roushan.mnitflightbookiing.R.drawable.a;

public class MyAccount extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button signin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        setTitle("My Account");
        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        sharedPreferences=getSharedPreferences("login", MODE_PRIVATE);


        //if SharedPreferences contains username and password then directly redirect to Home activity
        if(sharedPreferences.contains("email") && sharedPreferences.contains("password")){
            startActivity(new Intent(MyAccount.this,Profile.class));
            finish();   //finish current activity
        }

        signin=(Button)findViewById(R.id.button12);
        signup=(Button)findViewById(R.id.button13);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MyAccount.this,LogIn.class);
                startActivity(intent);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MyAccount.this,SignUp.class);
                startActivity(intent);

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
