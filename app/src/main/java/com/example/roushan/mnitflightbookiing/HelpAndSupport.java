package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HelpAndSupport extends AppCompatActivity {
    Intent intent,chooser;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_support);
        setTitle("Help and  Support");
        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    //for back on action bar
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
    public void progress(View view)
          {
              if(view.getId()==R.id.imageView8)
                  startActivity(new Intent(HelpAndSupport.this,Faq.class));
              else if(view.getId()==R.id.textView30)
                  startActivity(new Intent(HelpAndSupport.this,Faq.class));
              else if(view.getId()==R.id.textView31)
                  startActivity(new Intent(HelpAndSupport.this,Faq.class));
              else if(view.getId()==R.id.imageView9 | view.getId()==R.id.textView32 | view.getId()==R.id.textView33 )
                  {
                      intent=new Intent(Intent.ACTION_SEND);
                      intent.putExtra(Intent.EXTRA_EMAIL,"rrajbittu420@gmail.com");
                      intent.putExtra(Intent.EXTRA_SUBJECT,"please reset your password");
                      intent.putExtra(Intent.EXTRA_TEXT,"this is mail form mnitflightbooing ,please reset your password");
                      intent.setType("message/rfc822");
                      chooser=Intent.createChooser(intent,"send mail");
                      startActivity(chooser);
                  }
              else if(view.getId()==R.id.imageView10 | view.getId()==R.id.textView34 | view.getId()==R.id.textView35 )
              {
                  startActivity(new Intent(HelpAndSupport.this,Rating.class));
              }
          }
}
