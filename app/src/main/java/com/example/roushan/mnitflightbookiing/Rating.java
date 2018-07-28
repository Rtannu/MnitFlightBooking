package com.example.roushan.mnitflightbookiing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rating extends AppCompatActivity {
    RatingBar ratingBar;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        setTitle("Rating");
        //for back on action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        clickOnRatingBar();
        clickSubmit();
    }
    public void clickOnRatingBar()
         {
             textView=(TextView)findViewById(R.id.textView29) ;
             ratingBar=(RatingBar)findViewById(R.id.ratingBar);
             ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                 @Override
                 public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                         textView.setText(String.valueOf(rating));
                 }
             });
         }
    public void clickSubmit()
    {
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Rating.this,String.valueOf(ratingBar.getRating()),Toast.LENGTH_SHORT).show();
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
