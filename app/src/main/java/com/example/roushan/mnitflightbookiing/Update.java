package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DatebaseHelper datebaseHelper;
    EditText name,email,password;
    Button update;
    TextView profile;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle("Update Your Account");
        update=(Button)findViewById(R.id.button11);
        name=(EditText)findViewById(R.id.editText16);
        email=(EditText)findViewById(R.id.editText18);
        password=(EditText)findViewById(R.id.editText17);
        profile=(TextView)findViewById(R.id.textView61);

        //database
        datebaseHelper=new DatebaseHelper(this);
        //for show profile
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        final Cursor cursor=datebaseHelper.getCustomerName(sharedPreferences.getString("email",""));
        cursor.moveToFirst();
        profile.setText("Hi,"+cursor.getString(1));
        name.setText(cursor.getString(1));
        email.setText(cursor.getString(2));
        password.setText(cursor.getString(3));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(intent!=null) {
                    // dep=intent.getStringExtra("depart");
                   id =intent.getIntExtra("id",0);
                }

              boolean result = datebaseHelper.UpdateProfile(id,name.getText().toString(),email.getText().toString(),password.getText().toString());
                if(result)
                {
                    Toast.makeText(Update.this,"Your profile is updated,thankss..",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(Update.this,LogIn.class));
                    finish();   //finish current activity
                }
                else
                {
                    Toast.makeText(Update.this,"Sorry,Your profile is not updated",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
