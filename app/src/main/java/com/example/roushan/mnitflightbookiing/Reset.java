package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reset extends AppCompatActivity {
    Button send;
    Intent intent=null,chooser=null;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        setTitle("Reset Password");
        //for back button
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        email=(EditText)findViewById(R.id.editText9);

        send=(Button)findViewById(R.id.button4);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Intent.ACTION_SEND);


                intent.putExtra(Intent.EXTRA_EMAIL,"rrajbittu420@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,"please reset your password");
                intent.putExtra(Intent.EXTRA_TEXT,"this is mail form mnitflightbooing ,please reset your password");
                intent.setType("message/rfc822");
                chooser=Intent.createChooser(intent,"send mail");
                startActivity(chooser);

            }
        });
       /* cancle=(Button)findViewById(R.id.button3);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reset.this,MainActivity.class));
            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public  void process(View view)
        {
            if(view.getId()==R.id.button3)
            {
                startActivity(new Intent(Reset.this,MainActivity.class));
            }

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
