package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    TextView reset;
    Button login;
    EditText email;
    EditText password;
    DatebaseHelper datebaseHelper;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log in");
        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        sharedPreferences=getSharedPreferences("login", MODE_PRIVATE);


        //if SharedPreferences contains username and password then directly redirect to Home activity
        if(sharedPreferences.contains("email") && sharedPreferences.contains("password")){
            startActivity(new Intent(LogIn.this,Profile.class));
            finish();   //finish current activity
        }




        datebaseHelper=new DatebaseHelper(this);
        email=(EditText)findViewById(R.id.editText7);
        password=(EditText)findViewById(R.id.editText8);
        login=(Button)findViewById(R.id.button2) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=datebaseHelper.login(email.getText().toString(),password.getText().toString());
                if(result==0)
                    Toast.makeText(LogIn.this,"Incorrect Login details",Toast.LENGTH_SHORT).show();
                else {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("email",email.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.commit();
                    Toast.makeText(LogIn.this,"Login successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogIn.this,Profile.class));
                    finish();

                }
            }
        });


        reset=(TextView)findViewById(R.id.textView23);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,Reset.class));
            }
        });
    }
    public void signUp(View view)
          {
              startActivity(new Intent(LogIn.this,SignUp.class));
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
