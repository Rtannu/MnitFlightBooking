package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    Button logout;
    EditText name,email,password;
    TextView profile,Delete,Update;
    SharedPreferences sharedPreferences;
    DatebaseHelper datebaseHelper;
    int id;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("profile");
        //for database
        datebaseHelper=new DatebaseHelper(this);
        //for back button
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        profile=(TextView)findViewById(R.id.textView49);
        Delete=(TextView)findViewById(R.id.textView55);
        Update=(TextView)findViewById(R.id.textView57);
        name=(EditText)findViewById(R.id.editText13);
        email=(EditText)findViewById(R.id.editText15);
        password=(EditText)findViewById(R.id.editText14);

        //for profile
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        final Cursor cursor=datebaseHelper.getCustomerName(sharedPreferences.getString("email",""));
        cursor.moveToFirst();
        profile.setText("Hi,"+cursor.getString(1));
        name.setText(cursor.getString(1));
        email.setText(cursor.getString(2));
        password.setText(cursor.getString(3));
        id=cursor.getInt(0);

        //delete
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=datebaseHelper.AccountDelete(cursor.getString(2));
                if(result<0)
                    Toast.makeText(Profile.this,"Account not deleted",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(Profile.this, "You have deleted your account", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(Profile.this,MainActivity.class));
                    finish();   //finish current activity

                }
            }
        });

        //update
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(Profile.this,Update.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        //for logout
        logout=(Button)findViewById(R.id.button10);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();

                startActivity(new Intent(Profile.this,MainActivity.class));
                finish();   //finish current activity

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
